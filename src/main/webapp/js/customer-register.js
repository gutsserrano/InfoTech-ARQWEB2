window.onload = initPage;

function initPage(){
	let form;
	form = document.getElementById('form1');
	// desativar a validação automática do HTML5
	form.noValidate = true;
	form.addEventListener('submit', function(e){
		let valid = processValidity(this);
		if(!valid){
			e.preventDefault();
		}
	});
}

function processValidity(form){
	let valid;
	validatePassword(form);
	valid = applyValidity(form);
	return valid;
}

function applyValidity(form){
	let valid = true;
	let count = 0;
	let elements = form.elements;
	for(let i = 0; i < elements.length - 1; i++){
		let element = elements[i];
		let span = document.getElementById(i);
		if(!element.validity.valid){
			span.innerHTML = element.validationMessage;
			count++;
		}else{
			span.innerHTML = "";
		}
	}
	if(count > 0){
		valid = false;
	}
	return valid;
}

function validatePassword(form){
	let password, confirmPassword;
	password = document.getElementById('password');
	confirmPassword = document.getElementById('confirmPassword');
	if(password.value != confirmPassword.value){
		password.setCustomValidity(
		'Os valores dos campos de senha e confirmação '+ 
		'de senha são diferentes.');
	}else{
		password.setCustomValidity('');
	}
}

checkbox = document.getElementById('roleCheckbox')
checkbox.addEventListener("change", () => {
    const checkbox = document.getElementById('roleCheckbox');
    
    let role;
    if(checkbox.checked){
		role = "ADMIN";
	}else{
		role = "USER";
	}
    //const role = checkbox.checked ? 'ADMIN' : 'USER';
    
    document.getElementById('role').value = role;
  });

(function(){ 
 
    const cep = document.querySelector("input[name=cep]");
 
    cep.addEventListener('blur', e=> {
         const value = cep.value.replace(/[^0-9]+/, '');
         const url = `https://viacep.com.br/ws/${value}/json/`;
 
       fetch(url)
      .then( response => response.json())
      .then( json => {
                 
          if( json.logradouro ) {
            document.querySelector('input[name=street]').value = json.logradouro;
            document.querySelector('input[name=neighborhood]').value = json.bairro;
            document.querySelector('input[name=city]').value = json.localidade;
            document.querySelector('input[name=state]').value = json.uf;
          }
      });
   });
 })();