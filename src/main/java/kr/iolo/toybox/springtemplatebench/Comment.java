package kr.iolo.toybox.springtemplatebench;

import javax.persistence.*;
import java.util.Date;

/**
 * @author iolo
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String author;
    @Column
    private String content;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

}
