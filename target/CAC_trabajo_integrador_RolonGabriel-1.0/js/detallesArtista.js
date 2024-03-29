document.addEventListener("DOMContentLoaded", function () {


    const valores = window.location.search;

    //Creamos la instancia
    const urlParams = new URLSearchParams(valores);

    //Accedemos a los valores
    let idEstadio = urlParams.get('estadio');
    let idArtista = urlParams.get('artista');


    fetch(`/app/estadio/artista?action=getArtista&estadio=${idEstadio}&id=${idArtista}`)
        .then(
            res => res.json()
        )
        .then(
            json => {
                console.log(json)
                const art = document.querySelector(".artista")

                art.innerHTML += `
                <div class="datos">
                    <h3>${json.nombre}</h3>
                    <p></p>
                    <p></p>
                    <p></p>
                </div>
                <div class="img">
                    <img style="width: 300px; height: 200px;"' src="data:img/jpeg;base64,${json.estadio.fotoEstadio}" alt="">
                </div>
                `;
            }
        )
})