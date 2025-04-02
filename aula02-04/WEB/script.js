async function saveProduto(event){
    event.preventDefault();

        const nome = document.getElementById('nomeInput').value;
        const valor = parseFloat(document.getElementById('valorInput').value);
        const saldo = parseInt(document.getElementById('saldoInput').value);
        const saldoMinimo = parseInt(document.getElementById('saldoMinimoInput').value);

        const produto = {
            nome: nome,
            valor: valor,
            saldo: saldo,
            saldoMinimo: saldoMinimo
        };

        const response = await fetch("http://localhost:8080/produto", {
            method: "POST",
            headers: {
            "Content-Type": "application/json",
            },
            body: JSON.stringify(produto),
        }).then(function(res){ console.log(res) })
        .catch(function(res){ console.log(res) });
      
}

document.addEventListener("DOMContentLoaded", () => {
    let enviarBtn = document.getElementById('enviarBtn')
    if(enviarBtn instanceof HTMLButtonElement){
        enviarBtn.addEventListener('click', saveProduto)
    }
})