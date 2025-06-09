import { InvalidCaracter } from "../errors/InvalidCaracter.js";
import { ResponseErrorMessage } from "../errors/ResponseErrorMessage.js";

export async function auth(email, password) {

    if (email === "" || password === "") {
        throw new InvalidCaracter("Os atributos email e password não podem ser nulos");
    }

    getAuth(email, password);
}

const getAuth = async function (email, password) {
    try {
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if(response.status !== 200) {
            const error = await response.json();
            throw new ResponseErrorMessage(error.message)
        }

        const token = await response.text();

        localStorage.setItem("jwt", token);
        
        window.location.href = "/index.html"
    
    } catch(error) {
        const errorMessage = document.getElementById("wrong-password");
        
        console.log(error)
        errorMessage.innerText = error
        errorMessage.style.display = "block"
    }
}

export async function CreateRegister(name, email, password){

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