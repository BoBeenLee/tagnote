package kr.tagnote.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Test {
	@Id
	@GeneratedValue
	private String name;
	private int value;
}
