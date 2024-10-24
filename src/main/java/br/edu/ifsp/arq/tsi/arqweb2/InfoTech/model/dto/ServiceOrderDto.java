package br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.dto;

import java.time.LocalDate;

import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.InfoTech.model.Status;

public class ServiceOrderDto {

	private int id;
	private String description;
	private String name;
	private String email;
	private String phone;
	private String cpf;
	private LocalDate issueDate;
	private LocalDate endDate;
	private float price;
	private String status;
	private String observation;
	private int paymentId;
	private String paymentMethodName;
	
	public ServiceOrderDto() {
		
	}
	
	public ServiceOrderDto(ServiceOrder serviceOrder) {
		this.id = serviceOrder.getId();
		this.description = serviceOrder.getDescription();
		this.name = serviceOrder.getCustomer().getName();
		this.email = serviceOrder.getCustomer().getEmail();
		this.phone = serviceOrder.getCustomer().getPhone();
		this.cpf = serviceOrder.getCustomer().getCpf();
		this.issueDate = serviceOrder.getIssueDate();
		this.endDate = serviceOrder.getEndDate();
		this.price = serviceOrder.getPrice();
		this.status = serviceOrder.getStatus().toString();
		this.observation = serviceOrder.getObservation();
		this.paymentId = serviceOrder.getPaymentMethod().getId();
		this.paymentMethodName = serviceOrder.getPaymentMethod().getName();
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

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}
	
}
