package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Artista;

public class ArtistaDao {

    private static final String SQL_SELECT = "SELECT * FROM artistas";
    private static final String SQL_INSERT = "INSERT INTO artistas(long idArtista ,String nombre, String generoMusical,"
            + " String paisOrigen, int entradas ,double precio, int entradasVendidas , byte[] fotoArtista, String fotoBase64, "
            + "Estadio estadio) VALUES(?, ?, ?, ?)";
    private static final String SQL_LOGIC_DELETE = "DELETE FROM artistas WHERE idArtista = ?";

    public List<Artista> verArtistas() {

        return null;
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
