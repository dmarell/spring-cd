package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.concurrent.atomic.AtomicLong;

@ManagedResource
@RestController
@Transactional
public class GreetingController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Config config;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private EntityManager entityManager;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public Iterable<Greeting> getGreetings(@RequestParam(value = "authorName", required = false) String authorName) {
        logger.info(String.format("getGreetings: name=%s, backgroundColor=%s, counter=%d",
                authorName, config.getBackgroundColor(), counter.incrementAndGet()));
        if (authorName != null) {
            Author author = authorRepository.findByName(authorName);
            if (author != null) {
                return greetingRepository.findByAuthor(author);
            }
        }
        return greetingRepository.findAll();
    }

    @RequestMapping(value = "/greetings/{id}", method = RequestMethod.GET)
    public Greeting getGreeting(@PathVariable(value = "id") long id) {
        logger.info(String.format("getGreeting: id=%d", id));
        return greetingRepository.findOne(id);
    }

    @RequestMapping(value = "/greetings", method = RequestMethod.POST)
    public long storeGreeting(@RequestParam(value = "authorName") String authorName,
                              @RequestParam(value = "message") String message) {
        logger.info(String.format("storeGreeting: authorName=%s, message=%s, backgroundColor=%s, counter=%d",
                authorName, message, config.getBackgroundColor(), counter.incrementAndGet()));
        Author author = findOrCreateAuthor(authorName);
        Greeting greeting = new Greeting(message, author);
        entityManager.persist(greeting);
        entityManager.flush();
        return greeting.getId();
    }

    private Author findOrCreateAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);
        if (author == null) {
            author = new Author(authorName);
            entityManager.persist(author);
        }
        return author;
    }

    @ManagedAttribute
    public long getCounter() {
        return counter.get();
    }
}
