package kr.tagnote.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private long userId;
	private String email;
	private String password;
	private Timestamp created;
	private Timestamp updated;
}
