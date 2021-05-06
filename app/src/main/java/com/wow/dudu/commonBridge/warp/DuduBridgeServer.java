package com.wow.dudu.commonBridge.warp;

import android.os.RemoteException;
import com.wow.dudu.commonBridge.DuduBridgeCallback;
import com.wow.dudu.commonBridge.DuduBridgeInterface;

public abstract class DuduBridgeServer extends DuduBaseBridge {
    private DuduBridgeCallback duduBridgeCallback;
    private DuduBridgeInterfaceImpl duduBridgeInterface = new DuduBridgeInterfaceImpl();

    public abstract void connectSuccess();

    public abstract BaseWarp handleAction(BaseWarp baseWarp) throws DuduBridgeRunException;

    public DuduBridgeInterfaceImpl getInterface() {
        return this.duduBridgeInterface;
    }

    public boolean isConnected() {
        DuduBridgeCallback duduBridgeCallback2 = this.duduBridgeCallback;
        return duduBridgeCallback2 != null && duduBridgeCallback2.asBinder().isBinderAlive() && this.duduBridgeCallback.asBinder().pingBinder();
    }

    public BaseWarp notice(BaseWarp baseWarp) throws DuduBridgeRunException {
        Response response;
        if (isConnected()) {
            try {
                response = this.duduBridgeCallback.notice(encoded(baseWarp));
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
                    return decode(response.getParam());
                }
                throw new DuduBridgeRunException(response.getParam());
            }
        } else {
            throw new DuduBridgeRunException("远程服务未连接");
        }
    }

    /* access modifiers changed from: package-private */
    public class DuduBridgeInterfaceImpl extends DuduBridgeInterface.Stub {
        DuduBridgeInterfaceImpl() {
        }

        @Override // com.wow.dudu.commonBridge.DuduBridgeInterface
        public Response action(String str) throws RemoteException {
            try {
                BaseWarp handleAction = DuduBridgeServer.this.handleAction(DuduBridgeServer.this.decode(str));
                if (handleAction == null) {
                    return Response.m26ok();
                }
                return Response.m27ok(DuduBridgeServer.this.encoded(handleAction));
            } catch (DuduBridgeRunException e) {
                return Response.fail(e.getMessage());
            }
        }

        @Override // com.wow.dudu.commonBridge.DuduBridgeInterface
        public void setCallback(DuduBridgeCallback duduBridgeCallback) throws RemoteException {
            DuduBridgeServer.this.duduBridgeCallback = duduBridgeCallback;
            DuduBridgeServer.this.connectSuccess();
        }
    }
}
