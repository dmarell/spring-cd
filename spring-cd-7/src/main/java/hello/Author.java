/*
 * Created by Daniel Marell 14-10-19 13:11
 */
package hello;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Author extends AbstractEntity {
    @Column(unique = true)
    private String name;
    private String lastName;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
