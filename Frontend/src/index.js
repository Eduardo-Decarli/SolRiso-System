// Função para obter o Bearer Token (geralmente armazenado na sessão ou localStorage)
function getBearerToken() {
    return sessionStorage.getItem("jwt_token");
  }
  
  // Função para fazer a requisição e buscar os dados das reservas
  async function fetchReservas() {
    const url = 'http://localhost:8080/api/v1/reservation';  // Substitua pela URL da sua API
    const token = getBearerToken();
    console.log(token)
  
    if (!token) {
      alert('Você não está autenticado!');
      window.location.href = 'http://127.0.0.1:5500/public/login.html';
    }
  
    try {
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,  // Envia o Bearer Token no cabeçalho
          'Content-Type': 'application/json'
        }
      });
  
      // Verifica se a resposta foi bem-sucedida
      if (!response.ok) {
        throw new Error('Falha ao carregar as reservas!');
      }
  
      // Extrai os dados em JSON
      const data = await response.json();
  
      // Exibe as reservas
      renderizarReservas(data);
    } catch (error) {
      console.error('Erro ao buscar reservas:', error);
      document.getElementById('mensagem-status').textContent = 'Erro ao carregar as reservas. Tente novamente mais tarde.';
    }
  }
  
  // Função para renderizar as reservas na página
  function renderizarReservas(reservas) {
    const listaReservasElement = document.getElementById('lista-reservas');
    
    // Limpar lista antes de adicionar novos itens
    listaReservasElement.innerHTML = '';
  
    if (reservas.length === 0) {
      document.getElementById('mensagem-status').textContent = 'Nenhuma reserva encontrada para hoje.';
      return;
    }
  
    reservas.forEach(reserva => {
      const reservaElement = document.createElement('div');
      reservaElement.classList.add('reserva');
  
      // Adiciona informações básicas
      reservaElement.innerHTML = `
        <h3>Reserva #${reserva.id}</h3>
        <p><strong>Quartos:</strong> ${reserva.room}</p>
        <p><strong>Data de Entrada:</strong> ${reserva.checkin}</p>
        <p><strong>Data de Saída:</strong> ${reserva.checkout}</p>
        <p><strong>Total:</strong> R$ ${reserva.totalValue.toFixed(2)}</p>
        <div class="subtitulo">Responsável</div>
        <p><strong>Nome:</strong> ${reserva.responsible.name}</p>
        <p><strong>Email:</strong> ${reserva.responsible.email}</p>
        <p><strong>Telefone:</strong> ${reserva.responsible.phoneNumber}</p>
        <p><strong>CPF:</strong> ${reserva.responsible.cpf}</p>
      `;
      
      listaReservasElement.appendChild(reservaElement);
    });
  }
  
  // Chama a função para buscar as reservas assim que a página for carregada
  document.addEventListener('DOMContentLoaded', fetchReservas);
  