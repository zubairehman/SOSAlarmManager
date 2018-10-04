package com.embrace.soslibrary.ui.alarm.models;

/**
 * Created by Umair Adil on 13/02/2017.
 */

public class EventType {

    public static final int EVENT_UNDEFINED = 99;
    public static final int EVENT_REFRESH_DATA = 1;
    public static final int EVENT_AVAILABLE = 2; //Available
    public static final int EVENT_SYNC_COMPLETE = 3;
    public static final int EVENT_LOGOUT = 4;
    public static final int EVENT_SHOW_START_SCREEN = 5;
    public static final int EVENT_JOB_ASSIGNED = 6;
    public static final int EVENT_JOB_UNASSIGNED = 7;
    public static final int EVENT_REFRESH_CHAT = 8;
    public static final int EVENT_ALARM_RESPONDED = 9;
    public static final int EVENT_JOBS_READY = 10; //Available
    public static final int EVENT_RESTORE_ASSIGN_JOB = 11;
    public static final int EVENT_SYNC_ERROR = 12;
    public static final int EVENT_CHANGE_THEME = 14;
    public static final int EVENT_CHANGE_LANGUAGE = 15;
    public static final int EVENT_RESTART_SCANNER = 18;
    public static final int EVENT_REFRESH_NOTIFICATIONS = 19;
    public static final int EVENT_LOCATION_PROVIDER_DISABLED = 20;
    public static final int EVENT_DEVICE_UNLCOKED = 21;
    public static final int EVENT_ALARM_SENT = 1021;
    public static final int EVENT_ALARM_DISMISSED = 1022;

    int event_type;
    String eventData[];

    public EventType(int event_type) {
        this.event_type = event_type;
    }

    public EventType(int event_type, String[] eventData) {
        this.event_type = event_type;
        this.eventData = eventData;
    }

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
    }

    public String[] getEventData() {
        return eventData;
    }

    public void setEventData(String... params) {
        this.eventData = params;
    }
}
