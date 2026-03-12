const welcome = document.getElementById("welcome")
const createProject = document.getElementById("createProject")
const pName = document.getElementById("pName")
const iType = document.getElementById("iType")


fetch('http://localhost:8080/api/user/present', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
    .then(
        response => {
            if (response.ok) {
                return response.json()
            }
        })
    .then(
        data => welcome.textContent = data.name)
    .catch((error) => {

    });

createProject.addEventListener("click", function () {
    requestBody = {
        name: pName.value,
        dikengaStructureId: iType.value
    }

    fetch('http://localhost:8080/api/project/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
        .then(
            response => {
                if (response.ok) {
                    window.location.reload()
                }
            })
        .catch((error) => {
            console.log(error)
        });
})
