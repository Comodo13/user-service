package max.userservice.events;

import max.userservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UserCreatedEventListener {

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        User user = event.getUser();

        LocalDateTime timestamp = LocalDateTime.now();
        Logger logger = LoggerFactory.getLogger(this.getClass());

        //logger.info("Oh, boy, sending email to: {}", user.getEmail());
        // Format timestamp
        String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Log user creation event with details
        logger.info("Oh, boy, User created event at {}: Username={}, Email={}", formattedTimestamp, user.getUsername(), user.getEmail());

    }
}
