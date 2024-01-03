 package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artista {
    private Long idArtista;
    private String nombre;
    private String generoMusical;
    private String paisOrigen;
    private int entradas;
    private double precio;
    private int entradasVendidas;
    private byte[] fotoArtista;
    private String fotoBase64;
    private Estadio estadio;
    
        //constructor para craer objeto para subir
    public Artista (String nombre, String generoMusical, String paisOrigen, int entradas ,double precio, int entradasVendidas , byte[] fotoArtista, String fotoBase64, Estadio estadio) {
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.paisOrigen = paisOrigen;
        this.entradas = entradas;
        this.precio = precio;
        this.entradasVendidas = entradasVendidas;
        this.fotoArtista = fotoArtista;
        this.fotoBase64 = fotoBase64;
        this.estadio = estadio;
    }

    //constructor para craer objeto para bajar
    public Artista (long idArtista ,String nombre, String generoMusical, String paisOrigen, int entradas ,double precio, int entradasVendidas , byte[] fotoArtista, String fotoBase64, Estadio estadio) {
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.paisOrigen = paisOrigen;
        this.entradas = entradas;
        this.precio = precio;
        this.entradasVendidas = entradasVendidas;
        this.fotoArtista = fotoArtista;
        this.fotoBase64 = fotoBase64;
        this.estadio = estadio;
    }

}
