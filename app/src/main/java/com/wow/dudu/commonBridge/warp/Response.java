package com.wow.dudu.commonBridge.warp;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable {
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        /* class com.wow.dudu.commonBridge.warp.Response.C05231 */

        @Override // android.os.Parcelable.Creator
        public Response createFromParcel(Parcel parcel) {
            return new Response(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Response[] newArray(int i) {
            return new Response[i];
        }
    };
    private int code;
    private String param;

    public int describeContents() {
        return 0;
    }

    private Response(Parcel parcel) {
        this.code = 0;
        this.param = "";
        this.code = parcel.readInt();
        this.param = parcel.readString();
    }

    public Response() {
        this.code = 0;
        this.param = "";
    }

    public Response(int i, String str) {
        this.code = 0;
        this.param = "";
        this.code = i;
        this.param = str;
    }

    public int getCode() {
        return this.code;
    }

    public Response setCode(int i) {
        this.code = i;
        return this;
    }

    public String getParam() {
        return this.param;
    }

    public Response setParam(String str) {
        this.param = str;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.param);
    }

    /* renamed from: ok */
    public static Response m26ok() {
        return new Response(0, "");
    }

    /* renamed from: ok */
    public static Response m27ok(String str) {
        return new Response(1, str);
    }

    public static Response fail() {
        return fail("出现异常");
    }

    public static Response fail(String str) {
        return new Response(-1, str);
    }
}
