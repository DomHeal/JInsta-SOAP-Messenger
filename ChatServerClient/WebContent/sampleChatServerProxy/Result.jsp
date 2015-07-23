<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleChatServerProxyid" scope="session" class="wschatserver.ChatServerProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleChatServerProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleChatServerProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleChatServerProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        wschatserver.ChatServer getChatServer10mtemp = sampleChatServerProxyid.getChatServer();
if(getChatServer10mtemp == null){
%>
<%=getChatServer10mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 19:
        gotMethod = true;
        String id_1id=  request.getParameter("id22");
        int id_1idTemp  = Integer.parseInt(id_1id);
        String username_2id=  request.getParameter("username24");
            java.lang.String username_2idTemp = null;
        if(!username_2id.equals("")){
         username_2idTemp  = username_2id;
        }
        String joinMessage_3id=  request.getParameter("joinMessage26");
            java.lang.String joinMessage_3idTemp = null;
        if(!joinMessage_3id.equals("")){
         joinMessage_3idTemp  = joinMessage_3id;
        }
        int join19mtemp = sampleChatServerProxyid.join(id_1idTemp,username_2idTemp,joinMessage_3idTemp);
        String tempResultreturnp20 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(join19mtemp));
        %>
        <%= tempResultreturnp20 %>
        <%
break;
case 28:
        gotMethod = true;
        String id_4id=  request.getParameter("id31");
        int id_4idTemp  = Integer.parseInt(id_4id);
        java.lang.String listen28mtemp = sampleChatServerProxyid.listen(id_4idTemp);
if(listen28mtemp == null){
%>
<%=listen28mtemp %>
<%
}else{
        String tempResultreturnp29 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(listen28mtemp));
        %>
        <%= tempResultreturnp29 %>
        <%
}
break;
case 33:
        gotMethod = true;
        String id_5id=  request.getParameter("id36");
        int id_5idTemp  = Integer.parseInt(id_5id);
        String username_6id=  request.getParameter("username38");
            java.lang.String username_6idTemp = null;
        if(!username_6id.equals("")){
         username_6idTemp  = username_6id;
        }
        String leavemessage_7id=  request.getParameter("leavemessage40");
            java.lang.String leavemessage_7idTemp = null;
        if(!leavemessage_7id.equals("")){
         leavemessage_7idTemp  = leavemessage_7id;
        }
        sampleChatServerProxyid.leave(id_5idTemp,username_6idTemp,leavemessage_7idTemp);
break;
case 42:
        gotMethod = true;
        String id_8id=  request.getParameter("id45");
        int id_8idTemp  = Integer.parseInt(id_8id);
        String message_9id=  request.getParameter("message47");
            java.lang.String message_9idTemp = null;
        if(!message_9id.equals("")){
         message_9idTemp  = message_9id;
        }
        sampleChatServerProxyid.talk(id_8idTemp,message_9idTemp);
break;
case 49:
        gotMethod = true;
        int getUserCount49mtemp = sampleChatServerProxyid.getUserCount();
        String tempResultreturnp50 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getUserCount49mtemp));
        %>
        <%= tempResultreturnp50 %>
        <%
break;
case 52:
        gotMethod = true;
        java.util.HashMap getUsernames52mtemp = sampleChatServerProxyid.getUsernames();
if(getUsernames52mtemp == null){
%>
<%=getUsernames52mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 57:
        gotMethod = true;
        String id_10id=  request.getParameter("id60");
        int id_10idTemp  = Integer.parseInt(id_10id);
        String username_11id=  request.getParameter("username62");
            java.lang.String username_11idTemp = null;
        if(!username_11id.equals("")){
         username_11idTemp  = username_11id;
        }
        String receiverUsername_12id=  request.getParameter("receiverUsername64");
            java.lang.String receiverUsername_12idTemp = null;
        if(!receiverUsername_12id.equals("")){
         receiverUsername_12idTemp  = receiverUsername_12id;
        }
        String message_13id=  request.getParameter("message66");
            java.lang.String message_13idTemp = null;
        if(!message_13id.equals("")){
         message_13idTemp  = message_13id;
        }
        sampleChatServerProxyid.privateMsg(id_10idTemp,username_11idTemp,receiverUsername_12idTemp,message_13idTemp);
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>