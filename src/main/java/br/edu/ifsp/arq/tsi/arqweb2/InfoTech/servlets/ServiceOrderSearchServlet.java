package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dto.ServiceOrderDto;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ServiceOrderSearchServlet
 */
@WebServlet("/serviceOrderSearch")
public class ServiceOrderSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceOrderSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String cpf = req.getParameter("cpf");
			
		ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
		List<ServiceOrderDto> serviceOrders = new ArrayList<>();
		
		if(cpf == null || cpf.isEmpty()) {				
			for (var item : serviceOrderDao.getAllServicesOrders()) {
				serviceOrders.add(new ServiceOrderDto(item));
			}
		}else {
			for (var item : serviceOrderDao.getServicesOrdersByCpf(cpf)){
				serviceOrders.add(new ServiceOrderDto(item));
			}
		}
		
		req.setAttribute("serviceOrders", serviceOrders);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
