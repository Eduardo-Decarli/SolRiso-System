export function isNullOrEmpty(value) {
    return value === null || value === undefined || value === "";
}

export function isObjectBlank(obj) {
    const values = Object.values(obj);
    if(values.every(value => String(value).trim() === "" || value === null)) {
        return null;
    }
    return obj;
}

export function formatDate(date) {
    if(!date) {
        return null;
    }
    let data = new Date(date);
    data.setHours(data.getHours() + 3);
    let day = String(data.getDate()).padStart(2, '0');
    let month = String(data.getMonth() + 1).padStart(2, '0');
    let year = data.getFullYear();
    return `${day}/${month}/${year}`;
}

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

export function formaterCPF(cpf) {
  
  cpf = cpf.replace(/\D/g, '');

  if (cpf.length <= 3) {
    return cpf;
  } else if (cpf.length <= 6) {
    return cpf.replace(/(\d{3})(\d+)/, '$1.$2');
  } else if (cpf.length <= 9) {
    return cpf.replace(/(\d{3})(\d{3})(\d+)/, '$1.$2.$3');
  } else {
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2}).*/, '$1.$2.$3-$4');
  }
}

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

export function decodeJwtPayload(payload) {
    const json = atob(payload);
    return JSON.parse(json).sub;
}

