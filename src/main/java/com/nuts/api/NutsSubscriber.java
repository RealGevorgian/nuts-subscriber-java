package com.nuts.api;

import com.nuts.service.MessageService;

import java.util.Timer;
import java.util.TimerTask;

public class NutsSubscriber {

    private final MessageService service = new MessageService();

    public void start() {
        System.out.println("Starting simulated Nuts subscription...");
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String msg = "Test message at " + System.currentTimeMillis();
                service.handleMessage(msg);
            }
        }, 0, 5000); // every 5 seconds
    }
}
