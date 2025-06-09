import { auth, createRegister } from "./services/authService.js";
import { GetReservationsToday } from "./services/reservationsService.js";
import { PostReservation } from "./services/reservationsService.js";
import { getAddressByCEP } from "./services/getAddress.js"

document.addEventListener("DOMContentLoaded", () => {
    const login = document.getElementById("login");
    const register = document.getElementById('register-form');
    const reservationToday = document.getElementById('reservations');
    const cepInput = document.getElementById('cep');
    const createReservation = document.getElementById('create-reservation-form');
    let exitButton = document.getElementById('exit-button');

    if (login) Login();
    if (register) Register();
    if (reservationToday) InsertReservationsToday();
    if (cepInput) InsertAddress();
    if (createReservation) CreateReservation();
    if (exitButton) Logout();
})

if(localStorage.getItem('jwt') === null && window.location.pathname !== '/src/pages/login.html') {
    window.location.href = '/src/pages/login.html'
}


async function Login() {

    let form = document.getElementById("login");

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        try {
            let email = document.getElementById('email-login').value;
            let password = document.getElementById('password-login').value;

            auth(email, password);
        }
        catch (error) {
            console.log(error)
        }
    })
}

async function Register() {
    let form = document.getElementById('register-form');

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        let name = document.getElementById('name-register').value;
        let email = document.getElementById('email-register').value;
        let password = document.getElementById('password-register').value;

        createRegister(name, email, password);
    })
}

async function InsertReservationsToday() {
    let main = document.getElementById('reservations');
    const reservations = await GetReservationsToday();

    reservations.forEach((reservation) => {
        const content = `<div class="card">
            <div class="reservation-details">
                <h3>Dados da Reserva</h3>
                <p id="id"><strong>ID:</strong> ${reservation.id}</p>
                <p><strong>Quarto:</strong> ${reservation.room}</p>
                <p><strong>Checkin:</strong> ${reservation.checkin}</p>
                <p><strong>Checkout:</strong> ${reservation.checkout}</p>
                <p><strong>Valor da Reserva:</strong> ${reservation.totalValue}</p>
            </div>
            <div class="booking-details">
                <h3>Dados do Hospede</h3>
                <p><strong>Nome:</strong> ${reservation.responsible.name}</p>
                <p><strong>Telefone:</strong> ${reservation.responsible.phoneNumber}</p>
                <p><strong>Email:</strong> ${reservation.responsible.email}</p>
            </div>
            <button>Ver Mais</button>
        </div>`

        main.innerHTML += content;
    })
}

async function InsertAddress() {
    try {
        const cepInput = document.getElementById('cep');
        cepInput.addEventListener('focusout', async () => {
            const address = await getAddressByCEP(cepInput.value);

            document.getElementsByName('uf')[0].value = address.state;
            document.getElementsByName('city')[0].value = address.city;
            document.getElementsByName('neighborhood')[0].value = address.neighborhood;
            document.getElementsByName('street')[0].value = address.street;
        })

    } catch (error) {
        alert(error);
    }
}

async function CreateReservation() {

    const form = document.getElementById('create-reservation-form');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        try {

            const formData = new FormData(form);

            const reservation = {
                room: Number(formData.get('room')),
                quantGuests: Number(formData.get('quantGuests')),
                checkin: FormatDate(formData.get('checkin')),
                checkout: FormatDate(formData.get('checkout')),
                typeReservation: formData.get('typeReservation'),
                status: formData.get('status'),
                entryValue: Number(formData.get('entryValue')),
                totalValue: Number(formData.get('totalValue')),
                adminEmail: formData.get('adminEmail'),
                responsible: {
                    name: formData.get('name'),
                    phoneNumber: formData.get('phoneNumber'),
                    email: formData.get('email'),
                    cpf: formData.get('cpf'),
                    address: {
                        cep: formData.get('cep'),
                        state: formData.get('uf'),
                        city: formData.get('city'),
                        neighborhood: formData.get('neighborhood'),
                        street: formData.get('street'),
                        number: formData.get('number')
                    }
                },
                parking: {
                    carType: formData.get('carType'),
                    checkin: FormatDate(formData.get('parkingCheckin')),
                    checkout: FormatDate(formData.get('parkingCheckout'))
                }
            };

            PostReservation(reservation);
            
        }
        catch (error) {
            console.log(error)
        }
    })
}

function FormatDate(date) {
    let data = new Date(date);
    data.setHours(data.getHours() + 3);
    let day = String(data.getDate()).padStart(2, '0');
    let month = String(data.getMonth() + 1).padStart(2, '0');
    let year = data.getFullYear();
    return `${day}/${month}/${year}`;
}

/* Provisório */

function Logout() {
    let exitButton = document.getElementById('exit-button');

    exitButton.addEventListener('click', () => {
        localStorage.clear();
    })
}