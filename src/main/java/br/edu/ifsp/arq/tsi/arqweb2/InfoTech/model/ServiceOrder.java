package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model;

import java.util.Objects;

public class ServiceOrder {

	private int id;
	private Customer customer;
	private String description;
	
	public ServiceOrder() {
		
	}
	
	public ServiceOrder(int id, Customer customer, String description) {
		super();
		this.id = id;
		this.customer = customer;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrder other = (ServiceOrder) obj;
		return Objects.equals(id, other.id);
	}
}
