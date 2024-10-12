package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dto;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.ServiceOrder;

public class ServiceOrderDto {

	private int id;
	private String description;
	private String name;
	private String email;
	private String phone;
	private String cpf;
	
	public ServiceOrderDto() {
		
	}
	
	public ServiceOrderDto(ServiceOrder serviceOrder) {
		this.id = serviceOrder.getId();
		this.description = serviceOrder.getDescription();
		this.name = serviceOrder.getCustomer().getName();
		this.email = serviceOrder.getCustomer().getEmail();
		this.phone = serviceOrder.getCustomer().getPhone();
		this.cpf = serviceOrder.getCustomer().getCpf();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
