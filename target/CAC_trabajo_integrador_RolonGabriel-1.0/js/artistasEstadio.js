document.addEventListener("DOMContentLoaded", () => {

    getAllArtistas()
})


async function getAllArtistas() {
    
    const cards = document.querySelector(".cards");
    const valores = window.location.search;

    //Creamos la instancia
    const urlParams = new URLSearchParams(valores);

    //Accedemos a los valores
    let idEstadio = urlParams.get('estadio');
    try {

        const res = await fetch(`/app/estadio/artista?action=getAllArtistas&estadio=${idEstadio}`);

        if (!res.ok) throw (res.statusText);

        let artistas = await res.json();

        artistas.forEach(art => {

            cards.innerHTML += `
                <form action="../pages/detallesArtista.html">
                    <input type="hidden" name="estadio" value="${art.idArtista}">    
                    <button type="submit">
                        <div class="estadio">

                            <div class="estadio-content">
                                <h3>Nombre: ${artistas.nombre}</h3>
                                <p>Direccion: ${art.generoMusical}</p>
                                <p>Capacidad: ${art.paisOrigen}</p>
                            </div>


                        </div>
                    </button>
                </form>
            `;
        });





    } catch (error) {
        console.error(error);
    }
}
