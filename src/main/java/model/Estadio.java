
package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estadio {
    private int idEstadio;
    private String nombre;
    private String direccion;
    private byte[] fotoEstadio;
    private int capacidad;

    public Estadio(String nombre, String direccion, byte[] fotoEstadio, int capacidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fotoEstadio = fotoEstadio;
        this.capacidad = capacidad;
    }
    
    
}
