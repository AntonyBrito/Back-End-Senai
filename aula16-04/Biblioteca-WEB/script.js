const API_BASE = "http://localhost:8080";

function showSection(id) {
  document.querySelectorAll(".section").forEach(section => {
    section.classList.add("hidden");
  });
  document.getElementById(id).classList.remove("hidden");
}

// ========== LIVROS ==========
document.getElementById("livroForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const data = Object.fromEntries(formData);

  await fetch(`${API_BASE}/livro`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });

  e.target.reset();
  loadLivros();
});

async function loadLivros() {
  const res = await fetch(`${API_BASE}/livro`);
  const livros = await res.json();

  const container = document.getElementById("livroList");
  container.innerHTML = livros.map(l =>
    `<div><strong>${l.nome}</strong> - ${l.autor} (${l.genero}) | ISBN: ${l.isbn}</div>`
  ).join("");
}

loadLivros();

// ========== CLIENTES ==========
document.getElementById("clienteForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const data = Object.fromEntries(formData);

  await fetch(`${API_BASE}/cliente`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });

  e.target.reset();
  loadClientes();
});

async function loadClientes() {
  const res = await fetch(`${API_BASE}/cliente`);
  const clientes = await res.json();

  const container = document.getElementById("clienteList");
  container.innerHTML = clientes.map(c =>
    `<div>${c.nome} ${c.sobrenome} | CPF: ${c.cpf}</div>`
  ).join("");
}

loadClientes();

// ========== EMPRÉSTIMOS ==========
document.getElementById("emprestimoForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const data = {
    dataInicial: formData.get("dataInicial"),
    dataFinal: formData.get("dataFinal"),
    cliente: { cpf: formData.get("cpfCliente") },
    livros: formData.get("isbnLivro").split(',').map(isbn => ({ isbn: isbn.trim() }))
  };

  await fetch(`${API_BASE}/emprestimo`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });

  e.target.reset();
  loadEmprestimos();
});

async function loadEmprestimos() {
  const res = await fetch(`${API_BASE}/emprestimo`);
  const emprestimos = await res.json();

  const container = document.getElementById("emprestimoList");
  container.innerHTML = emprestimos.map(e =>
    `<div>
      Cliente: ${e.cliente.nome} ${e.cliente.sobrenome}<br />
      Livros: ${e.livros.map(l => l.nome).join(", ")}<br />
      De ${e.dataInicial} até ${e.dataFinal}
    </div><hr />`
  ).join("");
}

loadEmprestimos();
