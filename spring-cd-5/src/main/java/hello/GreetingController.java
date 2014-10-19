package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/greetings", method = RequestMethod.POST)
    public void storeGreeting(@RequestParam(value = "name") String name,
                              @RequestParam(value = "message") String message) {
        logger.info(String.format("storeGreeting: name=%s, message=%s, backgroundColor=%s, counter=%d",
                name, message, config.getBackgroundColor(), counter.incrementAndGet()));
        Author author = findOrCreateAuthor(name);
        Greeting greeting = new Greeting(message, author);
        entityManager.persist(greeting);
        entityManager.flush();
    }

    private Author findOrCreateAuthor(String name) {
        Author author = authorRepository.findByName(name);
        if (author == null) {
            author = new Author(name);
            entityManager.persist(author);
        }
        return author;
    }

    @ManagedAttribute
    public long getCounter() {
        return counter.get();
    }
}
