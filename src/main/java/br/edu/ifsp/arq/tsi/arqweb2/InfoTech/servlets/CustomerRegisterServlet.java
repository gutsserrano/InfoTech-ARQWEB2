package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Customer;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.CustomerDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/customerRegister")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String cpf = req.getParameter("cpf");
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setCpf(cpf);
		
		RequestDispatcher dispatcher = null;
		
		CustomerDao customerDao = new CustomerDao(DataSourceSearcher.getInstance().getDataSource());
		
		if(customerDao.save(customer)) {
			req.setAttribute("result", "registered");
			dispatcher = req.getRequestDispatcher("/home.jsp");
		}else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("customer-register.jsp");
		}
		
		dispatcher.forward(req, resp);
	}

}
