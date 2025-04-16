// Captura o formulário
const loginForm = document.getElementById('login-form');

// Adiciona o evento de submit ao formulário
loginForm.addEventListener('submit', async function(event) {
    event.preventDefault(); // Evita o envio padrão do formulário

    // Captura os valores do email e senha
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    // Realiza a requisição de login
    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: email,
                senha: senha
            })
        });

        // Verifica se a requisição foi bem-sucedida
        if (!response.ok) {
            throw new Error('Credenciais inválidas ou erro no servidor');
        }

        // Obtém o token JWT da resposta
        const data = await response.json();
        const token = data.token;

        // Armazena o token na sessão
        sessionStorage.setItem('jwt_token', token);

        // Redireciona o usuário para outra página após o login
        window.location.href = 'http://localhost:8080/api/v1/reservation/allReservations'; // Altere para a página desejada
    } catch (error) {
        alert('Erro: ' + error.message);
    }
});