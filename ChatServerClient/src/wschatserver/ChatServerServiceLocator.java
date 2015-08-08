/**
 * ChatServerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wschatserver;

public class ChatServerServiceLocator extends org.apache.axis.client.Service implements wschatserver.ChatServerService {

    public ChatServerServiceLocator() {
    }


    public ChatServerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ChatServerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ChatServer
    private java.lang.String ChatServer_address = "http://localhost:8080/ChatServer/services/ChatServer";

    public java.lang.String getChatServerAddress() {
        return ChatServer_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ChatServerWSDDServiceName = "ChatServer";

    public java.lang.String getChatServerWSDDServiceName() {
        return ChatServerWSDDServiceName;
    }

    public void setChatServerWSDDServiceName(java.lang.String name) {
        ChatServerWSDDServiceName = name;
    }

    public wschatserver.ChatServer getChatServer() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ChatServer_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getChatServer(endpoint);
    }

    public wschatserver.ChatServer getChatServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            wschatserver.ChatServerSoapBindingStub _stub = new wschatserver.ChatServerSoapBindingStub(portAddress, this);
            _stub.setPortName(getChatServerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setChatServerEndpointAddress(java.lang.String address) {
        ChatServer_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (wschatserver.ChatServer.class.isAssignableFrom(serviceEndpointInterface)) {
                wschatserver.ChatServerSoapBindingStub _stub = new wschatserver.ChatServerSoapBindingStub(new java.net.URL(ChatServer_address), this);
                _stub.setPortName(getChatServerWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ChatServer".equals(inputPortName)) {
            return getChatServer();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://wschatserver", "ChatServerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://wschatserver", "ChatServer"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ChatServer".equals(portName)) {
            setChatServerEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
