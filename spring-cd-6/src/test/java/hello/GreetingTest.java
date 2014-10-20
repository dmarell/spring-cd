/*
 * Created by Daniel Marell 14-10-20 20:10
 */
package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreetingTest {
    @InjectMocks
    GreetingController controller = new GreetingController();

    @Mock
    Config config = mock(Config.class);

    @Mock
    AuthorRepository authorRepository = mock(AuthorRepository.class);

    @Mock
    GreetingRepository greetingRepository = mock(GreetingRepository.class);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGreetings() throws Exception {
        Author authorLisa = new Author("Lisa");
        Author authorHomer = new Author("Homer");
        Greeting lisaGreeting = new Greeting("Okay, time for truth or dare. You go first.", authorHomer);
        Greeting homerGreeting1 = new Greeting("Ehh, truth. Ask me anything.", authorHomer);
        Greeting homerGreeting2 = new Greeting("D'oh! All right, dare.", authorHomer);

        when(config.getBackgroundColor()).thenReturn("whatever");

        when(authorRepository.findByName("Lisa")).thenReturn(authorLisa);
        when(authorRepository.findByName("Homer")).thenReturn(authorHomer);
        when(authorRepository.findByName("Bart")).thenReturn(null);

        when(greetingRepository.findByAuthor(authorLisa)).thenReturn(Arrays.asList(lisaGreeting));
        when(greetingRepository.findByAuthor(authorHomer)).thenReturn(Arrays.asList(homerGreeting1, homerGreeting2));
        when(greetingRepository.findAll()).thenReturn(Arrays.asList(lisaGreeting, homerGreeting1, homerGreeting2));

        assertThat(controller.getGreetings("Lisa").size(), is(1));
        assertThat(controller.getGreetings("Homer").size(), is(2));
        assertThat(controller.getGreetings("Bart").size(), is(0));
    }
}
