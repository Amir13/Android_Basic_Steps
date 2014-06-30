/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\Code\\Code\\workspace\\ViewElements6\\src\\com\\android\\course\\interfaces\\aidl\\service\\KeyGeneratorAidl.aidl
 */
package com.android.course.interfaces.aidl.service;
public interface KeyGeneratorAidl extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.android.course.interfaces.aidl.service.KeyGeneratorAidl
{
private static final java.lang.String DESCRIPTOR = "com.android.course.interfaces.aidl.service.KeyGeneratorAidl";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.android.course.interfaces.aidl.service.KeyGeneratorAidl interface,
 * generating a proxy if needed.
 */
public static com.android.course.interfaces.aidl.service.KeyGeneratorAidl asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.android.course.interfaces.aidl.service.KeyGeneratorAidl))) {
return ((com.android.course.interfaces.aidl.service.KeyGeneratorAidl)iin);
}
return new com.android.course.interfaces.aidl.service.KeyGeneratorAidl.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getKey:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getKey();
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.android.course.interfaces.aidl.service.KeyGeneratorAidl
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String getKey() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getKey, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.lang.String getKey() throws android.os.RemoteException;
}
