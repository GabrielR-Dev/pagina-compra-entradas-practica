package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.EstadioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Estadio;

@WebServlet("/estadios")
@MultipartConfig(
        location = "/media/temp",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class EstadioController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(EstadioController.class.getName());

    List<Estadio> estadios = null;
    Estadio estadio = null;

    ObjectMapper objectMapper = new ObjectMapper();
    byte[] imageBytes;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.err.println("Entranco a doGet de estadio Controller");
        String route = req.getParameter("action");

        switch (route) {
            case "getAll":
                System.err.println("Entranco al getAll");
                resp.setContentType("application/json; charset=utf-8");

                estadios = EstadioDao.seleccionarTodos();
                for (Estadio estadio : estadios) {
                    if (estadio.getFotoEstadio() != null) {
                        imageBytes = estadio.getFotoEstadio();
                    } else {
                        imageBytes = new byte[0];
                    }
                    //String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                    //estadio.setImagenBase64(imageBase64);
                }
                objectMapper.writeValue(resp.getWriter(), estadios);
                break;


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = "";
        String direccion = "";
        int capacidad = 0;

        //variable de bytes para guardar datos de la foto
        byte[] fotoEstadio = null;

        //Variable para recibir el archivo File del formulario
        Part filePart;

        //Variable para leer los datos en bytes
        InputStream fileContent;

        try {

            req.setCharacterEncoding("UTF-8");
            String route = req.getParameter("action");

            switch (route) {
                case "add":

                    nombre = req.getParameter("nombre");
                    direccion = req.getParameter("direccion");

                    if (req.getParameter("capacidad") != null) {
                        capacidad = Integer.parseInt(req.getParameter("capacidad"));
                    } else {
                        capacidad = 0;
                    }

                    //Con esta linea recibe el archivo file enviado desde el formulario
                    filePart = req.getPart("foto");
                    //Verificamos si el archivo file se encuentra en la request
                    if (filePart != null) {

                        //variable InputStream Esto permitirá leer los bytes del archivo.
                        fileContent = filePart.getInputStream();
                        //Se leen todos los bytes del archivo desde el InputStream y se almacenan en el array de bytes fotoEstadio
                        fotoEstadio = fileContent.readAllBytes();

                    }

                    Estadio nuevoEstadio = new Estadio(nombre, direccion, fotoEstadio, capacidad);

                    EstadioDao.insertar(nuevoEstadio);

                    break;

            }

        } catch (Exception e) {
            LOGGER.severe("Error al procesar la solicitud: " + e.getMessage());
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("eliminar");

        try {
            System.out.println(id);
            EstadioDao.bajaEstadio(Integer.parseInt(id));

        } catch (NumberFormatException e) {

        }
    }

}
