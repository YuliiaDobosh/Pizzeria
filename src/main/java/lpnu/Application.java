package lpnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
//        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(Application.class, args);
    }
}