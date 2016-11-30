package reactive.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReactiveSpring5ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpring5ExampleApplication.class, args);
    }

    @RequestMapping("/")
    public String startup() {
        return "OK!";
    }

}
