package data;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Artista;
import model.Estadio;

public class ArtistaDao {

    private static final String SQL_SELECT = "SELECT * FROM artistas";
    private static final String SQL_INSERT = "INSERT INTO artistas(long idArtista ,String nombre, String generoMusical,"
            + " String paisOrigen, int entradas ,double precio, int entradasVendidas , byte[] fotoArtista, "
            + "Estadio estadio) VALUES(?, ?, ?, ?)";
    private static final String SQL_LOGIC_DELETE = "DELETE FROM artistas WHERE idArtista = ?";

    public static List<Artista> verArtistas(Estadio est) {

            List<Artista> listaArtistas = new ArrayList<>();
        try {

            Connection conn = Conexion.getConexion();
            PreparedStatement stm = conn.prepareStatement(SQL_SELECT);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String generoMusical = rs.getString("generoMusical");
                String paisOrigen = rs.getString("paisOrigen");
                int entradas = rs.getInt("entradas");
                double precio = rs.getDouble("precio");
                int entradasVendidas = rs.getInt("entradasVendidas");
                //int estadio = rs.getInt("estadio");
                
                //byte[] fotoArtista = rs.getBytes("fotoArtista");
                
                
                
                /*if(rs.getBlob("fotoArtista") != null){

                    Blob blob = rs.getBlob("fotoArtista");
                    blob.
                }*/
                

                Artista art = new Artista(nombre,generoMusical,paisOrigen,entradas,precio,entradasVendidas,null, est);
                listaArtistas.add(art);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return listaArtistas;
    }

    public Artista verArtistaPoId(int id) {

        return null;
    }

    public Artista verArtistaPorNombre() {

        return null;
    }

    public Artista crearEvento(Artista artista) {

        return null;
    }

    public Artista editarArtista(Artista artista) {

        return null;
    }

    public void eliminarArtista(int id) {

    }
}
