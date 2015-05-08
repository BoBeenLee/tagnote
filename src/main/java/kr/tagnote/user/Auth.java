package kr.tagnote.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Auth {
	@Id
	@GeneratedValue
	@Column(name = "auth_id")
	private int authId;
	private String name;
	
	public static enum Role {
		ADMIN, USER
	}
}
