var url = new URL("http://localhost:8080/treinamento/rest/employee/");
var urlSetores = new URL("http://localhost:8080/treinamento/rest/department/");
window.onload = function () {
  this.listarSetores();
  this.listarFuncionarios();
};
function listarFuncionarios() {
  fetch(url)
    .then((response) => response.json())
    .then((resp) => {
      let li = [];

      resp.forEach((employee) => {
        li += `<tr>
                <td>${employee.name}</td>
                <td>${employee.cpf}</td>
                <td>${employee.idDepartment}</td>
                <td>${employee.baseSalary.toLocaleString("pt-br", {
                  style: "currency",
                  currency: "BRL",
                })}</td>
                <td>${employee.email}</td>
                <td>${employee.birthday.split("-").reverse().join("/")}</td>
              
                <td><button style="background: #1c1e1f; border-radius: 6px; padding: 15px; cursor: pointer; color: #fff; border: none; font-size: 16px;" type="button" id="deletar" onclick="deletarFuncionario(${
                  employee.id
                })">Deletar Funcionário</button></td>
            </tr>`;
      });
      // Display result
      document.getElementById("employess").innerHTML = li;
    });
}

function salvarFuncionario() {
  var name = document.getElementById("nome").value;
  var idDepartment = document.getElementById("setores").value;
  var baseSalary = document.getElementById("salario").value;
  var email = document.getElementById("email").value;
  var birthday = document.getElementById("dataDeNascimento").value;
  var cpf = document.getElementById("cpf").value;

  var form = {
    name,
    idDepartment,
    baseSalary,
    email,
    birthday,
    cpf,
  };
  fetch(url, {
    method: "POST",
    body: JSON.stringify(form),
    headers: {
      "Content-type": "application/json",
    },
  })
    .then((response) => this.listarFuncionarios())
    .then(() => {
      document.getElementById("nome").value = null;
      document.getElementById("dataDeNascimento").value = null;
      document.getElementById("salario").value = null;
      document.getElementById("email").value = null;
    });
}

function deletarFuncionario(id) {
  fetch(url + id, { method: "DELETE" }).then(() => this.listarFuncionarios());
}

function editarFuncionario(id) {
  console.log(id);
  var name = document.getElementById("nome").value;
  var idDepartment = document.getElementById("setor").value;
  var basesalary = document.getElementById("salario").value;
  var email = document.getElementById("email").value;
  var birthday = document.getElementById("birthday").value;
  var form = {
    id,
    name,
    idDepartment,
    basesalary,
    email,
    birthday,
  };
  fetch(url + id, {
    method: "PUT",
    body: JSON.stringify(form),
    headers: {
      "Content-type": "application/json",
    },
  }).then((response) => this.listarFuncionarios());
}

function listarSetores() {
  fetch(urlSetores)
    .then((response) => response.json())
    .then((resp) => {
      var select;
      resp.forEach((d) => {
        select += `<option value="${d.id}">${d.name}</option>`;
      });
      document.getElementById("setores").innerHTML = select;
    });
}
