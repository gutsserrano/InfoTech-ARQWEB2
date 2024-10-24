const botao = document.getElementById("saveButton");

botao.addEventListener("click", function() {
    const string = document.getElementById("cpfHidden").value;
	const cpf = string.substring(string.indexOf('-') + 1).trim();
	
  	document.getElementById("cpf").value = cpf;
  	document.getElementById("serviceOrderForm").submit();
});
