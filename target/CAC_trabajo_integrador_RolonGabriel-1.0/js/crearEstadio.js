
const form = document.querySelector("form");

document.addEventListener("submit", async e => {
    e.preventDefault();

    let formData = new FormData(form);
    formData.append('action', "add");
    try {
        const res = await fetch("/app/estadios", {
            method: "POST",
            body: formData
        })


        location.href = "http://localhost:8080/app/index.html";

    } catch (error) {

    }

})
