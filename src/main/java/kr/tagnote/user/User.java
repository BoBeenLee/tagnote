package kr.tagnote.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
	private String password;
	private String uid;
	private Timestamp created;
	private Timestamp updated;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_id")
	private Auth auth;

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
		private Auth auth;
	}

	@Data
	public static class Response {
		private long userId;
		private String email;
		private String uid;
		private Auth auth;
	}
}
