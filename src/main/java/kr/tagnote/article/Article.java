package kr.tagnote.article;

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
public class Article {
	@Id
	@GeneratedValue
	@Column(name = "art_id")
	private long artId;
	@Column(name="user_id")
	private long userId;
	private String content;
	private String subject;
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
