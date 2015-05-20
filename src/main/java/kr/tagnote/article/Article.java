package kr.tagnote.article;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import kr.tagnote.tag.TagArticle;
import kr.tagnote.util.JacksonUtils;
import lombok.Data;

import com.fasterxml.jackson.core.type.TypeReference;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "parent_id"}))
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
	@SequenceGenerator(name = "auto_gen", sequenceName = "article_art_id_seq")
	@Column(name = "art_id")
	private long artId;
	@Column(name = "user_id")
	private long userId;
	@Column(name = "parent_id")
	private long parentId;
	private String subject;
	private String content;
	private Timestamp created;
	private Timestamp updated;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL)
	private List<TagArticle> tagArticles;

	@Transient
	private List<String> tagList;
	
	@PrePersist
	public void onCreate() {
		created = updated = new Timestamp((new Date()).getTime());
	}

	@PreUpdate
	public void onUpdate() {
		updated = new Timestamp((new Date()).getTime());
	}

	public static List<String> convertTagArticlesToTagList(List<TagArticle> tags) {
		List<String> list = new ArrayList<String>();
		
		for(TagArticle tagArticle : tags)
			list.add(tagArticle.getTag().getName());
		return list;
	}
	
	@Data
	public static class Request {
		private long artId;
		private String subject;
		private String content;
		private List<String> tags;

		public void setTags(String tags) {
			try {
				this.tags = JacksonUtils.jsonToObject(tags, new TypeReference<List<String>>() {
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
		private List<TagArticle.Response> tags;
		private Timestamp updated;
		private String jsonTags;
		
		public void setTags(List<TagArticle.Response> tags) {
			List<String> list = new ArrayList<String>();
			
			this.tags = tags;
			for(TagArticle.Response tagArticle : tags)
				list.add(tagArticle.getTag().getName());
			jsonTags = JacksonUtils.objectToJson(list);
		}
	}
}
