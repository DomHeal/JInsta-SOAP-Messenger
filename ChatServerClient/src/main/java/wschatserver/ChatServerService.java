/**
 * ChatServerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wschatserver;

public interface ChatServerService extends javax.xml.rpc.Service {
    public java.lang.String getChatServerAddress();

    public wschatserver.ChatServer getChatServer() throws javax.xml.rpc.ServiceException;

    public wschatserver.ChatServer getChatServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
