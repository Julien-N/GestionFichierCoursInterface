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


<% if (User.getTypeUser().equals("user")){  %>
      <p>Vous �tes �l�ve</p>
      
      
      
      
      
      
<% } else if (User.getTypeUser().equals("admin")) { %>
      <p>Vous �tes admin</p><br>
      <p>Gestion des utilisateurs</p><br>
      <hr>
      <h4>Ajouter un utilisateur</h4><br>
      <form  name="id" class="form" action="ControleurMain?action=adduser" method="POST" >
            <table>
                   <input type="hidden" name="iduser" value = null id="id" />
                    <tr>
                        <td width="120px">name : </td>
                        <td><input type="text" name="name" id="login" /></td>
                    </tr>

                    <tr>
                        <td width="120px"> Mot de passe : </td>
                        <td><input type="text" name="pass" value="" id="pass"/></td>
                    </tr>
                    <tr>
                        <td width="120px"> Type : </td>
                        <td><select name = "typeuser">
                        <option value = "admin">admin
                        <option value = "prof" >prof
                        <option value = "user">�l�ve
                        </select></td>
                    </tr>
                    
                    <tr>
                        <td width="120px" ><input type="submit" value="Valider" class="submit"/></td>
                        <td width="120px" ><input type="reset" value="Annuler" class="submit" /></td>
                    </tr>
            </table>
        </form>
       
        <hr>
      <h4>Modifier un utilisateur</h4><br>
      <form  name="id" class="form" action="ControleurMain?action=UpdateUser" method="POST" >
            <table>
                     <tr>
                        <td width="120px">name : </td>
                       <input type="number" name="iduser" value = null id="id" />
                    </tr>
                   
                    <tr>
                        <td width="120px">name : </td>
                        <td><input type="text" name="name" id="login" /></td>
                    </tr>

                    <tr>
                        <td width="120px"> Mot de passe : </td>
                        <td><input type="text" name="pass" value="" id="pass"/></td>
                    </tr>
                    <tr>
                        <td width="120px"> Type : </td>
                        <td><select name = "typeuser">
                        <option value = "admin">admin
                        <option value = "prof" >prof
                        <option value = "user">�l�ve
                        </select></td>
                    </tr>
                    
                    <tr>
                        <td width="120px" ><input type="submit" value="Valider" class="submit"/></td>
                        <td width="120px" ><input type="reset" value="Annuler" class="submit" /></td>
                    </tr>
            </table>
        </form>
      
      
      
      
      
      
<% } %>

</body>
</html>
