/*
 * Created by Daniel Marell 14-10-19 13:53
 */
package hello;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {
    List<Greeting> findByAuthor(Author author);
}
