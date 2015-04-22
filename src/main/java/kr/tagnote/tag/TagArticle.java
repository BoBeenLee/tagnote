package kr.tagnote.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TagArticle {
	@Id
	@GeneratedValue
	@Column(name = "tag_art_id")
	private long tagArtId;
	@Column(name = "tag_id")
	private int tagId;
	@Column(name = "art_id")
	private long artId;
}
