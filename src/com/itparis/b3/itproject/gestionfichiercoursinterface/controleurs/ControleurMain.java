package com.itparis.b3.itproject.gestionfichiercoursinterface.controleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itparis.b3.poo.beans.Fichier;
import com.itparis.b3.poo.beans.User;
import com.itparis.b3.poo.dao.FichierDAO;
import com.itparis.b3.poo.dao.UserDAO;

/**
 * Servlet implementation class ControleurMain
 */
@WebServlet("/ControleurMain")
public class ControleurMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static ResourceBundle resource = ResourceBundle.getBundle("appli");

	/**
	 * Default constructor.
	 */
	public ControleurMain() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// initialisation des attributs
		request.setAttribute("url", null);
		response.setContentType("text/html;charset=UTF-8");
		// r閏up閞ation d'un descripteur d'閏riture de flux
		PrintWriter out = response.getWriter();

		try {
			// initialisation de ma page suivante - par rapport � l'action en
			// cours
			String forward = "Accueil.jsp";
			// r閏up閞ation de la source de la demande de requete
			String idaction = request.getParameter("action");

			// traitement de la requ阾e selon la source
			if (idaction.equals("auth")) {
				forward = authentifier(request);
			} else if (idaction.equals("allfiles")) {
				forward = getallfiles(request);
			} else if (idaction.equals("addfile")) {
				forward = addfile(request);
			}else if (idaction.equals("detailcours")) {
				forward = detailscours(request);
			}else if (idaction.equals("adduser")){
				forward = adduser(request);
			}else if (idaction.equals("updateUser")){
				forward = updateUser(request);
			}
			else if (idaction.equals("supprimerUser")){
				forward = supprimerUser(request);
			}
			// etc .......

			// � la fin du traitement apr鑣 if else ... etc ... je dispatche
			// vers la page de r閜onse
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(forward);
			dispatcher.forward(request, response);

		} finally {
			out.close();
		}
	}

	private String supprimerUser(HttpServletRequest request) {
		String forward;
		String iduser = request.getParameter("iduser");
		int id = Integer.parseInt(iduser);
		String name = request.getParameter("name");
		try{
			UserDAO.deleteUserById(id,name);
			forward = "passer.jsp";
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			forward = "Error.jsp";
		}
		return forward;
	}

	private String updateUser(HttpServletRequest request) {
		String forward;
		String iduser = request.getParameter("iduser");
		int id = Integer.parseInt(iduser);
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String type = request.getParameter("typeuser");
		
		try{
			User usertemp = new User(UserDAO.updateUser(id, name, pass,type));
			System.out.println(usertemp.getNomUser());
			forward = "passer.jsp";
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			forward = "Error.jsp";
		}
		
		
	
		return forward;
		
	}

	private String adduser(HttpServletRequest request) {
		String forward;
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String type = request.getParameter("typeuser");
		User usertemp;
		try {
			usertemp = new User(UserDAO.addUser(name, pass, type));
			forward = "passer.jsp";
			System.out.println(usertemp.getNomUser());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			forward = "Error.jsp";
		}
		
		return forward;
	}

	private String detailscours(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//ajouter des param鑤res 
		//oubien ajoiuter un objet cours -> le forward sera � la page.jsp
		return null;
	}

	private String addfile(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getallfiles(HttpServletRequest request) {
		String forward;
		ArrayList<Fichier> fileList = FichierDAO.getAllFichier();
		if(fileList !=null){
			forward = "ListeFichier.jsp";
		}
		else{
			forward = "ErreurFichiers.jsp";
		}
		
		return forward;
	}

	private String authentifier(HttpServletRequest request) {
		String forward;
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		//UserDAO myUserDAO = new UserDAO();
		System.out.println(login);
		System.out.println(pass);
		//User user = myUserDAO.getUserById(105);
		new User(UserDAO.getUserByNameAndPassword(login, pass));
		System.out.println("");
		System.out.println(User.getIdUser());
		System.out.println(User.getNomUser());
		System.out.println(User.getPasswordUser());
		System.out.println(User.getTypeUser());
		
			if ((login.equals(User.getNomUser())) && (pass.equals(User.getPasswordUser()))){
			forward = "Accueil.jsp";
			HttpSession session = request.getSession();
			if (User.isAdmin()) {
				session.setAttribute("role", "admin");
			} else if(User.isProf()){
				session.setAttribute("role", "prof");
			}else{
				session.setAttribute("role", "eleve");
			}
		} else {
			//a changer selon votre plan d'application ou CDC
			forward = "Error.jsp";
//			request.setAttribute("erreur", resource.getString("auth.erreur"));
		}
		return forward;
	}

}
