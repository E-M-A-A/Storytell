package it.unisa.emaa.www.sito.Control.Storia;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PubblicaStoria extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        String storia = req.getParameter("contenuto");
        resp.getWriter().print(pubblicaStoria(utente.getUsername(),storia));
    }

    private boolean pubblicaStoria(String username,String contenuto){
        StoriaDao storiaDao = new StoriaDao();
        storiaDao.doSave(username(),contenuto);
    }
}