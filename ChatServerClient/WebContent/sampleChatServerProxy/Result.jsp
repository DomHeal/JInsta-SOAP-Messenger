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
        int join19mtemp = sampleChatServerProxyid.join(id_1idTemp,username_2idTemp);
        String tempResultreturnp20 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(join19mtemp));
        %>
        <%= tempResultreturnp20 %>
        <%
break;
case 26:
        gotMethod = true;
        String id_3id=  request.getParameter("id29");
        int id_3idTemp  = Integer.parseInt(id_3id);
        java.lang.String listen26mtemp = sampleChatServerProxyid.listen(id_3idTemp);
if(listen26mtemp == null){
%>
<%=listen26mtemp %>
<%
}else{
        String tempResultreturnp27 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(listen26mtemp));
        %>
        <%= tempResultreturnp27 %>
        <%
}
break;
case 31:
        gotMethod = true;
        String id_4id=  request.getParameter("id34");
        int id_4idTemp  = Integer.parseInt(id_4id);
        String username_5id=  request.getParameter("username36");
            java.lang.String username_5idTemp = null;
        if(!username_5id.equals("")){
         username_5idTemp  = username_5id;
        }
        sampleChatServerProxyid.leave(id_4idTemp,username_5idTemp);
break;
case 38:
        gotMethod = true;
        int getUserCount38mtemp = sampleChatServerProxyid.getUserCount();
        String tempResultreturnp39 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getUserCount38mtemp));
        %>
        <%= tempResultreturnp39 %>
        <%
break;
case 41:
        gotMethod = true;
        java.util.HashMap getUsernames41mtemp = sampleChatServerProxyid.getUsernames();
if(getUsernames41mtemp == null){
%>
<%=getUsernames41mtemp %>
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
case 46:
        gotMethod = true;
        String id_6id=  request.getParameter("id49");
        int id_6idTemp  = Integer.parseInt(id_6id);
        String username_7id=  request.getParameter("username51");
            java.lang.String username_7idTemp = null;
        if(!username_7id.equals("")){
         username_7idTemp  = username_7id;
        }
        String receiverUsername_8id=  request.getParameter("receiverUsername53");
            java.lang.String receiverUsername_8idTemp = null;
        if(!receiverUsername_8id.equals("")){
         receiverUsername_8idTemp  = receiverUsername_8id;
        }
        String message_9id=  request.getParameter("message55");
            java.lang.String message_9idTemp = null;
        if(!message_9id.equals("")){
         message_9idTemp  = message_9id;
        }
        sampleChatServerProxyid.privateMsg(id_6idTemp,username_7idTemp,receiverUsername_8idTemp,message_9idTemp);
break;
case 57:
        gotMethod = true;
        String id_10id=  request.getParameter("id60");
        int id_10idTemp  = Integer.parseInt(id_10id);
        String message_11id=  request.getParameter("message62");
            java.lang.String message_11idTemp = null;
        if(!message_11id.equals("")){
         message_11idTemp  = message_11id;
        }
        sampleChatServerProxyid.talk(id_10idTemp,message_11idTemp);
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