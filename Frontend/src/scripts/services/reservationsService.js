const URL_RESERVATION = "http://localhost:8080/api/v1/reservation";

export async function GetReservationsToday() {
    try {
        const response = await fetch(URL_RESERVATION, {
            method: "GET",
            headers: {
                'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
                'Content-Type': 'application/json'
            }
        });
        if (response.status === 404) {
            console.log("Nenhuma Reserva Encontrada")
            throw new Error("Nenhuma Reserva Encontrada para Hoje");

        }
        if (response.status === 403) {
            console.log("Entrada Não Autorizada")
            return []
        }
        const content = await response.json();
        return content;
    } catch (error) {
        document.getElementById("reservations").innerHTML += `
            <div class="no-reservations">
                <p>${error}</p>
            </div>`;
    }
}

export async function PostReservation(reservation) {

    try {
        const response = await fetch(URL_RESERVATION, {
            method: "POST",
            headers: {
                'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( reservation )
        })

        const data = await response.json();
        console.log(data);

    } catch (error) {
        alert(error)
    }
}
