const forms = document.getElementById('login');

localStorage.clear();

forms.addEventListener('submit', (event) => {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    if (email === "" || password === "") {
        alert("Preencha corretamente todos os campos");
    }

    auth(email, password);
});

const auth = async function (email, password) {
    try {
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if(response.status !== 200) {
            throw new Error("Login Inválido")
        }

        const token = await response.text();

        localStorage.setItem("jwt", token);
    
        window.location.href = "./reservations.html"
    } catch(error) {
        alert("Houve um erro ao tentar fazer a requisição: " + error)
    }
}
