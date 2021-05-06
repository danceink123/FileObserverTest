package com.wow.dudu.commonBridge.warp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.wow.dudu.commonBridge.DuduBridgeCallback;
import com.wow.dudu.commonBridge.DuduBridgeInterface;

public abstract class DuduBridgeClient extends DuduBaseBridge {
    private static final String TAG = "DUDU-COMMON-DuduBridge";
    private String action;
    private Context context;
    private String cserverName;
    private boolean debug;
    private DuduBridgeCallback duduBridgeCallback = new DuduBridgeCallback.Stub() {
        /* class com.wow.dudu.commonBridge.warp.DuduBridgeClient.BinderC05222 */

        @Override // com.wow.dudu.commonBridge.DuduBridgeCallback
        public Response notice(String str) throws RemoteException {
            try {
                if (DuduBridgeClient.this.debug) {
                    Log.d(DuduBridgeClient.TAG, "msg:" + str);
                }
                BaseWarp handleNotice = DuduBridgeClient.this.handleNotice(DuduBridgeClient.this.decode(str));
                if (handleNotice == null) {
                    return Response.m26ok();
                }
                return Response.m27ok(DuduBridgeClient.this.encoded(handleNotice));
            } catch (DuduBridgeRunException e) {
                return Response.fail(e.getMessage());
            }
        }
    };
    private DuduBridgeInterface duduBridgeInterface;
    private String packageName;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        /* class com.wow.dudu.commonBridge.warp.DuduBridgeClient.ServiceConnectionC05211 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DuduBridgeClient.this.duduBridgeInterface = DuduBridgeInterface.Stub.asInterface(iBinder);
            try {
                DuduBridgeClient.this.duduBridgeInterface.setCallback(DuduBridgeClient.this.duduBridgeCallback);
                DuduBridgeClient.this.onBind();
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    DuduBridgeClient.this.context.unbindService(this);
                } catch (Exception unused) {
                }
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            DuduBridgeClient.this.unBind();
        }
    };

    public abstract BaseWarp handleNotice(BaseWarp baseWarp) throws DuduBridgeRunException;

    public void onBind() {
    }

    public void onUnBind() {
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public DuduBridgeClient(Context context2, String str, String str2) {
        this.context = context2;
        this.packageName = str;
        this.cserverName = str2;
    }

    public DuduBridgeClient(Context context2, String str, String str2, String str3) {
        this.context = context2;
        this.packageName = str;
        this.cserverName = str2;
        this.action = str3;
    }

    public void tryBind() {
        try {
            if (this.duduBridgeInterface != null && (!this.duduBridgeInterface.asBinder().pingBinder() || !this.duduBridgeInterface.asBinder().isBinderAlive())) {
                this.context.unbindService(this.serviceConnection);
                this.duduBridgeInterface = null;
            }
        } catch (Exception unused) {
        }
        if (this.duduBridgeInterface == null) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(this.packageName, this.cserverName));
                if (this.action != null) {
                    intent.setAction(this.action);
                }
                this.context.bindService(intent, this.serviceConnection, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isBind() {
        DuduBridgeInterface duduBridgeInterface2 = this.duduBridgeInterface;
        return duduBridgeInterface2 != null && duduBridgeInterface2.asBinder().pingBinder() && this.duduBridgeInterface.asBinder().isBinderAlive();
    }

    public void unBind() {
        try {
            if (this.duduBridgeInterface != null) {
                this.duduBridgeInterface.setCallback(null);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.duduBridgeInterface = null;
        onUnBind();
    }

    public BaseWarp action(BaseWarp baseWarp) throws DuduBridgeRunException {
        Response response;
        DuduBridgeInterface duduBridgeInterface2 = this.duduBridgeInterface;
        if (duduBridgeInterface2 != null) {
            try {
                response = duduBridgeInterface2.action(encoded(baseWarp));
            } catch (RemoteException e) {
                e.printStackTrace();
                response = null;
            }
            if (response == null) {
                throw new DuduBridgeRunException("连接失败");
            } else if (response.getCode() == 0) {
                return null;
            } else {
                if (response.getCode() > 0) {
                    if (this.debug) {
                        Log.d(TAG, "res:" + response.getParam());
                    }
                    return decode(response.getParam());
                }
                throw new DuduBridgeRunException(response.getParam());
            }
        } else {
            throw new DuduBridgeRunException("远程服务未连接");
        }
    }
}
