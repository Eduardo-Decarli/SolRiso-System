const URL_ReservationToday = "http://localhost:8080/api/v1/reservation"

async function getReservationToday() {
    const response = await fetch(URL_ReservationToday, {
        method: "GET",
        headers: {
            'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
            'Content-Type': 'application/json'
        }
    });
    if(response.status === 404) {
        console.log("Nenhuma Reserva Encontrada") 
        return[]   
    }
    if(response.status === 403) {
        console.log("Entrada Não Autorizada")
        return []    
    }
    const content = await response.json();
    return content;
}

