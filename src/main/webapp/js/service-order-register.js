const botao = document.getElementById("saveButton");

botao.addEventListener("click", function() {
    const cpfHidden = document.getElementById("cpfHidden").value;
	const cpf = cpfHidden.substring(cpfHidden.indexOf('-') + 1).trim();
	
  	document.getElementById("cpf").value = cpf;
  	
  	const methodSelect = document.getElementById("methodSelect").value;
	const methodId = methodSelect.substring(0, methodSelect.indexOf('-')).trim();
	
  	document.getElementById("paymentMethod").value = methodId;
  	
  	document.getElementById("serviceOrderForm").submit();
});