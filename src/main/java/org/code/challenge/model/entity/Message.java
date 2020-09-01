package org.code.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Message {

private String messageText;

private LocalDateTime timeStamp;

    public Message(String messageText) {
        this.messageText = messageText;
        timeStamp = LocalDateTime.now();
    }

}
