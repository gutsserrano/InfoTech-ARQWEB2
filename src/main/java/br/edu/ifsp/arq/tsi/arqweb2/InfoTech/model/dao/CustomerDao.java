package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Address;
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
	
	public List<Customer> getAllCustomers(){
		String sql = "SELECT " +
						 "c.id," +
					     "c.name, " +
					     "c.email," +
					     "c.phone, " +
					     "c.cpf, " +
					     "c.addressId, " +
					     "a.street, " +
					     "a.number, " +
					     "a.complement, " +
					     "a.neighborhood, " +
					     "a.zipCode, " +
					     "a.city, " +
					     "a.state " +
					"FROM customer c " +
					"JOIN address a ON a.id=c.addressId;";
		List<Customer> customers = new ArrayList<Customer>();
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					Address address = new Address();
					address.setId(rs.getInt(6));
					address.setStreet(rs.getString(7));
					address.setNumber(rs.getString(8));
					address.setComplement(rs.getString(9));
					address.setNeighborhood(rs.getString(10));
					address.setZipCode(rs.getString(11));
					address.setCity(rs.getString(12));
					address.setState(rs.getString(13));
					
					Customer customer = new Customer();
					customer.setId(rs.getInt(1));
					customer.setName(rs.getString(2));
					customer.setEmail(rs.getString(3));
					customer.setPhone(rs.getString(4));
					customer.setCpf(rs.getString(5));
					customer.setAddress(address);
					
					customers.add(customer);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return customers;
	}
	
	public Boolean save(Customer customer){
		Optional<Customer> optional = getCustomerByCpf(customer.getCpf());
		if(optional.isPresent()) {
			return false;
		}
		
		saveAddress(customer.getAddress());
		String sql = "insert into customer (name, email, phone, cpf, active, addressId) \n"
				   + "values (?,?,?,?,?,LAST_INSERT_ID());";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPhone());
			ps.setString(4, customer.getCpf());
			ps.setBoolean(5, true);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD, Customer", e);
		}
		return true;
	}
	
	private Boolean saveAddress(Address address){
		String sql = "insert into address (street, number, complement, neighborhood, zipCode, city, state) \n"
				   + "values (?,?,?,?,?,?,?); ";
		Optional<Integer> optional = Optional.empty();
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, address.getStreet());
			ps.setString(2, address.getNumber());
			ps.setString(3, address.getComplement());
			ps.setString(4, address.getNeighborhood());
			ps.setString(5, address.getZipCode());
			ps.setString(6, address.getCity());
			ps.setString(7, address.getState());
			
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD, Address", e);
		}
		return true;
	}
}
