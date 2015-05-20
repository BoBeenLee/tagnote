package kr.tagnote.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import kr.tagnote.article.Article;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"tag_id", "art_id"}))
public class TagArticle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
	@SequenceGenerator(name = "auto_gen", sequenceName = "tag_article_tag_art_id_seq")
	@Column(name = "tag_art_id")
	private long tagArtId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "art_id")
	private Article article;

	@Data
	public static class Request {
		private Tag.Request tag;
		private Article.Request article;
	}

	@Data
	public static class Response {
		private long tagArtId;
		private Tag.Reponse tag;
		private Article.Response article;
	}
}
