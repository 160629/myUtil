<%@ page language="java" contentType="text/html;charset=UTF-8"
import="javax.mail.*,mail.*"
import="send.receiver.POP3Help"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

      String from = "";
      String subject = "";    
      Folder folder = POP3Help.getFolder();
      session.setAttribute("folder",folder);
      Message [] messages = folder.getMessages();
      
      for(int i=0;i<messages.length;i++)
      {
          try
          {
            from = messages[i].getFrom()[0].toString();
            subject = messages[i].getSubject();
            out.print(i + 1);
%>
            发件人地址：<%=from %>  邮件主题：<%=subject %>                
            <a href="displayMsg.jsp?msgnum=<%=i+1%>">查看邮件</a><br/>
<%
        }
            catch(Exception e){}
          }
%>
</body>
</html>