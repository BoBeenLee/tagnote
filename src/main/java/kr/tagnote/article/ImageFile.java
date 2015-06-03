package kr.tagnote.article;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "file")
public class ImageFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
	@SequenceGenerator(name = "auto_gen", sequenceName = "file_file_id_seq")
	@Column(name = "file_id")
	private long fileId;
	@Column(name = "art_id")
	private long artId;
	private String name;
	private long size;
	private String type;
	private String url;
	Timestamp created;
	@Column(name = "public_id")
	private String publicId;
	
	@Transient
	private byte[] bytes;
	
	@PrePersist
	public void onCreate() {
		created = new Timestamp((new Date()).getTime());
	}
}
