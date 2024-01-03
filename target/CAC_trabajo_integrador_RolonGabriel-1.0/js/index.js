
document.addEventListener("DOMContentLoaded", function () {

    dataIndex();

});


async function dataIndex() {


    const cardEstadios = document.querySelector(".card-estadios");

    try {

        const res = await fetch("/app/estadios?action=getAll")

        if(!res.ok) throw(res.statusText);

        let estadios = await res.json();

        estadios.forEach(estadio => {
            console.log(estadio)
            cardEstadios.innerHTML += `
                    <a href="pages/eventosEstadio.html"><div class="estadio">
                        <div class="estadio-img">
                            <img style="width: 300px; height: 200px;"' src="data:img/jpeg;base64,${estadio.fotoEstadio}" alt="">
                        </div>
                        <div class="estadio-content">
                            <h3>Nombre: ${estadio.nombre}</h3>
                            <p>Direccion: ${estadio.direccion}</p>
                            <p>Capacidad: ${estadio.capacidad}</p>
                        </div>
                        <div class="estadio-eliminar">
                        <button class="eliminar" type="button" onclick="eliminarEstadio(${estadio.idEstadio})">Eliminar</button>
                        </div>
                    </div></a>

                `
        });
        console.log("Lisssssssssssssssssssto")


    } catch (error) {
        console.error(error);
    }

}


