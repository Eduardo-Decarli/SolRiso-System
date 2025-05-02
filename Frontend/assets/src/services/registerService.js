const formRegister = document.getElementById('register');

formRegister.addEventListener('submit', (event) => {
    event.preventDefault();
    const name = document.getElementById('name-user').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    if (name === "" || email === "" || password === "") {
        alert("Insira dados válidos");
        return;
    }

    register(name, email, password)
});

const register = async function (name, email, password, role = "ADMIN") {
    try {
        const response = await fetch("http://localhost:8080/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name, email, password, role })
        })

        switch (response.status) {
            case 201:
                window.location.href = "./login.html"
                break;

            case 409:
                alert("Dados Incorretos");
                break;
        }
    } catch (error) {
        return "Houve um erro ao tentar fazer o cadastro: " + error;
    }
} 