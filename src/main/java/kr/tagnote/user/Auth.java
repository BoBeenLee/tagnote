package kr.tagnote.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Auth {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
	@SequenceGenerator(name = "auto_gen", sequenceName = "auth_auth_id_seq")
	@Column(name = "auth_id")
	private int authId;
	private String name;
	
	public static enum Role {
		ADMIN, USER
	}
}
