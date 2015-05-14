package kr.tagnote.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "email", unique = true)
	private String email;
	private String type;
	private String password;
	private String uid;
	@Column(name = "access_token")
	private String accessToken;
	private Timestamp created;
	private Timestamp updated;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_id")
	private Auth auth;

	public User(){
	}
	
	public User(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	@PrePersist
	public void onCreate() {
		created = updated = new Timestamp((new Date()).getTime());
	}

	@PreUpdate
	public void onUpdate() {
		updated = new Timestamp((new Date()).getTime());
	}

	@Data
	public static class Request {
		private String email;
		private String uid;
		private String password;
		private String password1;
		private Auth auth;
	}

	@Data
	public static class Response {
		private long userId;
		private String email;
		private String uid;
		private Auth auth;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Google {
		public final static String REQUEST_ME = "https://www.googleapis.com/plus/v1/people/me";
		public final static String REQUEST_REVOKE = "https://accounts.google.com/o/oauth2/revoke";
		
		public List<HashMap<String, String>> emails;
		public String displayName;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Facebook {
		public final static String REQUEST_ME = "https://graph.facebook.com/v2.3/me";
		public final static String REQUEST_REVOKE = "/user/fblogout";
		
		public String name;
		public String email;
	}
}
