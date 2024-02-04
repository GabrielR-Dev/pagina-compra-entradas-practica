 function eliminarEstadio(id){

    //const button = document.querySelector(".eliminar");

    confirm("Seguro deceas eliminar el estadio: 'Nombre' con el id: '"+id+"'")

    if(confirm){

        try {
            const res =  fetch(`/app/estadios?eliminar=${id}`, { method: "DELETE" });
                window.location.replace("http://localhost:8080/app/index.html");
            if(!res.ok){
                throw(res.statusText);
            } 
    

            
        } catch (error) {
            return "Error al borrar: " + error;
        }
    }
}




