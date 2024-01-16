document.addEventListener("DOMContentLoaded", function (){


    const valores = window.location.search;

    //Creamos la instancia
    const urlParams = new URLSearchParams(valores);

    //Accedemos a los valores
    let idEstadio = urlParams.get('estadio');
    let idArtista = urlParams.get('artista');

    

})