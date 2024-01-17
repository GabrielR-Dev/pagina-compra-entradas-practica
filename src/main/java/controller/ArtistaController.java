package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.ArtistaDao;
import data.EstadioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import model.Artista;
import model.Estadio;

@WebServlet("/estadio/artista")
@MultipartConfig(
        location = "/media/temp",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class ArtistaController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ArtistaController.class.getName());
    ObjectMapper objectMapper = new ObjectMapper();

    List<Artista> art = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.err.println("Entranco a doGet de artistaController");

        String est = req.getParameter("estadio");
        String route = req.getParameter("action");
        String id = req.getParameter("id");

        switch (route) {

            case "getAllArtistas":
                System.out.println("Entrando a la case getAllArtistas");
                resp.setContentType("application/json; charset=utf-8");

                try {
                    Estadio estadio = EstadioDao.buscarEstadioPorId(Integer.parseInt(est));
                    art = ArtistaDao.verArtistas(estadio);

                } catch (Exception e) {
                    System.err.println(e);
                }

                objectMapper.writeValue(resp.getWriter(), art);
                break;

            case "getArtista":

                System.err.println("Entrando al getArtista");
                resp.setContentType("application/json; charset=utf-8");

                Artista artist = null;

                try {
                    Estadio estadio = EstadioDao.buscarEstadioPorId(Integer.parseInt(est));
                    artist = ArtistaDao.verArtistaPoId(estadio, Integer.parseInt(id));

                } catch (ClassNotFoundException ex) {
                    System.err.println("Error en el catch del getUser: " + ex);
                }

                objectMapper.writeValue(resp.getWriter(), artist);
                break;

            case "editarArtista":
                System.out.println("Entrando a la case editarArtista");
                resp.setContentType("application/json; charset=utf-8");

                try {
                    Estadio estadio = EstadioDao.buscarEstadioPorId(Integer.parseInt(est));

                } catch (ClassNotFoundException e) {
                } finally {
                }

                break;

        }

    }

}
