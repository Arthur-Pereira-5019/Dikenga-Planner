const welcome = document.getElementById("welcome")

if (cookieExists('login')) {
    console.log("logn")
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
            console.log()
            console.error('Error:', error);
        });
}