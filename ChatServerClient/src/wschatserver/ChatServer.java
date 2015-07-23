/**
 * ChatServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wschatserver;

public interface ChatServer extends java.rmi.Remote {
    public int join(int id, java.lang.String username, java.lang.String joinMessage) throws java.rmi.RemoteException;
    public java.lang.String listen(int id) throws java.rmi.RemoteException;
    public void leave(int id, java.lang.String username, java.lang.String leavemessage) throws java.rmi.RemoteException;
    public void talk(int id, java.lang.String message) throws java.rmi.RemoteException;
    public void privateMsg(int id, java.lang.String username, java.lang.String receiverUsername, java.lang.String message) throws java.rmi.RemoteException;
    public java.util.HashMap getUsernames() throws java.rmi.RemoteException;
    public int getUserCount() throws java.rmi.RemoteException;
}
