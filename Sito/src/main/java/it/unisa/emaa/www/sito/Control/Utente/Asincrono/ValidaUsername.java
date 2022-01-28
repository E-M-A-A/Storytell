package it.unisa.emaa.www.sito.Control.Utente.Asincrono;

import it.unisa.emaa.www.sito.Model.dao.UtenteDao;
import it.unisa.emaa.www.sito.Utils.Validazione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="UsernamePresente",urlPatterns = "/UsernamePresente")
public class ValidaUsername extends HttpServlet {
    private UtenteDao utenteDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            validaUsername(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void validaUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String username = req.getParameter("username");
        resp.setContentType("plain/text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(Validazione.usernameIsPresent(username,utenteDao));
    }
    public ValidaUsername(){
        utenteDao = new UtenteDao();
    }
    public ValidaUsername(UtenteDao utenteDao){
        this.utenteDao = utenteDao;
    }


}
