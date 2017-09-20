<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<frameset rows="25%,*">
     <frame src="/myUtil/DisplayHead?msgnum=<%=request.getParameter("msgnum")%>" scrolling="no">
     <frame src="/myUtil/DisplayContent?msgnum=<%=request.getParameter("msgnum")%>" scrolling="no">
</frameset>
</body>
</html>