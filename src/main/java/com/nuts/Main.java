package com.nuts;

import com.nuts.api.NutsSubscriber;

public class Main {
    public static void main(String[] args) {
        NutsSubscriber subscriber = new NutsSubscriber();
        subscriber.start();  // Simulated message stream
    }
}
