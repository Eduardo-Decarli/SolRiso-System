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


        if(response.status === 400) {
            const error = await response.json();
            console.log(error)
            throw new Error(error.message)
        }
        if(response.status !== 200) {
            const error = await response.json();
            throw new Error(error.message)
        }

        const token = await response.text();

        localStorage.setItem("jwt", token);
    
        window.location.href = "./index.html"
    } catch(error) {
        const errorMessage = document.getElementById("wrong-password");
        
        console.log(error)
            errorMessage.innerText += error
            errorMessage.style.display = "block"

    }
}
