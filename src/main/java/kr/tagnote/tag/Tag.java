package kr.tagnote.tag;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@Entity
public class Tag {
	@Id
	@GeneratedValue
	@Column(name = "tag_id")
	private int tagId;
	@Column(unique = true)
	private String name;
	private String color;
	private Timestamp created;
	private Timestamp updated;

	@PrePersist
	public void onCreate() {
		created = updated = new Timestamp((new Date()).getTime());
	}

	@PreUpdate
	public void onUpdate() {
		updated = new Timestamp((new Date()).getTime());
	}
}
