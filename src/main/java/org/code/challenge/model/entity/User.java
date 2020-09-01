package org.code.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private String name;

    private List<Message> messages;

    public User(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(messages, user.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, messages);
    }
}
