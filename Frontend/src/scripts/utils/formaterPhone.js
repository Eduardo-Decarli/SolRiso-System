export function formaterPhone(phone) {

    phone = phone.replace(/\D/g, "");

    if (phone.length <= 1) {
        return phone;
    } else if (phone.length <= 2) {
        return phone.replace(/(\d{2})/, "($1)");
    } else if (phone.length <= 7) {
        return phone.replace(/(\d{2})(\d+)/, "($1) $2");
    } else if (phone.length <= 10) {
        return phone.replace(/(\d{2})(\d{5})(\d+)/, "($1) $2-$3");
    } else {
        return phone.replace(/(\d{2})(\d{5})(\d{4}).*/, "($1) $2-$3")
    }

}