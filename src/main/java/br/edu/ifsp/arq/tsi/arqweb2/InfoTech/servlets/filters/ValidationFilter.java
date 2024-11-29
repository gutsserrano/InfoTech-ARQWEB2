package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.servlets.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/customerSearch","/paymentMethodRegister",
		"/paymentSearch","/serviceOrderRegister","/serviceOrderSearch", "/home.jsp"
		,"/navbar.jsp","/payment-method-register.jsp", "/service-order-register.jsp"}, 
		filterName = "Authorization")
public class ValidationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession(false);
		if(session == null || session.getAttribute("customer") == null) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.sendRedirect(httpRequest.getContextPath()+
					"/login.jsp");
		}
		else {
			chain.doFilter(request, response);
		}	
	}

}
