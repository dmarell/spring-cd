package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@ManagedResource
@RestController
public class GreetingController {
    @Autowired
    private Config config;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format("Hello %s!, backgroundColor=%s, counter=%d", name, config.getBackgroundColor(), counter.get()));
    }

    @ManagedAttribute
    public long getCounter() {
        return counter.get();
    }
}
