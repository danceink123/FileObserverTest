package com.wow.carlauncher.widget.time;

import com.wow.carlauncher.widget.LogEx;
import com.wow.carlauncher.widget.TaskExecutor;
import com.wow.carlauncher.widget.time.event.TMEvent10Second;
import com.wow.carlauncher.widget.time.event.TMEvent30Minute;
import com.wow.carlauncher.widget.time.event.TMEvent3Second;
import com.wow.carlauncher.widget.time.event.TMEvent5Minute;
import com.wow.carlauncher.widget.time.event.TMEventHalfSecond;
import com.wow.carlauncher.widget.time.event.TMEventMinute;
import com.wow.carlauncher.widget.time.event.TMEventSecond;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ScheduledFuture;

public class TimeManage {
    private static final int HOUR = 7200;
    private static final int MHOUR = 3600000;
    private static final int MINUTE = 120;
    private static final int MINUTE30 = 3600;
    private static final int MINUTE5 = 600;
    private static final int MMINUTE = 60000;
    private static final int MMINUTE30 = 1800000;
    private static final int MMINUTE5 = 300000;
    private static final int MSECOND = 1000;
    private static final int MSECOND10 = 10000;
    private static final int MSECOND3 = 3000;
    private static final int SECOND = 2;
    private static final int SECOND10 = 20;
    private static final int SECOND3 = 6;
    private static final int ZHOUQI = 500;
    private long timeMark;
    private ScheduledFuture<?> timer;

    /* access modifiers changed from: private */
    public static class SingletonHolder {
        private static final TimeManage instance = new TimeManage();

        private SingletonHolder() {
        }
    }

    public static TimeManage self() {
        return SingletonHolder.instance;
    }

    private TimeManage() {
        this.timeMark = 0;
    }

    public void init() {
        long currentTimeMillis = System.currentTimeMillis();
        startTimer();
        LogEx.m22d(this, "init time:" + (System.currentTimeMillis() - currentTimeMillis));
    }

    private void startTimer() {
        stopTimer();
        LogEx.m22d(this, "startTimer");
        this.timer = TaskExecutor.self().repeatRun(new Runnable() {
            /* class com.wow.carlauncher.widget.time.$$Lambda$TimeManage$V4Z8GLvS1H4H8hDd_Oo54YzeXY */

            public final void run() {
                TimeManage.this.lambda$startTimer$0$TimeManage();
            }
        }, 500, 500);
    }

    public /* synthetic */ void lambda$startTimer$0$TimeManage() {
        try {
            if (EventBus.getDefault().hasSubscriberForEvent(TMEventHalfSecond.class)) {
                EventBus.getDefault().post(new TMEventHalfSecond());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.timeMark % 2 == 0 && EventBus.getDefault().hasSubscriberForEvent(TMEventSecond.class)) {
                EventBus.getDefault().post(new TMEventSecond());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (this.timeMark % 6 == 0 && EventBus.getDefault().hasSubscriberForEvent(TMEvent3Second.class)) {
                EventBus.getDefault().post(new TMEvent3Second());
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            if (this.timeMark % 20 == 0 && EventBus.getDefault().hasSubscriberForEvent(TMEvent10Second.class)) {
                EventBus.getDefault().post(new TMEvent10Second());
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            if (this.timeMark % 600 == 0 && EventBus.getDefault().hasSubscriberForEvent(TMEvent5Minute.class)) {
                EventBus.getDefault().post(new TMEvent5Minute());
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            if (this.timeMark % 120 == 0 && EventBus.getDefault().hasSubscriberForEvent(TMEventMinute.class)) {
                EventBus.getDefault().post(new TMEventMinute());
            }
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            if (this.timeMark % 3600 == 0 && EventBus.getDefault().hasSubscriberForEvent(TMEvent30Minute.class)) {
                EventBus.getDefault().post(new TMEvent30Minute());
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        this.timeMark++;
    }

    private void stopTimer() {
        LogEx.m22d(this, "stopTimer");
        ScheduledFuture<?> scheduledFuture = this.timer;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.timer = null;
        }
    }
}
