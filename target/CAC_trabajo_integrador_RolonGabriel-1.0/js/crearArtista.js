const form = document.querySelector("form");

document.addEventListener("submit", async e => {
    e.preventDefault();

    let formData = new FormData(form);
    formData.append('action', "addArtista");

    try {
        const res = await fetch("/app/estadio/artista", {
            method: "POST",
            body: formData
        })



        //location.href = "http://localhost:8080/app/index.html";

    } catch (error) {

    }

})
