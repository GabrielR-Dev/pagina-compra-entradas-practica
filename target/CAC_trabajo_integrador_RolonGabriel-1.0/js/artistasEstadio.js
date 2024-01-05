document.addEventListener("DOMContentLoaded", () => {

    getAllArtistas()
})

async function getAllArtistas(){

    const valores = window.location.search;

    //Creamos la instancia
    const urlParams = new URLSearchParams(valores);

    //Accedemos a los valores
    let idEstadio = urlParams.get('estadio');

    try {

        const res = await fetch(`/app/estadio/artista?action=getAllArtistas&estadio=${idEstadio}`);
        
        if(!res.ok) throw(res.statusText);

        let artistas = await res.json();

        console.log(artistas);




    } catch (error) {
        console.error(error);
    }


}