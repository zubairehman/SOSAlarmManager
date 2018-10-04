package com.embrace.soslibrary.ui.alarm.models;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.embrace.soslibrary.ui.alarm.AlarmActivity;
import com.embrace.soslibrary.ui.alarm.interfaces.ISOSAlarmListener;

import org.greenrobot.eventbus.EventBus;

public class SOSAlarmHelper {

    private final SOSAlarmBuilder sosAlarmBuilder;
    private ISOSAlarmListener listener;

    public SOSAlarmHelper(@NonNull SOSAlarmBuilder sosAlarmBuilder) {
        this.sosAlarmBuilder = sosAlarmBuilder;
    }

    public void setSOSAlarmListener(ISOSAlarmListener listener) {
        this.listener = listener;
    }

    public void startAlarmActivity() {
        EventBus.getDefault().postSticky(this);
        Intent intent = new Intent(sosAlarmBuilder.getActivity(), AlarmActivity.class);
        sosAlarmBuilder.getActivity().startActivity(intent);
    }

    public SOSAlarmBuilder getSOSAlarmBuilder() {
        return sosAlarmBuilder;
    }
}
