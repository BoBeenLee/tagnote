package kr.tagnote.file;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import kr.tagnote.article.Article;
import lombok.Data;

@Entity
@Data
@Table(name = "file")
public class TagFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
	@SequenceGenerator(name = "auto_gen", sequenceName = "file_file_id_seq")
	@Column(name = "file_id")
	private long fileId;
	private String name;
	private long size;
	private String type;
	private String url;
	private Timestamp created;
	@Column(name = "public_id")
	private String publicId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "art_id")
	private Article article;
	
	@Transient
	private byte[] bytes;
	
	@PrePersist
	public void onCreate() {
		created = new Timestamp((new Date()).getTime());
	}
	
	@Data
	public static class Response {
		private long fileId;
		private String name;
		private Article.Response article;
		private long size;
		private String type;
		private String url;
		private String publicId;
		private Timestamp created;
	}
}
