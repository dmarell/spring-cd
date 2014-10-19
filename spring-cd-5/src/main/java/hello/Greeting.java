package hello;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Greeting extends AbstractEntity {
    private String content;

    @ManyToOne(optional = false)
    private Author author;

    public Greeting() {
    }

    public Greeting(String content, Author author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
