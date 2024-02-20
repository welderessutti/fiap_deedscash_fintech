package br.com.deedscash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.UsuarioDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.factory.DAOFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;

    public LoginServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getUsuarioDAO();
    }

// GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("index.jsp");
	}

// POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			HttpSession session = request.getSession();

			if (session != null) {
				session.invalidate();
			}

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			Usuario validarUsuario = new Usuario(email, senha);

			if (dao.validarUsuario(validarUsuario)) {

				Usuario usuarioSessao = dao.buscarPorEmail(email);
				session = request.getSession();
				session.setAttribute("usuarioSessao", usuarioSessao);

				response.sendRedirect(request.getContextPath() + "/main");

			} else {

				request.setAttribute("erro", "Usuário ou senha inválidos");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		} catch (DBException db) {

			db.printStackTrace();
			request.setAttribute("erro", "Ocorreu um erro na validação");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
