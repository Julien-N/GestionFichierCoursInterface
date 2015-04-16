<%@page import="com.itparis.b3.poo.beans.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session ="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
</head>
<body>
<h1>Bienvenue <%= User.getNomUser() %></h1>

<% if (session.getAttribute("role").equals("eleve")){  %>
      <p>Vous êtes élève WESH</p>
      
<% } else if (session.getAttribute("role").equals("admin")) { %>
      <p>Vous êtes admin, cher monsieur ou chère madame</p>
<% } else if (session.getAttribute("role").equals("prof")) { %>
	  <p>Yo le prof!</p>
<% } %>


<form action="ControleurMain?action=allfiles" method ="POST">

<td width="120px" ><input type="submit" value="Récupérer les infos d'un cours" class="submit"/></td>

</form>



</body>
</html>
