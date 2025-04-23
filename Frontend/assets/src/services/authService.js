
const submit = document.getElementById('submit-login');

submit.addEventListener('click', () => {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    console.log(email, password);
});