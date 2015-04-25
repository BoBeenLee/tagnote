package kr.tagnote.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
	private long userId;
    @Column(unique = true)
	private String email;
	private String password;
	private Timestamp created;
	private Timestamp updated;

	@OneToOne(fetch = FetchType.LAZY)
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
}
