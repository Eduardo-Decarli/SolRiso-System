import {InvalidCaracter} from "../errors/InvalidCaracter.js";

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

        if(response.status === 400) {
            const error = await response.json();
            console.log(error)
            throw new InvalidCaracter(error.message)
        }
        if(response.status !== 200) {
            const error = await response.json();
            throw new Error(error.message)
        }

        const token = await response.text();

        localStorage.setItem("jwt", token);
        
        window.location.href = "/index.html"
    
    } catch(error) {
        const errorMessage = document.getElementById("wrong-password");
        
        console.log(error)
            errorMessage.innerText += error
            errorMessage.style.display = "block"
    }
}