function cookieExists(name) {
    console.log(document.cookie)
    return document.cookie
        .split(';')
        .some(item => item.trim().startsWith(`${name}=`));
}