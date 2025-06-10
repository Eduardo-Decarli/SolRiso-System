import { InvalidCaracter } from "../errors/InvalidCaracter.js";
import { ResponseErrorMessage } from "../errors/ResponseErrorMessage.js";

export async function auth(email, password) {

    const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
    });

    if (response.status !== 200) {
        const error = await response.json();
        console.log(error);
        if (error.errors.password != null) {
            throw new ResponseErrorMessage(error.errors.password)
        } else {
            throw new ResponseErrorMessage(error.message)
        }
    }

    const token = await response.text();
    localStorage.setItem("jwt", token);
    window.location.href = "/index.html"
}


export async function createRegister(name, email, password) {

    if (name === "" || email === "" || password === "") {
        throw new InvalidCaracter("Os caracteres são inválidos");
    }

    getRegister(name, email, password);
};

const getRegister = async function (name, email, password, role = "ADMIN") {
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