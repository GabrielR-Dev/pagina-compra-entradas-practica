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

    private static final String SQL_SELECT = "SELECT * FROM artistas WHERE idEstadio = ?";
    private static final String SQL_SELECT_POR_ID = "SELECT * FROM artistas WHERE idArtista = ? AND idEstadio = ?";
    private static final String SQL_UPDATE_ARTISTA = "UPDATE artistas SET nombre = ?, generoMusical = ?, paisOrigen = ?, entradas = ?,precio = ?, entradasVendidas ?, fotoArtista = ? WHERE idArtista = ?";
    private static final String SQL_INSERT = "INSERT INTO artistas(long idArtista ,String nombre, String generoMusical,"
            + " String paisOrigen, int entradas ,double precio, int entradasVendidas , byte[] fotoArtista, "
            + "Estadio estadio) VALUES(?, ?, ?, ?)";
    private static final String SQL_LOGIC_DELETE = "DELETE FROM artistas WHERE idArtista = ?";

    public static List<Artista> verArtistas(Estadio est) {

        System.out.println( "Entrando a la funcion: verArtistas de ArtistaDao");
        List<Artista> listaArtistas = new ArrayList<>();
        try {

            Connection conn = Conexion.getConexion();
            PreparedStatement stm = conn.prepareStatement(SQL_SELECT);
            stm.setInt(1, est.getIdEstadio());

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                
                String id = rs.getString("idArtista");
                String nombre = rs.getString("nombre");
                String generoMusical = rs.getString("generoMusical");
                String paisOrigen = rs.getString("paisOrigen");
                int entradas = rs.getInt("entradas");
                double precio = rs.getDouble("precio");
                int entradasVendidas = rs.getInt("entradasVendidas");
                /*int estadio = rs.getInt("estadio");

                byte[] fotoArtista = rs.getBytes("fotoArtista");
                if(rs.getBlob("fotoArtista") != null){

                    Blob blob = rs.getBlob("fotoArtista");
                    blob.
                }*/
                Artista art = new Artista(Long.parseLong(id),nombre, generoMusical, paisOrigen, entradas, precio, entradasVendidas, null,null, est);
                listaArtistas.add(art);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return listaArtistas;
    }

    public static Artista verArtistaPoId(Estadio est, int id) {

        //System.err.println("Entrando a verArtistaPorId: Con los datos de Estadio= "+est+" y el id= "+id);
        Artista art = null;

        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stm = conn.prepareStatement(SQL_SELECT_POR_ID);
            stm.setInt(1, id);
            stm.setInt(2, est.getIdEstadio());
            ResultSet rs = stm.executeQuery();
            System.err.println("Este es el resultset= "+rs);

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String generoMusical = rs.getString("generoMusical");
                String paisOrigen = rs.getString("paisOrigen");
                int entradas = rs.getInt("entradas");
                double precio = rs.getDouble("precio");
                int entradasVendidas = rs.getInt("entradasVendidas");
               /* int estadio = rs.getInt("estadio");

                byte[] fotoArtista = rs.getBytes("fotoArtista");
                if(rs.getBlob("fotoArtista") != null){

                    Blob blob = rs.getBlob("fotoArtista");
                    blob.
                }*/
                art = new Artista(nombre, generoMusical, paisOrigen, entradas, precio, entradasVendidas, null, est);
            }

        } catch (Exception e) {
            System.err.println("Error Artista Dao en funcion verArtistaPorId");
        }

        System.err.println(art);
        return art;
        
    }

    public Artista verArtistaPorNombre() {

        return null;
    }

    public Artista crearEvento(Artista artista) {

        return null;
    }

    public Artista editarArtista(Artista artista) {

        try {

            Connection conn = Conexion.getConexion();

            PreparedStatement stm = conn.prepareStatement(SQL_SELECT_POR_ID);
            stm.setString(1, artista.getNombre());
            stm.setString(2, artista.getGeneroMusical());
            stm.setString(3, artista.getPaisOrigen());
            stm.setInt(4, artista.getEntradas());
            stm.setDouble(5, artista.getPrecio());
            stm.setInt(6, artista.getEntradasVendidas());
            stm.setBytes(7, artista.getFotoArtista());
            stm.setLong(8, artista.getIdArtista());

            if (!stm.execute()) {
                System.out.println("Ocurrio un error al editar");
            };

            
            
        } catch (Exception e) {
        }
        return null;
    }

    public void eliminarArtista(int id) {

    }
}
