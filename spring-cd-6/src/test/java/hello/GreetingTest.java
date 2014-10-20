/*
 * Created by Daniel Marell 14-10-19 23:42
 */
package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GreetingTest {
    private static final String RESOURCE_URL = "http://localhost:8080";

    @Autowired
    GreetingRepository greetingRepository;

    @Autowired
    AuthorRepository authorRepository;

    RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void shouldStoreAndReadGreeting() throws Exception {
        assertThat(getGreetings().length == 0, is(true));

        ResponseEntity<Long> response = restTemplate.postForEntity(
                RESOURCE_URL + "/greetings?authorName={authorName}&message={message}",
                null, Long.class, "Homer", "Message 1");
        long id = response.getBody();
        assertThat(id > 0L, is(true));
        Greeting[] result = getGreetings();
        assertThat(result.length, is(1));
        Greeting greeting = result[0];
        assertThat(greeting.getAuthor().getName(), is("Homer"));
        assertThat(greeting.getContent(), is("Message 1"));
        greeting = findById(id);
        assertThat(greeting.getContent(), is("Message 1"));
    }

    Greeting findById(long id) {
        ResponseEntity<Greeting> response = restTemplate.getForEntity(
                RESOURCE_URL + "/greetings/{id}", Greeting.class, id);
        return response.getBody();
    }

    Greeting[] getGreetings() {
        ResponseEntity<Greeting[]> response = restTemplate.getForEntity(
                RESOURCE_URL + "/greetings", Greeting[].class);
        return response.getBody();
    }
}
