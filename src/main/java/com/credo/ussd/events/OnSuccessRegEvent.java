package com.credo.ussd.events;

import com.credo.ussd.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnSuccessRegEvent extends ApplicationEvent {
    User user;

    public OnSuccessRegEvent(User user) {
        super(user);
        this.user = user;
    }
}
