package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.PaymentMethod;

public class PaymentMethodDao {

	private DataSource dataSource;
	
	public PaymentMethodDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<PaymentMethod> getAllPaymentMethods(){
		String sql = "SELECT \n"
					+ "	id,\n"
					+ "	name\n"
					+ "FROM paymentMethod";
		
		List<PaymentMethod> result  = new ArrayList<PaymentMethod>();
		
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					PaymentMethod paymentMethod = new PaymentMethod();
					paymentMethod.setId(rs.getInt(1));
					paymentMethod.setName(rs.getString(2));
					
					result.add(paymentMethod);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		
		return result;
	}
	
	public Optional<PaymentMethod> getPaymentMethodById(int id){
		String sql = "SELECT \n"
					+ "	id,\n"
					+ "	name\n"
					+ "FROM paymentMethod WHERE id=?\n";
		
		Optional<PaymentMethod> optional  =  Optional.empty();;
		
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					PaymentMethod paymentMethod = new PaymentMethod();
					paymentMethod.setId(rs.getInt(1));
					paymentMethod.setName(rs.getString(2));
					
					optional = Optional.of(paymentMethod);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		
		return optional;
	}
	
	public Boolean save(String paymentName){
		List<PaymentMethod> allPaymentMethods = getAllPaymentMethods();
		
		for (PaymentMethod paymentMethod : allPaymentMethods) {
			if(paymentMethod.getName().equalsIgnoreCase(paymentName)) {
				return false;
			}
		}
		
		String sql = "insert into paymentMethod (name) "
				+ "values (?)";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, paymentName);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}
		return true;
	}
}
