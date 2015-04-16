<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
   response.setHeader("Refresh", "5;URL=Accueil.jsp");
%>
<body>  
  Action succèss, vous allez revenir à l'accueil dans <span id=jump>5</span> secondes
  <script>
       function mskip(abc){
           jump.innerText=abc;
           if(--abc > 0)
              setTimeout("mskip(" + abc + ")",1000);
           }
       mskip(5);
  </script>        
</body>
</html>