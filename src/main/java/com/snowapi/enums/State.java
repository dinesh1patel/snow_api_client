package com.snowapi.enums;

public enum State {
    OPEN("Open", 1),
    WORK_IN_PROGRESS("Work in Progress", 2),
    CLOSED_COMPLETE("Closed Complete", 3),
    CLOSED_INCOMPLETE("Closed Incomplete", 4),
    AWAITING_EVIDENCE("Awaiting Evidence", 5);

    private final String name;
    private final int state;

    State (String name, int state) {
        this.name = name;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
