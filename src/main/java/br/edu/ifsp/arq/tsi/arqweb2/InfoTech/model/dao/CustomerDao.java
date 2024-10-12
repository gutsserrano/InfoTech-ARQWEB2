package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Customer;

public class CustomerDao {

	private DataSource dataSource;
	
	public CustomerDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Optional<Customer> getCustomerByCpf(String cpf){
		String sql = "select id,name,email,phone,cpf from customer where cpf=?";
		Optional<Customer> optional = Optional.empty();
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, cpf);
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					Customer customer = new Customer();
					customer.setId(rs.getInt(1));
					customer.setName(rs.getString(2));
					customer.setEmail(rs.getString(3));
					customer.setPhone(rs.getString(4));
					customer.setCpf(rs.getString(5));
					optional = Optional.of(customer);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return optional;
	}
	
	public Boolean save(Customer customer){
		Optional<Customer> optional = getCustomerByCpf(customer.getCpf());
		if(optional.isPresent()) {
			return false;
		}
		String sql = "insert into customer (name, email, phone, cpf, active) "
				+ "values (?,?,?,?,?)";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPhone());
			ps.setString(4, customer.getCpf());
			ps.setBoolean(5, true);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}
		return true;
	}
}
