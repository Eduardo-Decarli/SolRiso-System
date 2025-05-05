let main = document.getElementById('reservations');

(async function insertReservationsToday() {
    const reservations = await getReservationToday()
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
    } )
})()
