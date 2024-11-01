const botao = document.getElementById("saveButton");

botao.addEventListener("click", function() {
    const cpfHidden = document.getElementById("cpfHidden").value;
	const cpf = cpfHidden.substring(cpfHidden.indexOf('-') + 1).trim();
	
  	document.getElementById("cpf").value = cpf;
  	
  	const methodSelect = document.getElementById("methodSelect").value;
	const methodId = methodSelect.substring(0, methodSelect.indexOf('-')).trim();
	
  	document.getElementById("paymentMethod").value = methodId;
  	
  	verifyPrice();
  	
  	if(verifyDates()){
	  	document.getElementById("serviceOrderForm").submit();  
 	}else{
		 const issueDate = Date.parse(document.getElementById("issueDate").value);
		 
		 issueDate.setCustomValidity('a data de emissão deve vir antes');
	 }
});

function verifyPrice(){
	let price = document.getElementById("price").value;
	
	price = price.replace(/[a-zA-Z\s]/, "0");
  	
  	if(price < 0){
	  document.getElementById("price").value = "0";
	}
}

function verifyDates(){
	const issueDate = Date.parse(document.getElementById("issueDate").value);
	const endDate = Date.parse(document.getElementById("endDate").value);
	
	if(issueDate < endDate){
		return true;
	}else{
		return false;
	}
}