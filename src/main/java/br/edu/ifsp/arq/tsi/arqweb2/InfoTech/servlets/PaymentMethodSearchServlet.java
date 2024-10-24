package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentMethodSearchServlet
 */
@WebServlet("/paymentSearch")
public class PaymentMethodSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentMethodSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String url = "/service-order-register.jsp";
    	
    	PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
    	
    	List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethods();
    	
    	req.setAttribute("paymentMethods", paymentMethods);
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
