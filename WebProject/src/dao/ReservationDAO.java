package dao;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import enumeration.ReservationStatus;
import model.Apartment;
import model.Reservation;
import model.ReservationPeriod;

public class ReservationDAO {
	private Map<Long, Reservation> reservations = new HashMap<>();
	private Map<Long, Apartment> apartments = new HashMap<>();

	private String contextPath;
	
	public ReservationDAO() {}
	
	
	public ReservationDAO(String contextPath) throws NoSuchAlgorithmException, IOException {
		this.contextPath = contextPath;
		reservations = loadReservations(contextPath);
	}
	
	public Collection<Reservation> findAll() {
		return reservations.values();
	}
	
	public Reservation getReservation(Long id) {
		return reservations.get(id);
	}
	
	public Reservation addReservation(String dateFromString, String numberOfNights, String message, Long apartmentId, String username) {
		ApartmentDAO apartmentDAO = new ApartmentDAO();

		Apartment apartment =ApartmentDAO.searchApById(apartmentId);
		System.out.println(apartment);
//		if(apartment == null) {
//
//			return null;
//		}		
		System.out.println("AA");

		LocalDate dateFrom = LocalDate.parse(dateFromString);
		LocalDate dateTo = dateFrom.plusDays(Long.parseLong(numberOfNights));
		
		System.out.println(apartment.getBusyDates());
		for (ReservationPeriod busyDate : apartment.getBusyDates()) {
			
			LocalDate busyDateTo = busyDate.getDateFrom().plusDays(busyDate.getNumberOfNights());
			if(busyDate.getDateFrom().isBefore(dateFrom) 
					&& busyDateTo.isAfter(dateTo)) {
				return null;
			} else if(busyDate.getDateFrom().isBefore(dateTo) 
					&& busyDate.getDateFrom().isAfter(dateTo)) {
				return null;
			} else if(busyDateTo.isAfter(dateFrom)
					&& busyDateTo.isBefore(dateTo)) {
				return null;
			}
		}
		
		return createReservation(dateFrom, Integer.parseInt(numberOfNights), message, apartment, username);
	}
	
	public Reservation createReservation(LocalDate dateFrom, int numberOfNights, String message, Apartment apartment, String username) {
		Reservation reservation = new Reservation();
		reservation.setId(reservations.size() + 1);
		reservation.setActive(true);
		reservation.setBookedApartment(apartment);
		reservation.setGuest(username);
		reservation.setNightsNumber(numberOfNights);
		reservation.setReservationMessage(message);
		reservation.setReservationStatus(ReservationStatus.CREATED);
		reservation.setStartDate(dateFrom);
		reservation.setTotalPrice(numberOfNights * apartment.getPricePerNight());
		reservations.put(reservation.getId(), reservation);
		
		return reservation;
	}

	public Collection<Reservation> findGuestReservation(String guest){
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		
		for(Reservation r : reservations.values()) {
			if(guest.equals(r.getGuest())) {
				res.add(r);
			}
		}
		return res;
	}
	
	public Collection<Reservation> findHostReservation(String host){
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		
		for(Reservation r : reservations.values()) {
			Apartment apartment = apartments.get(r.getBookedApartment());
			if(host.equals(apartment.getHost()))
				res.add(r);
		}
		return res;
	}
	
	public Reservation acceptReservation(Reservation reservation) {
		
//		ApartmentDAO apartmentDAO = apartmentDAO.findAllHostApartment(host);	
		
		for(Reservation res : reservations.values()) {
			if(res.getReservationStatus().equals(ReservationStatus.CREATED)) {
				reservation.setReservationStatus(ReservationStatus.ACCEPTED);
				
			}
		}
		
		return reservation;
	}
	
	
	public boolean cancelReservation(long id) {
		
		Reservation reservation = reservations.get(id);
		
			if(reservation.getReservationStatus().equals(ReservationStatus.CREATED) || reservation.getReservationStatus().equals(ReservationStatus.ACCEPTED)) {
				reservation.setReservationStatus(ReservationStatus.CANCELED);
				return true;
			}
		return false;
	}
	
	
	//ucitavanje liste korisnika iz fajla
	public HashMap<Long, Reservation> loadReservations(String contextPath) throws IOException, NoSuchAlgorithmException {
	    ObjectMapper mapper = new ObjectMapper();
	    File reservationFile = new File(contextPath + "/reservations.json");
	    boolean success = reservationFile.createNewFile();
	    if (success) {
	    	mapper.writeValue(reservationFile, reservations);
		}
	    return mapper.readValue(reservationFile, new TypeReference<HashMap<Long,Reservation>>() {});		
	}

		//upisivanje u novi fajl 
	public void saveReservations(String contextPath) throws IOException, NoSuchAlgorithmException {
	    ObjectMapper mapper = new ObjectMapper();
	    File reservationFile = new File(contextPath + "/reservations.json");
	    reservationFile.createNewFile();
	    mapper.writeValue(reservationFile, reservations);
	}

			
	
}