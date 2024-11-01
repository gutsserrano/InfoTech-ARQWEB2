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
		int id = Integer.parseInt(req.getParameter("id"));
		String cpf = req.getParameter("cpf");
		String desc = req.getParameter("description");
		float price = Float.parseFloat(req.getParameter("price"));
		int paymentId = Integer.parseInt(req.getParameter("paymentMethod"));
		String observation = req.getParameter("observation");
		LocalDate issueDate = LocalDate.now();
		
		
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
				serviceOrder.setPrice(price);
				serviceOrder.setPaymentMethod(paymentMethod.get());
				serviceOrder.setStatus(Status.IN_APPROVAL);
				serviceOrder.setObservation(observation);
				
				ServiceOrderDao serviceOrderDao = new ServiceOrderDao(datasource);
				
				if(id == 0) {
					if(serviceOrderDao.save(serviceOrder)) {
						req.setAttribute("result", "registered");
						dispatcher = req.getRequestDispatcher("/serviceOrderSearch");
					}else {
						req.setAttribute("result", "notRegistered");
						dispatcher = req.getRequestDispatcher("service-order-register.jsp");
					}
				}else {
					serviceOrder.setId(id);
					if(serviceOrderDao.update(serviceOrder)) {
						dispatcher = req.getRequestDispatcher("/serviceOrderSearch");
					}else {
						req.setAttribute("result", "notRegistered");
						dispatcher = req.getRequestDispatcher("service-order-register.jsp");
					}
				}
			}
		}
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		int id = Integer.parseInt(req.getParameter("service-order-id"));
		String status = req.getParameter("newStatus");
		String url = null;

		ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
		Optional<ServiceOrder> optional = serviceOrderDao.getServiceOrderById(id);
		
		RequestDispatcher dispatcher = null;
		
		if(!optional.isEmpty()) {
			if(action.equals("update")) {
				req.setAttribute("serviceOrder", optional.get());
				url = "/service-order-register.jsp";
				dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, resp);
			}
			if(action.equals("remove")) {
				Boolean response = serviceOrderDao.delete(id);
				resp.setContentType("application/json");
				resp.getWriter().write(response.toString());
			}
			if(action.equals("finish")){
				Boolean response = serviceOrderDao.updateStatus(id, Status.valueOf(status));
				resp.setContentType("application/json");
				resp.getWriter().write(response.toString());
			}
		}
		
		url = "/serviceOrderSearch";
		dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
}
