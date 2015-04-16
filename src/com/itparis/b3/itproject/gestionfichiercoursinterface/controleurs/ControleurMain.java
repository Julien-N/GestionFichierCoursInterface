package com.itparis.b3.itproject.gestionfichiercoursinterface.controleurs;

import java.io.IOException;
import java.io.PrintWriter;
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
		// récupération d'un descripteur d'écriture de flux
		PrintWriter out = response.getWriter();

		try {
			// initialisation de ma page suivante - par rapport à l'action en
			// cours
			String forward = "Accueil.jsp";
			// récupération de la source de la demande de requete
			String idaction = request.getParameter("action");

			// traitement de la requête selon la source
			if (idaction.equals("auth")) {
				forward = authentifier(request);
			} else if (idaction.equals("allfiles")) {
				forward = getallfiles(request);
			} else if (idaction.equals("addfile")) {
				forward = addfile(request);
			}else if (idaction.equals("detailcours")) {
				forward = detailscours(request);
			}
			// etc .......

			// à la fin du traitement après if else ... etc ... je dispatche
			// vers la page de réponse
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(forward);
			dispatcher.forward(request, response);

		} finally {
			out.close();
		}
	}

	private String detailscours(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//ajouter des paramètres 
		//oubien ajoiuter un objet cours -> le forward sera à la page.jsp
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
