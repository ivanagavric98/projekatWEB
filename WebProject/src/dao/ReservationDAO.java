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
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import enumeration.ReservationStatus;
import model.Apartment;
import model.Reservation;

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
/*
	public boolean addReservation(Reservation reservation, LocalDate ld, int n, ArrayList<LocalDate> available) throws NoSuchAlgorithmException, IOException {
		
		int nightsNumber = reservation.getNightsNumber();
		LocalDate date =  reservation.getStartDate();
		
		
		//provera dostupnosti apartmana

		for(int i = 0; i < n; i++) {
			LocalDate loc =  ld.plusDays(i + 1);
			if(!available.contains(loc)) {
				return false;
			}
		}
		
//		LocalDate localDate = LocalDate.of(Integer.parseInt(part[2]), Integer.parseInt(part[0]), Integer.parseInt(part[1]));
		
		reservation.setId(reservations.size() + 1);
		reservation.setReservationStatus(ReservationStatus.CREATED);
		
		reservations.put(reservation.getId(), reservation);
		saveReservations(contextPath);
		return true;
		
	}
*/
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