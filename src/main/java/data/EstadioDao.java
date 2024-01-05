/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import static data.Conexion.*;
import java.sql.*;
import java.util.*;
import model.Estadio;

public class EstadioDao {

    private static final String SQL_SELECT = "SELECT * FROM estadios";
    private static final String SQL_SELECT_POR_ID = "SELECT * FROM estadios WHERE idEstadio = ?";

    private static final String SQL_INSERT = "INSERT INTO estadios(nombre, direccion, fotoEstadio, capacidad) VALUES(?, ?, ?, ?)";
    private static final String SQL_LOGIC_DELETE = "DELETE FROM estadios WHERE idEstadio = ?";

    public static List<Estadio> seleccionarTodos() {
        Estadio estadio = null;
        List<Estadio> estadios = new ArrayList();
        Blob blob = null;
        byte[] imagen = null;

        try (
                Connection conn = getConexion(); // try with no necesita finally ni los close en Conexion
                 PreparedStatement stmt = conn.prepareStatement(SQL_SELECT); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idEstadio = rs.getInt(1);
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                byte[] fotoEstadio = rs.getBytes("fotoEstadio");
                int capacidad = rs.getInt("capacidad");

                /*if (rs.getBlob("fotoEstadio") != null) {
                    blob = rs.getBlob("fotoEstadio");
                    imagen = blob.getBytes(1, (int) blob.length());
                }*/
                estadio = new Estadio(idEstadio, nombre, direccion, fotoEstadio, capacidad);

                estadios.add(estadio);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return estadios;
    }

    public static Estadio buscarEstadioPorId(int id) throws ClassNotFoundException {
        System.out.println("SeleccionarPorID: numero ====  " + id);
        Estadio estadio = null;

        try {
            Connection conn = getConexion();
            PreparedStatement st = conn.prepareCall(SQL_SELECT_POR_ID);
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int idEstadio = rs.getInt(1);
                    String nombre = rs.getString("nombre");
                    String direccion = rs.getString("direccion");
                    byte[] fotoEstadio = rs.getBytes("fotoEstadio");
                    int capacidad = rs.getInt("capacidad");

                    estadio = new Estadio(idEstadio, nombre, direccion, fotoEstadio, capacidad);
                }
            }
            return estadio;
        } catch (SQLException e) {
            System.out.println("Error aquiii ");
        }
        return estadio;
    }

    public static int insertar(Estadio estadio) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, estadio.getNombre());
            stmt.setString(2, estadio.getDireccion());
            stmt.setInt(4, estadio.getCapacidad());

            Blob blob = conn.createBlob();
            blob.setBytes(1, estadio.getFotoEstadio());
            stmt.setBlob(3, blob);

            registros = stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public static int bajaEstadio(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_LOGIC_DELETE);
            stmt.setInt(1, id);

            registros = stmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    /*
     */
}
