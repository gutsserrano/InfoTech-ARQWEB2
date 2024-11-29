package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets;

import java.io.IOException;
import java.util.Optional;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Customer;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.CustomerDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.utils.PasswordEncoder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = PasswordEncoder.encode(req.getParameter("password"));
		
		String url;
		
		CustomerDao customerDao = new CustomerDao(DataSourceSearcher.getInstance().getDataSource());
		
		Optional<Customer> optional = customerDao.getUserByEmailAndPassword(email, password);
		if(optional.isPresent()) {
			Customer customer = optional.get();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("customer", customer);
			url = "/serviceOrderSearch";
		}else {
			req.setAttribute("result", "loginError");
			url = "/login.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
