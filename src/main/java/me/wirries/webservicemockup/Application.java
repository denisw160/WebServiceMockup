package me.wirries.webservicemockup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * This is the main class for the application and starts the service.
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * Start the service.
     *
     * @param args arguments from command line.
     */
    public static void main(String[] args) {
        LOGGER.info("Starting application with arguments {}", Arrays.toString(args));
        SpringApplication.run(Application.class, args);
    }

}
