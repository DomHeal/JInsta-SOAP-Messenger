package wschatserver;

public class ChatServerProxy implements wschatserver.ChatServer {
  private String _endpoint = null;
  private wschatserver.ChatServer chatServer = null;
  
  public ChatServerProxy() {
    _initChatServerProxy();
  }
  
  public ChatServerProxy(String endpoint) {
    _endpoint = endpoint;
    _initChatServerProxy();
  }
  
  private void _initChatServerProxy() {
    try {
      chatServer = (new wschatserver.ChatServerServiceLocator()).getChatServer();
      if (chatServer != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)chatServer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)chatServer)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (chatServer != null)
      ((javax.xml.rpc.Stub)chatServer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public wschatserver.ChatServer getChatServer() {
    if (chatServer == null)
      _initChatServerProxy();
    return chatServer;
  }
  
  public int join(int id, java.lang.String username) throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    return chatServer.join(id, username);
  }
  
  public java.lang.String listen(int id) throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    return chatServer.listen(id);
  }
  
  public void leave(java.lang.String username) throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    chatServer.leave(username);
  }
  
  public void talk(int id, java.lang.String message) throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    chatServer.talk(id, message);
  }
  
  public int getUserCount() throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    return chatServer.getUserCount();
  }
  
  public java.util.HashMap getUsernames() throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    return chatServer.getUsernames();
  }
  
  public void privateMsg(int id, java.lang.String username, java.lang.String receiverUsername, java.lang.String message) throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    chatServer.privateMsg(id, username, receiverUsername, message);
  }
  
  public void grantAdmin(java.lang.String username) throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    chatServer.grantAdmin(username);
  }
  
  public boolean isAdmin(java.lang.String username) throws java.rmi.RemoteException{
    if (chatServer == null)
      _initChatServerProxy();
    return chatServer.isAdmin(username);
  }
  
  
}