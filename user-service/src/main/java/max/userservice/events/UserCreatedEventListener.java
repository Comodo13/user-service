package max.userservice.events;

import max.userservice.model.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventListener {

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        User user = event.getUser();

        System.out.println("Oh, boy, sending email to " + user.getEmail());
    }
}
