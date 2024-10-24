package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Customer;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.CustomerDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServiceorderRegisterServlet
 */
@WebServlet("/serviceOrderRegister")
public class ServiceOrderRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceOrderRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		String desc = req.getParameter("description");
		LocalDate issueDate = LocalDate.parse(req.getParameter("issueDate"));
		LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));
		float price = Float.parseFloat(req.getParameter("price"));
		int paymentId = Integer.parseInt(req.getParameter("paymentMethod"));
		String observation = req.getParameter("observation");
		
		
		DataSource datasource = DataSourceSearcher.getInstance().getDataSource();
		CustomerDao customerDao = new CustomerDao(datasource);
		Optional<Customer> customer = customerDao.getCustomerByCpf(cpf);
		
		PaymentMethodDao paymentMethodDao = new PaymentMethodDao(datasource);
		Optional<PaymentMethod> paymentMethod = paymentMethodDao.getPaymentMethodById(paymentId);
		
		RequestDispatcher dispatcher = null;
		
		if(!customer.isPresent()) {
			req.setAttribute("result", "customerNotFound");
			dispatcher = req.getRequestDispatcher("service-order-register.jsp");
		}else {
			if(!paymentMethod.isPresent()) {
				req.setAttribute("result", "paymentMethodNotFound");
				dispatcher = req.getRequestDispatcher("service-order-register.jsp");
			}else {
				ServiceOrder serviceOrder = new ServiceOrder();
				serviceOrder.setCustomer(customer.get());
				serviceOrder.setDescription(desc);
				serviceOrder.setIssueDate(issueDate);
				serviceOrder.setEndDate(endDate);
				serviceOrder.setPrice(price);
				serviceOrder.setPaymentMethod(paymentMethod.get());
				serviceOrder.setStatus(Status.APPROVED);
				serviceOrder.setObservation(observation);
				
				ServiceOrderDao serviceOrderDao = new ServiceOrderDao(datasource);
				
				if(serviceOrderDao.save(serviceOrder)) {
					req.setAttribute("result", "registered");
					dispatcher = req.getRequestDispatcher("/home.jsp");
				}else {
					req.setAttribute("result", "notRegistered");
					dispatcher = req.getRequestDispatcher("service-order-register.jsp");
				}
			}
		}
		
		dispatcher.forward(req, resp);
	}

}
