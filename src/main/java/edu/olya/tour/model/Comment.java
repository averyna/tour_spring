package edu.olya.tour.model;

import javax.persistence.*;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "comments")
//@NamedQueries // можно сразу написать запросы, которые будут выполняться с этим классом.
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "author", nullable = false)
    private String author;

    //date date NOT NULL DEFAULT (now())::date
    @Column(name = "date", columnDefinition="date DEFAULT (now())::date")
    private Date date;

    @Column(name = "comment", nullable = false)
    private String comment;

    public Comment() {}

    public Comment(String author, Date date, String comment) {
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
