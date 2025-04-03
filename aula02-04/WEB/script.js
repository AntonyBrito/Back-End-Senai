async function saveProduto(event) {
    event.preventDefault();

    try {
        const nome = document.getElementById('nomeInput').value.trim();
        if (!nome) {
            throw new Error('O nome do produto é obrigatório');
        }

        const valor = parseFloat(document.getElementById('valorInput').value);
        if (isNaN(valor)) {
            throw new Error('Valor inválido');
        }

        const saldo = parseInt(document.getElementById('saldoInput').value);
        if (isNaN(saldo)) {
            throw new Error('Saldo inválido');
        }

        const saldoMinimo = parseInt(document.getElementById('saldoMinimoInput').value);
        if (isNaN(saldoMinimo)) {
            throw new Error('Saldo mínimo inválido');
        }

        const produto = {
            nome,
            valor,
            saldo,
            saldoMinimo
        };

        const response = await fetch("http://localhost:8080/produto", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(produto),
        });


        // status HTTP entre 200-299
        if (!response.ok ) { 
            const errorData = await response.json();
            throw new Error(errorData.message || 'Erro ao salvar produto');
        }

        const data = await response.json();
        console.log('Produto salvo com sucesso:', data);
        
        alert('Produto salvo com sucesso!');
        document.getElementById('produtoForm').reset();
        loadProduto();
        
    } catch (error) {
        console.error('Erro ao salvar produto:', error);
        alert(error.message);
    }
    
}

async function loadProduto() {
    try {
        const response = await fetch("http://localhost:8080/produto", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao carregar produtos');
        }

        const produtos = await response.json();
        exibirProdutos(produtos);
        
    } catch (error) {
        console.error('Erro:', error);
    }
}

function exibirProdutos(produtos) {
    const lista = document.getElementById('resultado');
    lista.textContent = '';

    if (produtos.length === 0) {
        const vazio = document.createElement('li');
        vazio.textContent = 'Nenhum produto encontrado';
        lista.appendChild(vazio);
    }

    produtos.forEach(produto => {
        const item = document.createElement('li');
        
        const nome = document.createElement('strong');
        nome.textContent = produto.nome;
        
        const texto = document.createTextNode(
            ` - Valor: R$ ${produto.valor.toFixed(2)} - Saldo: ${produto.saldo} - Mínimo: ${produto.saldoMinimo} -`
        );

        const deletarBtn = document.createElement('button')

        deletarBtn.textContent = 'Deletar';
        deletarBtn.className = 'deletarBtn';

        deletarBtn.addEventListener('click', () => {
            deletarProduto(produto.idProduto)
        });
        
        item.appendChild(nome);
        item.appendChild(texto);
        item.appendChild(deletarBtn);
        lista.appendChild(item);
    });

}

async function deletarProduto(id) {
    fetch(`http://localhost:8080/produto/${id}`, {
        method: "DELETE",
    }).then(response => {
        if(!response.ok){
            throw new Error('Erro ao deletar produtos');
        }
        loadProduto()
    }).catch(error => {
        console.log("Erro:", error);
    });
}

document.addEventListener("DOMContentLoaded", () => {
    loadProduto();
    const produtoForm = document.getElementById('produtoForm')
    const carregarProdutosBtn = document.getElementById('carregarProdutosBtn')
    if(produtoForm instanceof HTMLFormElement && 
        carregarProdutosBtn instanceof HTMLButtonElement){
        produtoForm.addEventListener("submit", saveProduto);
        carregarProdutosBtn.addEventListener("click", loadProduto);
    }
})