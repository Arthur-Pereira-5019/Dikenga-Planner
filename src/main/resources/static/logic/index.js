const welcome = document.getElementById("welcome")
const createProject = document.getElementById("createProject")


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

        createProject.addEventListener("click",function(){
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
        })
