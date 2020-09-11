package dao;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.Cookie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Comment;;

public class CommentDAO {

	private HashMap<Long,Comment> comments = new HashMap<Long, Comment>();
	private String contextPath;
	
	
	public CommentDAO(String contextPath) throws NoSuchAlgorithmException, IOException {
		this.contextPath = contextPath;
		comments = loadComments(contextPath);
	}

	public Collection<Comment> findAllComments() {
		return comments.values();
	}
	
	public Comment addNewComment(Comment comment) throws NoSuchAlgorithmException, IOException {
		
		long id = comments.size() + 1;
		
		Comment com = new Comment(
					id,
					comment.getGuest(),
					comment.getApartment(),
					comment.getText(),
					comment.getRatingOfApartments());
		
		comments.put(id, com);
		saveComments(contextPath);
		return comment;
	}
	
	
	
	public HashMap<Long,Comment> loadComments(String contextPath) throws IOException, NoSuchAlgorithmException {
	    ObjectMapper mapper = new ObjectMapper();
	    File commentFile = new File(contextPath + "/comments.json");
	    boolean success = commentFile.createNewFile();
	    if (success) {
	       mapper.writeValue(commentFile, comments);
	    }
	    return mapper.readValue(commentFile, new TypeReference<HashMap<Long,Comment>>() {});
	}

	public void saveComments(String contextPath) throws IOException, NoSuchAlgorithmException {
	    ObjectMapper mapper = new ObjectMapper();
	    File commentFile = new File(contextPath + "/comments.json");
	    commentFile.createNewFile();
	    mapper.writeValue(commentFile, comments);
		}
	
}
