package com.example.scheduler;

public class Scheduler {
    public int id;
    public int clientId;
    public long time;
    public long timeDifference;
    public String location;

    public Scheduler(int id, int clientId, long time, long timeDifference, String location) {
        this.id = id;
        this.clientId = clientId;
        this.time = time;
        this.timeDifference = timeDifference;
        this.location = location;
    }

    public Scheduler() {
    }
}
