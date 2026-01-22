

const iPassword = document.querySelector("#iPassword");
const irPassword = document.querySelector("#irPassword");
const irName = document.querySelector("#iName");
const submit = document.querySelector("#submit");

iPassword.addEventListener("focusin",function() {
    $('.collapse').collapse({toggle:true})
    
})

iPassword.addEventListener("focusout",function() {
    $('.collapse').collapse({toggle:false})
})


submit.addEventListener("click",function() {
    fetch
})