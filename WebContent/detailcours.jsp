<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Informations du cours s�lectionn�</h1>
	<%-- unClient est un attribut de requ�te --%>
	<jsp:useBean id="unCours" class="com.itparis.b3.poo.beans.Cours"
		scope="request" />
	<b>Matiere : </b>
	<%=unCours.getIdMatiere()%>
	<br />
	<b>Nom : </b>
	<%=unCours.getNomCours()%>
	<br />
	<b>Date : </b>
	<%=unCours.getDateCours()%>
	<br />
</body>
</html>