export async function getAddressByCEP(cep) {
  const URL_ViaCEP = `https://viacep.com.br/ws/${cep}/json/`;

  try {
    const response = await fetch(URL_ViaCEP);

    if (!response.ok) {
      throw new Error(`Erro na requisição: ${response.status}`);
    }

    const data = await response.json();

    if (data.erro) {
      throw new Error("CEP não encontrado.");
    }

    return {
      street: data.logradouro,
      city: data.localidade,
      state: data.uf,
      neighborhood: data.bairro,
      postalCode: data.cep
    };
  } catch (error) {
    console.error("Erro ao buscar endereço:", error.message);
    return null;
  }
}
