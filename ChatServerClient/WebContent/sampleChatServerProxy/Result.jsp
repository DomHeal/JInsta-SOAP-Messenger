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
        String username_4id=  request.getParameter("username34");
            java.lang.String username_4idTemp = null;
        if(!username_4id.equals("")){
         username_4idTemp  = username_4id;
        }
        sampleChatServerProxyid.leave(username_4idTemp);
break;
case 36:
        gotMethod = true;
        String id_5id=  request.getParameter("id39");
        int id_5idTemp  = Integer.parseInt(id_5id);
        String message_6id=  request.getParameter("message41");
            java.lang.String message_6idTemp = null;
        if(!message_6id.equals("")){
         message_6idTemp  = message_6id;
        }
        sampleChatServerProxyid.talk(id_5idTemp,message_6idTemp);
break;
case 43:
        gotMethod = true;
        int getUserCount43mtemp = sampleChatServerProxyid.getUserCount();
        String tempResultreturnp44 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getUserCount43mtemp));
        %>
        <%= tempResultreturnp44 %>
        <%
break;
case 46:
        gotMethod = true;
        java.util.HashMap getUsernames46mtemp = sampleChatServerProxyid.getUsernames();
if(getUsernames46mtemp == null){
%>
<%=getUsernames46mtemp %>
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
case 51:
        gotMethod = true;
        String id_7id=  request.getParameter("id54");
        int id_7idTemp  = Integer.parseInt(id_7id);
        String username_8id=  request.getParameter("username56");
            java.lang.String username_8idTemp = null;
        if(!username_8id.equals("")){
         username_8idTemp  = username_8id;
        }
        String receiverUsername_9id=  request.getParameter("receiverUsername58");
            java.lang.String receiverUsername_9idTemp = null;
        if(!receiverUsername_9id.equals("")){
         receiverUsername_9idTemp  = receiverUsername_9id;
        }
        String message_10id=  request.getParameter("message60");
            java.lang.String message_10idTemp = null;
        if(!message_10id.equals("")){
         message_10idTemp  = message_10id;
        }
        sampleChatServerProxyid.privateMsg(id_7idTemp,username_8idTemp,receiverUsername_9idTemp,message_10idTemp);
break;
case 62:
        gotMethod = true;
        String username_11id=  request.getParameter("username65");
            java.lang.String username_11idTemp = null;
        if(!username_11id.equals("")){
         username_11idTemp  = username_11id;
        }
        sampleChatServerProxyid.grantAdmin(username_11idTemp);
break;
case 67:
        gotMethod = true;
        String username_12id=  request.getParameter("username70");
            java.lang.String username_12idTemp = null;
        if(!username_12id.equals("")){
         username_12idTemp  = username_12id;
        }
        boolean isAdmin67mtemp = sampleChatServerProxyid.isAdmin(username_12idTemp);
        String tempResultreturnp68 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(isAdmin67mtemp));
        %>
        <%= tempResultreturnp68 %>
        <%
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