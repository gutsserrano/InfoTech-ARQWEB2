package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Address;
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
		String password = PasswordEncoder.encode(req.getParameter("password"));
		String phone = req.getParameter("phone");
		String cpf = req.getParameter("cpf");
		
		String cep = req.getParameter("cep");
		String street = req.getParameter("street");
		String number = req.getParameter("number");
		String neighborhood = req.getParameter("neighborhood");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String complement = req.getParameter("complement");
		String role = req.getParameter("role");
		
		Address address = new Address();
		address.setZipCode(cep);
		address.setStreet(street);
		address.setNumber(number);
		address.setNeighborhood(neighborhood);
		address.setCity(city);
		address.setState(state);
		address.setComplement(complement);
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setPhone(phone);
		customer.setCpf(cpf);
		customer.setAddress(address);
		customer.setRole(role);
		
		RequestDispatcher dispatcher = null;
		
		CustomerDao customerDao = new CustomerDao(DataSourceSearcher.getInstance().getDataSource());
		
		if(customerDao.save(customer)) {
			req.setAttribute("result", "registered");
			dispatcher = req.getRequestDispatcher("/serviceOrderSearch");
		}else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("customer-register.jsp");
		}
		
		dispatcher.forward(req, resp);
	}

}
