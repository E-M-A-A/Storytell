package it.unisa.emaa.www.sito.Control.Utente;

import it.unisa.emaa.www.sito.Model.dao.UtenteDao;
import it.unisa.emaa.www.sito.Model.entity.Utente;
import it.unisa.emaa.www.sito.Utils.Validazione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Questa servlet effettua il login di un utente.
 * L'operazione fallisce se:
 * non è presente nel database un utente con l'email data;
 * è presente un utente con l'email data ma la password non corrisponde con la sua.
 * @see it.unisa.emaa.www.sito.Utils.Validazione
 * @author Alessandro Marigliano
 */
@WebServlet(name = "Login",urlPatterns = "/Login")
public class Login extends HttpServlet {
    private UtenteDao utenteDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean login = Validazione.datiCorrispondenti(email,password,utenteDao);
        req.setAttribute("Login",login);
        if(!login) {
            String referer = req.getHeader("referer");
            resp.sendRedirect(referer);
        }
        HttpSession session = req.getSession(true);
        Utente utente = recuperaUtente(email);
        session.setAttribute("Utente",utente);
    }
    private Utente recuperaUtente(String email){
        Utente utente = utenteDao.doRetrieveByEmail(email);
        utente.setPassword("");
        return utente;
    }
    public Login(){
        utenteDao = new UtenteDao();
    }
    public Login(UtenteDao utenteDao){
        this.utenteDao = utenteDao;
    }
}
