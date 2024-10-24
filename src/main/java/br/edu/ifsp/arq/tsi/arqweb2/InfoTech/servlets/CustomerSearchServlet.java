package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Customer;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.CustomerDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dto.CustomerDto;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/customerSearch")
public class CustomerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url;

		CustomerDao customerDao = new CustomerDao(DataSourceSearcher.getInstance().getDataSource());
		List<Customer> customers = customerDao.getAllCustomers();
		
		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		for (Customer customer : customers) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCpf(customer.getCpf());
			customerDto.setName(customer.getName());
					
			customersDto.add(customerDto);
		}
		
		req.setAttribute("customers", customersDto);

		url = "/paymentSearch";

		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
