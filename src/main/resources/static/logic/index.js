const welcome = document.getElementById("welcome")
const createProject = document.getElementById("createProject")
const pName = document.getElementById("pName")
const iType = document.getElementById("iType")
const firstCard = document.getElementById("firstCard")



fetch('http://localhost:8080/api/data/project_types', {
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
        data => data.forEach(x => {
            iType.options.add(new Option(x.name, x.id))
        }))
    .catch((error) => {

    });


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
        data => {
            welcome.textContent = data.name
            fetchProjects()
        }
    )
    .catch((error) => {
        firstCard.remove()
        createProject.remove()
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

async function fetchProjects() {
    const colleft = document.getElementById("colleft")
    const colright = document.getElementById("colright")
    fetch('http://localhost:8080/api/project', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(
        response => {
            if (response.ok) {
                return response.json()
            }
        }
    ).then(data => {
        if (data.length == 0) {
            firstCard.remove()
            return;
        }
        data.forEach((x, i) => {
            if (i == 0) {
                buildCard(firstCard, x)
            } else {
                newCard = firstCard.cloneNode(true)
                buildCard(newCard, x)
                if (i % 2 == 1) {
                    colright.appendChild(newCard)
                } else {
                    colleft.appendChild(newCard)
                }
            }
        })
    }).catch(error => {
        console.log(error)
        firstCard.remove()
    })

}

async function buildCard(element, data) {
    element.querySelector(".card-title").textContent = data.projectName
    element.querySelector(".card-text").textContent = data.projectDescription
    strctid = data.dikengaStructure.id
    element.querySelector(".card-img-top").src = "/assets/"+strctid+"_"+data.currentPhaseNumber%data.dikengaStructure.phases+".png"
}