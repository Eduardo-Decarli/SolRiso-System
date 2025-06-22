//import { ResponseErrorMessage } from "../errors/ResponseErrorMessage";

const URL_RESERVATION = "http://localhost:8080/api/v1/reservation";

export async function getReservationsToday() {
    try {
        const response = await fetch(URL_RESERVATION, {
            method: "GET",
            headers: {
                'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
                'Content-Type': 'application/json'
            }
        });

        if (response.status !== 200) {
            console.log(`Erro ao realizar o fetch: ${data.message}`);
            throw new Error(response.message);
        }

        const data = await response.json();

        return data;
    }
    catch (error) {
        document.getElementById("reservations").innerHTML += `
            <div class="no-reservations">
                <p>${error.message}</p>
            </div>`;
    }
}

export async function postReservation(reservation) {

    const response = await fetch(URL_RESERVATION, {
        method: "POST",
        headers: {
            'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reservation)
    });

    const data = await response.json();
    

    if (response.status !== 201) {
        console.log(`Erro ao realizar o fetch: ${data.message}`);
        throw new Error(data.message);
    }

    if (response.status === 201) {
        setTimeout(() => {
            window.location.href = "/src/pages/reservations.html"
        }, 1000);
    }
}

export async function getAllReservations() {
    const response = await fetch(`${URL_RESERVATION}/allReservations`, {
        method: "GET",
        headers: {
            'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
            'Content-Type': 'application/json'
        }
    });

    const data = response.json();

    if (response.status !== 200) {
        console.log(`Erro ao realizar o fetch: ${response.message}`);
        throw new Error(data.message);
    }

    return data;
}

export async function getReservationById(id) {
    const response = await fetch(`${URL_RESERVATION}/id?id=${id}`, {
        method: "GET",
        headers: {
            'authorization': `Bearer ${localStorage.getItem('jwt')}`,
            'Content-Type': 'application/json'
        }
    });

    const data = response.json();

    if (response.status !== 200) {
        console.log(`Erro ao realizar o fetch: ${response.message}`);
        throw new Error(response.message);
    }

    return data;
}

export async function getReservationByBetween(checkin, checkout) {
    const response = await fetch(`${URL_RESERVATION}/byBetween?checkin=${checkin}&checkout=${checkout}`, {
        method: "GET",
        headers: {
            'authorization': `Bearer ${localStorage.getItem('jwt')}`,
            'Content-Type': 'application/json'
        }
    });

    const data = response.json();

    if (data.status !== 200) {
        console.log(`Erro ao realizar o fetch: ${data.message}`);
        throw new Error(data.message);
    }

    return data;
}

export async function getReservationByRoom(room) {
    const response = await fetch(`${URL_RESERVATION}/byRoom?room=${room}`, {
        method: "GET",
        headers: {
            'authorization': `Bearer ${localStorage.getItem('jwt')}`,
            'Content-Type': 'application/json'
        }
    });

    const data = response.json();

    if (data.status !== 200) {
        console.log(`Erro ao realizar o fetch: ${data.message}`);
        throw new Error(data.message);
    }

    return data;
}

export async function getReservationByResponsibleName(name) {
    const response = await fetch(`${URL_RESERVATION}/byResponsibleName?name=${name}`, {
        method: "GET",
        headers: {
            'authorization': `Bearer ${localStorage.getItem('jwt')}`,
            'Content-Type': 'application/json'
        }
    });

    const data = response.json();

    if (data.status !== 200) {
        console.log(`Erro ao realizar o fetch: ${data.message}`);
        throw new Error(data.message);
    }

    return data;
}

export async function deleteReservationById(id) {
    const response = await fetch(`${URL_RESERVATION}?id=${id}`, {
        method: "DELETE",
        headers: {
            'authorization': `Bearer ${localStorage.getItem('jwt')}`,
            'Content-Type': 'application/json'
        }
    });

    const data = response.json();

    if (data.status !== 200) {
        console.log(`Erro ao realizar o fetch: ${data.message}`);
        throw new Error(data.message);
    }

    return data;
}