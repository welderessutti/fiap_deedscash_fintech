package br.com.deedscash.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public LoginFilter() {
        super();
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		
		if (session.getAttribute("usuarioSessao") == null && 
				!url.endsWith("DeedsCashFintech/") && !url.endsWith("login") && 
				!url.contains("usuario") && !url.endsWith("index.jsp") && 
				!url.endsWith("signup.jsp") && !url.contains("resources") && 
				!url.contains("home"))
		{
			request.setAttribute("erro", "Entre com o usuário e senha!");
			request.getRequestDispatcher("index.jsp").forward(req, response);
		
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
