package com.wow.carlauncher.widget;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.FileObserver;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;

import com.feketga.fileobservertest.R;
import com.wow.carlauncher.widget.common.AppUtil;
import com.wow.carlauncher.widget.console.CSMEventAddMessage;
import com.wow.carlauncher.widget.time.event.TMEvent10Second;
import com.wow.carlauncher.widget.ty.TyEventInfo;
import com.wow.carlauncher.widget.ty.TyEventState;
import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.DuduBridgeRunException;
import com.wow.dudu.commonBridge.warp.DuduBridgeServer;
import com.wow.dudu.commonBridge.warp.FromJsonInterface;
import com.wow.dudu.commonBridge.warp.ex.ExWarpConvert;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SExCmd;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SGetMusicLrcReq;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SKillApp;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CTirePressureInfo;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CTirePressureState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private final String TAG = "MyService";
    private DuduBridgeServer duduBridgeServer;
    private boolean checkSuccess = true;
    private boolean connected = false;

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
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(TAG, "嘟嘟桌面扩展服务", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.setShowBadge(true);
            notificationChannel.setDescription("嘟嘟桌面扩展服务");
            notificationChannel.setLockscreenVisibility(1);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(notificationChannel);
            startForeground(1, new Notification.Builder(this).setChannelId(TAG).setContentTitle("嘟嘟桌面扩展服务").setContentText("服务运行中...").setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).build());
        }
        this.duduBridgeServer = new DuduBridgeServer() {

            @Override
            public BaseWarp decodeJson(short s, String str) {
                return null;
            }

            @Override
            public String encodedJson(BaseWarp baseWarp) {
                return null;
            }

            @Override
            public void connectSuccess() {
                addToDumpAndSend("嘟嘟主服务绑定成功");
                MyService.this.connected = true;
            }

            @Override
            public BaseWarp handleAction(BaseWarp baseWarp) throws DuduBridgeRunException {
                if (!MyService.this.checkSuccess) {
                    return null;
                }
                if (baseWarp instanceof C2SExCmd) {
                    EventBus eventBus = EventBus.getDefault();
                    StringBuilder sb = new StringBuilder();
                    sb.append("CMD：");
                    C2SExCmd c2SExCmd = (C2SExCmd) baseWarp;
                    sb.append(c2SExCmd.getExcmd());
                    eventBus.post(new CSMEventAddMessage(sb.toString()));
                    int excmd = c2SExCmd.getExcmd();
                    if (excmd == 100) {
                        EventBus.getDefault().post(new CSMEventAddMessage("连接胎压指令！"));
                        try {
                            MyService.this.duduBridgeServer.notice(new S2CTirePressureState().setReady(true));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        TaskExecutor.self().run(LambdaMainService.INSTANCE);
                    }
                }
                return null;
            }
        };
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.duduBridgeServer.getInterface();
    }

    public void onCreated() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(TAG, "嘟嘟桌面扩展服务", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.setShowBadge(true);
            notificationChannel.setDescription("嘟嘟桌面扩展服务");
            notificationChannel.setLockscreenVisibility(1);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(notificationChannel);
            startForeground(1, new Notification.Builder(this).setChannelId(TAG).setContentTitle("嘟嘟桌面扩展服务").setContentText("服务运行中...").setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).build());
        }
        this.duduBridgeServer = new DuduBridgeServer() {

            @Override
            public BaseWarp decodeJson(short s, String str) {
                return null;
            }

            @Override
            public String encodedJson(BaseWarp baseWarp) {
                return null;
            }

            @Override
            public void connectSuccess() {
                addToDumpAndSend("嘟嘟主服务绑定成功");
                MyService.this.connected = true;
            }

            @Override
            public BaseWarp handleAction(BaseWarp baseWarp) throws DuduBridgeRunException {
                if (!MyService.this.checkSuccess) {
                    return null;
                }
                if (baseWarp instanceof C2SExCmd) {
                    EventBus eventBus = EventBus.getDefault();
                    StringBuilder sb = new StringBuilder();
                    sb.append("CMD：");
                    C2SExCmd c2SExCmd = (C2SExCmd) baseWarp;
                    sb.append(c2SExCmd.getExcmd());
                    eventBus.post(new CSMEventAddMessage(sb.toString()));
                    int excmd = c2SExCmd.getExcmd();
                    if (excmd == 100) {
                        EventBus.getDefault().post(new CSMEventAddMessage("连接胎压指令！"));
                        try {
                            MyService.this.duduBridgeServer.notice(new S2CTirePressureState().setReady(true));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        TaskExecutor.self().run(LambdaMainService.INSTANCE);
                    }
                }
                return null;
            }
        };
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
        intent.putExtra(EXTRA_EVENT_DUMP, text == null ? mEventDump.toString() : text);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(TMEvent10Second tMEvent10Second) {
        this.checkSuccess = AppUtil.isDefaultLauncher(this, "com.wow.carlauncher");
        if (!this.checkSuccess) {
            EventBus.getDefault().post(new CSMEventAddMessage("请将嘟嘟车机桌面设置为默认桌面后再使用此应用！！！！"));
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(TyEventState tyEventState) {
        try {
            if (this.checkSuccess) {
                this.duduBridgeServer.notice(new S2CTirePressureState().setReady(tyEventState.isReady()));
            }
        } catch (DuduBridgeRunException e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(TyEventInfo tyEventInfo) {
        try {
            if (this.checkSuccess) {
                this.duduBridgeServer.notice(new S2CTirePressureInfo().setLBTemp(tyEventInfo.getlBTemp()).setLBTirePressure(tyEventInfo.getlBTirePressure()).setLFTemp(tyEventInfo.getlFTemp()).setLFTirePressure(tyEventInfo.getlFTirePressure()).setRFTemp(tyEventInfo.getrFTemp()).setRFTirePressure(tyEventInfo.getrFTirePressure()).setRBTemp(tyEventInfo.getrBTemp()).setRBTirePressure(tyEventInfo.getrBTirePressure()));
            }
        } catch (DuduBridgeRunException e) {
            e.printStackTrace();
        }
    }

}
