

const iPassword = document.querySelector("#iPassword");
const irPassword = document.querySelector("#irPassword");
const iName = document.querySelector("#iName");
const iEmail = document.querySelector("#iEmail");
const form = document.querySelector("form");
const submit = document.querySelector("#submit");

irPassword.addEventListener("focusout", function () {
    if (iPassword.value != irPassword.value) {
        iPassword.data
    }
})

submit.addEventListener("click", function () {
    const body = {
        username: iName.value,
        email: iEmail.value,
        password: iPassword.value,
    };

    fetch('http://localhost:8080/api/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
        .then(
            response => {
                if (response.ok) {
                    alert("Registered with success!")
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