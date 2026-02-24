

const iPassword = document.querySelector("#iPassword");
const iEmail = document.querySelector("#iEmail");
const iRemember = document.querySelector("#iRemember");

submit.addEventListener("click", function () {
    const body = {
        remember: iRemember.checked,
        email: iEmail.value,
        password: iPassword.value,
    };
    console.log(body)

    fetch('http://localhost:8080/api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
        .then(
            response => {
                if (response.ok) {
                    alert("Welcome!")
                    window.location.pathname = ""
                } else {
                    return response.json()
                }
            })
        .then(
            data => console.log('Error:', data))
        .catch((error) => {
            console.log()
            console.error('Error:', error);
        });
})