<%@page import="com.itparis.b3.poo.beans.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
</head>
<body>
<h1>Bienvenue <%= User.getNomUser() %></h1>


<% if (User.getTypeUser().equals("eleve")){  %>
      <p>Vous êtes élève</p>
      
      
      
      
      
      
<% } else if (User.getTypeUser().equals("admin")) { %>
      <p>Vous êtes admin, si si</p>
      
      
      
      
      
      
<% } %>

</body>
</html>
