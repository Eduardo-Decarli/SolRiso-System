export function formaterCEP(cep) {
    if (cep.length <= 4) {
        return cep
    } else if (cep.length <= 6) {
        return cep.replace(/(\d{5})(\d+)/, "$1-$2")
    } else {
        return cep.replace(/(\d{5})(\d{3})/, "$1-$2")
    }
}