package com.wow.dudu.commonBridge;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.wow.dudu.commonBridge.DuduBridgeCallback;
import com.wow.dudu.commonBridge.warp.Response;

public interface DuduBridgeInterface extends IInterface {
    Response action(String str) throws RemoteException;

    void setCallback(DuduBridgeCallback duduBridgeCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements DuduBridgeInterface {
        private static final String DESCRIPTOR = "com.wow.dudu.commonBridge.DuduBridgeInterface";
        static final int TRANSACTION_action = 1;
        static final int TRANSACTION_setCallback = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static DuduBridgeInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof DuduBridgeInterface)) {
                return new Proxy(iBinder);
            }
            return (DuduBridgeInterface) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                Response action = action(parcel.readString());
                parcel2.writeNoException();
                if (action != null) {
                    parcel2.writeInt(1);
                    action.writeToParcel(parcel2, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setCallback(DuduBridgeCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements DuduBridgeInterface {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.wow.dudu.commonBridge.DuduBridgeInterface
            public Response action(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Response.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wow.dudu.commonBridge.DuduBridgeInterface
            public void setCallback(DuduBridgeCallback duduBridgeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(duduBridgeCallback != null ? duduBridgeCallback.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
