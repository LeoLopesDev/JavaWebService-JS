var url = new URL("http://localhost:8080/treinamento/rest/department/");
window.onload = function () {
  this.listarSetores();
};
function listarSetores() {
  fetch(url)
    .then((response) => response.json())
    .then((resp) => {
      let li = `<tr><th>ID</th><th>Nome</th></tr>`;

      resp.forEach((department) => {
        li += `<tr>
                <td>${department.id}</td>
                <td>${department.name}</td>      
                <td><button style="background: #1c1e1f; border-radius: 6px; padding: 15px; cursor: pointer; color: #fff; border: none; font-size: 16px;"type="button" id="deletar" onclick="deletarSetor(${department.id})">DELETAR</button></td> 
                <td><button style="background: #1c1e1f; border-radius: 6px; padding: 15px; cursor: pointer; color: #fff; border: none; font-size: 16px;" type="button" id="atualizar" onclick="editarSetor(${department.id})">ATUALIZAR</button></td>  
            </tr>`;
      });

      // Display result
      document.getElementById("departments").innerHTML = li;
    });
}

function criarSetor() {
  var nome = document.getElementById("text").value;
  var form = {
    name: nome,
  };
  fetch(url, {
    method: "POST",
    body: JSON.stringify(form),
    headers: {
      "Content-type": "application/json",
    },
  })
    .then((response) => this.listarSetores())
    .then(() => {
      document.getElementById("name").value = null;
    });
}

function deletarSetor(id) {
  fetch(url + id, { method: "DELETE" }).then(() => this.listarSetores());
}

function editarSetor(id) {
  console.log(id);
  var nome = document.getElementById("text").value;
  var form = {
    id,
    name: nome,
  };
  fetch(url + id, {
    method: "PUT",
    body: JSON.stringify(form),
    headers: {
      "Content-type": "application/json",
    },
  }).then((response) => this.listarSetores());
}
