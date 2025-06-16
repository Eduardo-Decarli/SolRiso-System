import { auth, createRegister, newPassword } from "./services/authService.js";
import { GetReservationsToday } from "./services/reservationsService.js";
import { PostReservation } from "./services/reservationsService.js";
import { getAddressByCEP } from "./services/getAddress.js";
import { FormatDate } from "./utils/formatDate.js";
import { formaterCPF } from "./utils/formaterCPF.js";

document.addEventListener("DOMContentLoaded", () => {

    if (localStorage.getItem('jwt') === null && window.location.pathname !== '/src/pages/login.html' && window.location.pathname !== '/src/pages/register.html' && window.location.pathname !== '/src/pages/forgot-password.html') {
        window.location.href = '/src/pages/login.html'
    }

    const login = document.getElementById("login");
    const register = document.getElementById('register-form');
    const reservationToday = document.getElementById('reservations');
    const cepInput = document.getElementById('cep');
    const createReservation = document.getElementById('create-reservation-form');
    const exitButton = document.getElementById('exit-button');
    const forgotPasswordForm = document.getElementById('forgot-password-forms');
    const cpfInput = document.getElementById('cpf');

    if (login) Login();
    if (register) Register();
    if (reservationToday) InsertReservationsToday();
    if (cepInput) InsertAddress();
    if (createReservation) CreateReservation();
    if (exitButton) Logout();
    if (forgotPasswordForm) forgotPassword();
    if (cpfInput) formatCPF(cpfInput);
    

})

function formatCPF(cpf) {
    document.addEventListener("input", () => {
        cpf.value = formaterCPF(cpf.value);
    })
}

function Login() {

    let form = document.getElementById("login");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        try {
            let email = document.getElementById('email-login').value;
            let password = document.getElementById('password-login').value;

            if (email === "" || password === "") {
                throw new InvalidCaracter("Os atributos email e password não podem ser nulos");
            }

            await auth(email, password);

        } catch (error) {
            let errorTemplate = document.getElementById("wrong-password");
            console.log(error.message);
            errorTemplate.innerText = error.message;
            errorTemplate.style.display = "block";
        }
    })
}

function Register() {
    let form = document.getElementById('register-form');

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        try {
            let name = document.getElementById('name-register').value;
            let email = document.getElementById('email-register').value;
            let password = document.getElementById('password-register').value;

            if (name === "" || email === "" || password === "") {
                throw new InvalidCaracter("Os caracteres são inválidos");
            }

            await createRegister(name, email, password);

        } catch (error) {
            let errorTemplate = document.getElementById("wrong-password");
            console.log(error.message);
            errorTemplate.innerText = error.message;
            errorTemplate.style.display = "block";
        }
    })
}

function forgotPassword() {

    const form = document.getElementById('forgot-password-forms');

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        try {
            const email = document.getElementById('forgot-password-email').value;
            const password = document.getElementById('new-password').value;

            await newPassword(email, password);

        } catch (error) {
            let errorTemplate = document.getElementById("wrong-password");
            console.log(error.message);
            errorTemplate.innerText = error.message;
            errorTemplate.style.display = "block";
        }
    })
}

async function InsertReservationsToday() {

    const reservations = await GetReservationsToday();
    let roomsModal = document.getElementsByClassName('room');
    let statusModal = document.getElementsByClassName('status');

    reservations.forEach((reservation) => {

        for (let i = 0; i < roomsModal.length; i++) {
            let roomModal = roomsModal[i];
            let data_room = parseInt(roomModal.getAttribute('data-room'))

            if (reservation.room === data_room) {
                roomModal.classList = "room ocupado";
                roomModal.setAttribute("href", `#${data_room}`)
                statusModal[data_room - 1].innerHTML = "Quarto Ocupado"
            }
        }
    })

    let main = document.getElementById('reservations');

    reservations.forEach((reservation) => {
        const modalId = `modal-room-${reservation.room}`;

        const content = `
        <div class="card" id="${reservation.room}">
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
            <button class="open-modal-btn" data-modal="${modalId}">Ver Mais</button>
        </div>
        
        <div class="modal" id="${modalId}" role="dialog" aria-modal="true" aria-labelledby="modalTitle">
            <div class="modal-content">

                
        
                <div class="reservation-section">
                    <h3 id="modalTitle">Dados da Reserva</h3>
                    <p><strong>ID:</strong> ${reservation.id}</p>
                    <p><strong>Quarto:</strong> ${reservation.room}</p>
                    <p><strong>Tipo da Reserva:</strong> ${reservation.typeReservation}</p>
                    <p><strong>Status da Reserva:</strong> ${reservation.status}</p>
                    <p><strong>Checkin:</strong> ${reservation.checkin}</p>
                    <p><strong>Checkout:</strong> ${reservation.checkout}</p>
                    <p><strong>Valor de Entrada:</strong> ${reservation.entryValue}</p>
                    <p><strong>Valor Total:</strong> ${reservation.totalValue}</p>
                </div>

                <div class="guest-section">
                    <h3>Dados do Hóspede</h3>
                    <p><strong>Nome:</strong> ${reservation.responsible.name}</p>
                    <p><strong>Telefone:</strong> ${reservation.responsible.phoneNumber}</p>
                    <p><strong>Email:</strong> ${reservation.responsible.email}</p>
                    <p><strong>CPF:</strong> ${reservation.responsible.cpf}</p>
                </div>

                <span class="close-modal-btn" data-close="${modalId}" aria-label="Fechar modal">&times;</span>

                <div class="modal-actions">
                    <a href="#" class="modal-btn btn-excluir">Excluir Reserva</a>
                    <a href="#" class="modal-btn">Editar Reserva</a>
                    <a href="#" class="modal-btn">Ver Reserva</a>
                    <a href="#" class="modal-btn">Emitir Comprovante</a>
                </div>

            </div>
        </div>`

        main.innerHTML += content;
    });
    addModalEvents()
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

function Logout() {
    let exitButton = document.getElementById('exit-button');

    exitButton.addEventListener('click', () => {
        localStorage.clear();
    })
}

function addModalEvents() {
    const openButtons = document.querySelectorAll(".open-modal-btn");
    const closeButtons = document.querySelectorAll(".close-modal-btn");

    openButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            const modalId = btn.getAttribute("data-modal");
            const modal = document.getElementById(modalId);
            modal.style.display = "block";
        });
    });

    closeButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            const modalId = btn.getAttribute("data-close");
            const modal = document.getElementById(modalId);
            modal.style.display = "none";
        });
    });

    window.addEventListener("click", (event) => {
        if (event.target.classList.contains("modal")) {
            event.target.style.display = "none";
        }
    });
}