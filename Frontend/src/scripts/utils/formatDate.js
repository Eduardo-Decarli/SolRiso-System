export function formatDate(date) {
    let data = new Date(date);
    data.setHours(data.getHours() + 3);
    let day = String(data.getDate()).padStart(2, '0');
    let month = String(data.getMonth() + 1).padStart(2, '0');
    let year = data.getFullYear();
    return `${day}/${month}/${year}`;
}