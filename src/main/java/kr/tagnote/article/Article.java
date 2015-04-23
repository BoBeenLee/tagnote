package kr.tagnote.article;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.type.TypeReference;

import kr.tagnote.util.JacksonUtils;
import lombok.Data;

@Data
@Entity
public class Article {
	@Id
	@GeneratedValue
	@Column(name = "art_id")
	private long artId;
	@Column(name = "user_id")
	private long userId;
	private String subject;
	private String content;
	private Timestamp created;
	private Timestamp updated;

	@Transient
	private List<String> tags;
	
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
		private String subject;
		private String content;
		private List<String> tags;

		public void setTags(String tags) {
			try {
				this.tags = JacksonUtils.jsonToObject(tags,
						new TypeReference<List<String>>() {
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Data
	public static class Response {
		private long artId;
		private long userId;
		private String subject;
		private String content;
		private List<String> tags;
		private Timestamp updated;
	}
}
