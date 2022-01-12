package it.unisa.emaa.www.sito.Control.Storia;

import com.google.gson.Gson;
import it.unisa.emaa.www.sito.Model.dao.CommentoDao;
import it.unisa.emaa.www.sito.Model.dao.StoriaDao;
import it.unisa.emaa.www.sito.Model.entity.Commento;
import it.unisa.emaa.www.sito.Model.entity.Post;
import it.unisa.emaa.www.sito.Model.entity.Storia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Questa servlet gestisce la visualizzazione di una storia con i relativi commenti.
 * Crea un oggetto Post che contiene le due entità.
 * @author Alessandro Marigliano
 */
@WebServlet(name = "VisualizzaPost",urlPatterns = "/VisualizzaPost")
public class VisualizzaPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idStoria = Integer.parseInt(req.getParameter("storia"));
        Post post = recuperaPost(idStoria);
        Gson gson = new Gson();
        String json = gson.toJson(post);
        resp.setContentType("plain/text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(json);
    }
    private Post recuperaPost(int idStoria){
        StoriaDao storiaDao = new StoriaDao();
        CommentoDao commentoDao = new CommentoDao();
        Storia storia = storiaDao.doRetrieveById(idStoria);
        ArrayList<Commento> listaCommenti = (ArrayList<Commento>) commentoDao.doRetrieveByStoria(idStoria);
        Post post = new Post();
        post.setStoria(storia);
        post.setCommenti(listaCommenti);
        return post;
    }
}
