/*
 * Created by Daniel Marell 14-10-19 13:23
 */
package hello;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByName(String lastName);
}
