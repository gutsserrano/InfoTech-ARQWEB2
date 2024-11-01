package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Customer;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Status;

public class ServiceOrderDao {

	private DataSource dataSource;
	
	public ServiceOrderDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<ServiceOrder> getAllServicesOrders(){
		String sql = "SELECT \n"
					+ "	SO.id,\n"
					+ "	SO.description,\n"
					+ " C.id,\n"
					+ " C.name,\n"
					+ " C.email,\n"
					+ " C.phone,\n"
					+ " C.cpf,\n"
					+ "	SO.issueDate,\n"
					+ "	SO.endDate,\n"
					+ "	SO.price,\n"
					+ "	SO.observation,\n"
					+ "	SO.status,\n"
					+ "	PM.id,\n"
					+ "	PM.name\n"
					+ "FROM serviceOrder SO\n"
					+ "JOIN customer C ON C.id=SO.customerId\n"
					+ "JOIN paymentMethod PM ON PM.id=SO.paymentId\n"
					+ "ORDER BY SO.id ASC";
		
		List<ServiceOrder> result = new ArrayList<ServiceOrder>();

		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					ServiceOrder serviceOrder = new ServiceOrder();
					serviceOrder.setId(rs.getInt(1));
					serviceOrder.setDescription(rs.getString(2));
					serviceOrder.setIssueDate(LocalDate.parse(rs.getDate(8).toString()));
					serviceOrder.setPrice(rs.getFloat(10));
					serviceOrder.setObservation(rs.getString(11));
					serviceOrder.setStatus(Status.valueOf(rs.getString(12)));
					
					if(rs.getDate(9) != null) {							
						serviceOrder.setEndDate(LocalDate.parse(rs.getDate(9).toString()));
					}else {
						serviceOrder.setEndDate(null);
					}
					
					Customer customer = new Customer();
					customer.setId(rs.getInt(3));
					customer.setName(rs.getString(4));
					customer.setEmail(rs.getString(5));
					customer.setPhone(rs.getString(6));
					customer.setCpf(rs.getString(7));	
					serviceOrder.setCustomer(customer);
					
					PaymentMethod paymentMethod = new PaymentMethod();
					paymentMethod.setId(rs.getInt(13));
					paymentMethod.setName(rs.getString(14));
					serviceOrder.setPaymentMethod(paymentMethod);
					
					result.add(serviceOrder);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		
		return result;
	}
	
	public List<ServiceOrder> getServicesOrdersByCpf(String cpf){
		String sql = "SELECT \n"
					+ "	SO.id,\n"
					+ "	SO.description,\n"
					+ "	SO.issueDate,\n"
					+ "	SO.endDate,\n"
					+ "	SO.price,\n"
					+ "	SO.observation,\n"
					+ "	SO.status,\n"
					+ "	SO.paymentId\n"
					+ "FROM serviceOrder AS SO\n"
					+ "JOIN customer AS C ON C.id=SO.customerId\n"
					+ "where C.cpf=?";
		
		List<ServiceOrder> result = new ArrayList<ServiceOrder>();
		
		CustomerDao customerDao = new CustomerDao(dataSource);
		Optional<Customer> customer = customerDao.getCustomerByCpf(cpf);
		
		PaymentMethodDao paymentMethodDao = new PaymentMethodDao(dataSource);
		

		if(customer.isPresent()) {
			try(Connection conn = dataSource.getConnection(); 
					PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, cpf);
				try(ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						ServiceOrder serviceOrder = new ServiceOrder();
						serviceOrder.setId(rs.getInt(1));
						serviceOrder.setDescription(rs.getString(2));
						serviceOrder.setCustomer(customer.get());
						serviceOrder.setIssueDate(LocalDate.parse(rs.getDate(3).toString()));
						serviceOrder.setPrice(rs.getFloat(5));
						serviceOrder.setObservation(rs.getString(6));
						serviceOrder.setStatus(Status.valueOf(rs.getString(7)));
						
						if(rs.getDate(4) != null) {							
							serviceOrder.setEndDate(LocalDate.parse(rs.getDate(4).toString()));
						}else {
							serviceOrder.setEndDate(null);
						}
						
						Optional<PaymentMethod> paymentMethod = paymentMethodDao.getPaymentMethodById(rs.getInt(8));
						serviceOrder.setPaymentMethod(paymentMethod.get());
						
						result.add(serviceOrder);
					}
				}
			}catch (SQLException e) {
				throw new RuntimeException("Erro durante a consulta no BD", e);
			}
		}
		return result;
	}
	
	public Optional<ServiceOrder> getServiceOrderById(int id){
		String sql = "SELECT \n"
					+ "	SO.id,\n"
					+ "	SO.description,\n"
					+ " C.id,\n"
					+ " C.name,\n"
					+ " C.email,\n"
					+ " C.phone,\n"
					+ " C.cpf,\n"
					+ "	SO.issueDate,\n"
					+ "	SO.endDate,\n"
					+ "	SO.price,\n"
					+ "	SO.observation,\n"
					+ "	SO.status,\n"
					+ "	PM.id,\n"
					+ "	PM.name\n"
					+ "FROM serviceOrder SO\n"
					+ "JOIN customer C ON C.id=SO.customerId\n"
					+ "JOIN paymentMethod PM ON PM.id=SO.paymentId\n"
					+ "WHERE SO.id=?\n"
					+ "ORDER BY SO.id ASC";
		
		Optional<ServiceOrder> result = Optional.empty();

		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					ServiceOrder serviceOrder = new ServiceOrder();
					serviceOrder.setId(rs.getInt(1));
					serviceOrder.setDescription(rs.getString(2));
					serviceOrder.setIssueDate(LocalDate.parse(rs.getDate(8).toString()));
					serviceOrder.setPrice(rs.getFloat(10));
					serviceOrder.setObservation(rs.getString(11));
					serviceOrder.setStatus(Status.valueOf(rs.getString(12)));
					
					if(rs.getDate(9) != null) {							
						serviceOrder.setEndDate(LocalDate.parse(rs.getDate(9).toString()));
					}else {
						serviceOrder.setEndDate(null);
					}
					
					Customer customer = new Customer();
					customer.setId(rs.getInt(3));
					customer.setName(rs.getString(4));
					customer.setEmail(rs.getString(5));
					customer.setPhone(rs.getString(6));
					customer.setCpf(rs.getString(7));	
					serviceOrder.setCustomer(customer);
					
					PaymentMethod paymentMethod = new PaymentMethod();
					paymentMethod.setId(rs.getInt(13));
					paymentMethod.setName(rs.getString(14));
					serviceOrder.setPaymentMethod(paymentMethod);
					
					result = Optional.of(serviceOrder);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		
		return result;
	}

	
	public Boolean save(ServiceOrder serviceOrder){
		if(serviceOrder.getCustomer() == null) {
			return false;
		}
		String sql = "insert into serviceOrder (customerId, description, issueDate, endDate, price, observation, status, paymentId) "
				+ "values (?,?,?,?,?,?,?,?)";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, serviceOrder.getCustomer().getId());
			ps.setString(2, serviceOrder.getDescription());
			ps.setDate(3, Date.valueOf(serviceOrder.getIssueDate()));
			ps.setFloat(5, serviceOrder.getPrice());
			ps.setString(6, serviceOrder.getObservation());
			ps.setString(7, serviceOrder.getStatus().toString());
			ps.setInt(8, serviceOrder.getPaymentMethod().getId());
			
			if(serviceOrder.getEndDate() != null) {				
				ps.setDate(4, Date.valueOf(serviceOrder.getEndDate()));
			}else {
				ps.setDate(4, null);
			}
			
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}
		return true;
	}
	
	public Boolean delete(int id) {
		String sql = "delete from serviceOrder where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao remover dados", sqlException);
		}
	}
	
	public Boolean update(ServiceOrder serviceOrder) {
		String sql = "update serviceOrder set " + "description=?," + "price=?," + "observation=?"
				+ " where id=?";
		
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, serviceOrder.getDescription());
			ps.setFloat(2, serviceOrder.getPrice());
			ps.setString(3, serviceOrder.getObservation());
			ps.setInt(4, serviceOrder.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao atualizar dados", sqlException);
		}
	}
	
	public Boolean updateStatus(int id, Status status) {
		String sql = "update serviceOrder set " + "status=?," + "endDate=?"
				+ " where id=?";
		
		Date date = null;
		
		if(status.equals(Status.FINISHED)) {
			date = Date.valueOf(LocalDate.now());
		}
		
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, status.toString());
			ps.setDate(2, date);
			ps.setInt(3, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao atualizar dados", sqlException);
		}
	}
}
