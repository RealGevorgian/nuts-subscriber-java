package com.nuts.service;

import com.nuts.data.MessageRepository;

public class MessageService {

    private final MessageRepository repository = new MessageRepository();

    public void handleMessage(String message) {
        if (message == null || message.isBlank()) {
            System.out.println("Skipped blank message.");
            return;
        }

        System.out.println("Processing: " + message);
        repository.save(message);
    }
}
