export function formaterCEP(cep) {
    cep = cep.replace(/\D/g, '');

    if (cep.length <= 5) {
        return cep
    } else if (cep.length <= 7) {
        return cep.replace(/(\d{5})(\d+)/, "$1-$2")
    } else {
        return cep.replace(/(\d{5})(\d{3}).*/, "$1-$2")
    }
}