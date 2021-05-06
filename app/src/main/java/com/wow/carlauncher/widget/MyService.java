package com.wow.carlauncher.widget;

import android.app.Service;
import android.content.Intent;
import android.os.FileObserver;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyService extends Service {

    public static final String ACTION_COMMAND_START = "com.feketga.fileobservertest.intent.ACTION_COMMAND_START";
    public static final String ACTION_COMMAND_STOP = "com.feketga.fileobservertest.intent.ACTION_COMMAND_STOP";
    public static final String EXTRA_FILE_PATH = "com.feketga.fileobservertest.intent.EXTRA_FILE_PATH";

    public static final String ACTION_COMMAND_CLEAR = "com.feketga.fileobservertest.intent.ACTION_COMMAND_CLEAR";
    public static final String ACTION_COMMAND_DUMP = "com.feketga.fileobservertest.intent.ACTION_COMMAND_DUMP";

    public static final String ACTION_EVENT = "com.feketga.fileobservertest.intent.ACTION_EVENT";
    public static final String EXTRA_EVENT_DUMP = "com.feketga.fileobservertest.intent.EXTRA_EVENT_DUMP";
    public static final String EXTRA_EVENT_FILENAME = "com.feketga.fileobservertest.intent.EXTRA_EVENT_FILENAME";

    private String mObservedPath;
    private FileObserver mFileObserver;
    private StringBuilder mEventDump = new StringBuilder();
    private String TAG = "MyService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (ACTION_COMMAND_START.equals(intent.getAction())) {
                stopObserving();
                mObservedPath = intent.getStringExtra(EXTRA_FILE_PATH);
                startObserving();
            } else if (ACTION_COMMAND_STOP.equals(intent.getAction())) {
                stopObserving();
            } else if (ACTION_COMMAND_CLEAR.equals(intent.getAction())) {
                mEventDump = new StringBuilder();
                //addToDumpAndSend("CLEARED\n");
            } else if (ACTION_COMMAND_DUMP.equals(intent.getAction())) {
                sendDump(null);
            }
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void startObserving() {
        int eventMask = FileObserver.ALL_EVENTS;
        mFileObserver = new FileObserver(mObservedPath, eventMask) {
            @Override
            public void onEvent(int event, String path) {
                // Filter out the undocumented IN_IGNORED (32768) event:
                // https://code.google.com/p/android/issues/detail?id=29546&q=FileObserver&colspec=ID%20Type%20Status%20Owner%20Summary%20Stars
                final int FILE_IGNORED = 0x8000;
                if ((event & FILE_IGNORED) != 0) {
                    return;
                }
                Log.e(TAG, "onEvent" + event);

                // Workaround for a bug in FileObserver:
                // The high-order bits of event may be set for some event types.
                // So, make sure those bits are cleared before looking at the event type.
                event &= FileObserver.ALL_EVENTS;

                switch (event) {
                    case FileObserver.ACCESS:
                        //addToDumpAndSend("ACCESS:" + path);
                        break;
                    case FileObserver.MODIFY:
                        //addToDumpAndSend("MODIFY:" + path);
                        break;
                    case FileObserver.CREATE:
                        addToDumpAndSend("CREATE:" + path);
                        break;
                    case FileObserver.CLOSE_WRITE:
                        addToDumpAndSend("CLOSE_WRITE:" + path);
                        break;
                    case FileObserver.CLOSE_NOWRITE:
                        //addToDumpAndSend("CLOSE_NOWRITE:" + path);
                        break;
                    case FileObserver.OPEN:
                        //addToDumpAndSend("OPEN:" + path);
                        break;
                    case FileObserver.MOVED_TO:
                        //addToDumpAndSend("MOVED_TO:" + path);
                        break;
                    case FileObserver.ATTRIB:
                        //addToDumpAndSend("ATTRIB:" + path);
                        break;
                    case FileObserver.MOVED_FROM:
                        //addToDumpAndSend("MOVED_FROM:" + path);
                        break;
                    case FileObserver.DELETE:
                        //addToDumpAndSend("DELETE:" + path);
                        break;
                    case FileObserver.DELETE_SELF:
                        //addToDumpAndSend("DELETE_SELF:" + path);
                        break;
                    case FileObserver.MOVE_SELF:
                        //addToDumpAndSend("MOVE_SELF:" + path);
                        break;
                    default:
                        //addToDumpAndSend("UNKNOWN:" + path);
                        break;
                }
            }
        };

        mFileObserver.startWatching();
        //addToDumpAndSend("START: Observing: " + mObservedPath);
    }

    private void stopObserving() {
        if (mFileObserver != null) {
            mFileObserver.stopWatching();
            mFileObserver = null;
            //addToDumpAndSend("STOP: Observing: " + mObservedPath);
        }
    }

    private void addToDumpAndSend(final String text) {
        //mEventDump.append(text).append("\n");
        sendDump(text);
    }

    private void sendDump(String text) {
        Intent intent = new Intent(ACTION_EVENT);
        intent.putExtra(EXTRA_EVENT_DUMP, text==null?mEventDump.toString():text);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
