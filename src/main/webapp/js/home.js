const customerInfoModal = document.getElementById('customerInfoModal');

customerInfoModal.addEventListener('show.bs.modal', (event) => {
    const button = event.relatedTarget;
    const name = button.getAttribute('data-bs-name');
    const cpf = button.getAttribute('data-bs-cpf');
    const phone = button.getAttribute('data-bs-phone');
    const email = button.getAttribute('data-bs-email'); 

    const modalBody = customerInfoModal.querySelector('.modal-body');
    modalBody.innerHTML = `
        <p><strong>Nome:</strong> ${name}</p>
        <p><strong>CPF:</strong> ${cpf}</p>
        <p><strong>Telefone:</strong> ${phone}</p>
        <p><strong>Email:</strong> ${email}</p>
    `;
});

const serviceOrderInfoModal = document.getElementById('serviceOrderInfoModal');

serviceOrderInfoModal.addEventListener('show.bs.modal', (event) => {
    const button = event.relatedTarget;
    const status = button.getAttribute('data-bs-status');
    const observation = button.getAttribute('data-bs-observation');
    const paymentMethodName = button.getAttribute('data-bs-paymentMethodName');

    const modalBody = serviceOrderInfoModal.querySelector('.modal-body');
    modalBody.innerHTML = `
        <p><strong>Status:</strong> ${status}</p>
        <p><strong>Observação:</strong> ${observation}</p>
        <p><strong>Método de Pagamento:</strong> ${paymentMethodName}</p>
    `;
});

const completeDescriptionModal = document.getElementById('completeDescriptionModal');

completeDescriptionModal.addEventListener('show.bs.modal', (event) => {
    const button = event.relatedTarget;
    const text = button.getAttribute('data-bs-text');

    const modalBody = completeDescriptionModal.querySelector('.modal-body');
    modalBody.innerHTML = `
        <p>${text}</p>
    `;
});
