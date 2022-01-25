package Control.StoriaTest.Asincrono;

import com.google.gson.Gson;
import it.unisa.emaa.www.sito.Control.Storia.Asincrono.CaricaStorie;
import it.unisa.emaa.www.sito.Model.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Model.dao.StoriaDao;
import it.unisa.emaa.www.sito.Model.entity.Reazione;
import it.unisa.emaa.www.sito.Model.entity.Storia;
import it.unisa.emaa.www.sito.Model.entity.StoriaReazioni;
import it.unisa.emaa.www.sito.Model.entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CaricaStorieTest {

    @Test
    public void successoTest() throws IOException {
        Utente utente = new Utente();
        Storia storia = new Storia();
        Storia storia1 = new Storia();
        Storia storia2 = new Storia();
        int pagina = 0;

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        session.setAttribute("utente", utente);
        request.setParameter("pagina", pagina + "");

        ArrayList<Storia> lista = new ArrayList<>();
        storia.setId(1);
        storia.setUsername("pippo");
        storia.setContenuto("AAAAAAAAAAAAAAAAAAAAAAAAAAHHHH");
        storia.setDataCreazione(LocalDate.now());
        storia.setNReazioni(0);
        storia.setNCommenti(1);

        utente.setPassword("");
        utente.setUsername("pippo2");
        utente.setId("gaiusgi@yagdaygs.com");

        Reazione reazione = new Reazione();
        reazione.setEmailUtente("gaiusgi@yagdaygs.com");
        reazione.setIdStoria(1);

        StoriaReazioni storiareazioni = new StoriaReazioni();
        storiareazioni.setStoria(storia);
        storiareazioni.setReazionata(true);


        ArrayList<StoriaReazioni> lista2 = new ArrayList<>();
        lista2.add(storiareazioni);


        Gson gson = new Gson();

        String risultato = gson.toJson(lista2);




        StoriaDao dao = Mockito.mock(StoriaDao.class);

        ReazioneDao dao2 = Mockito.mock(ReazioneDao.class);

        Mockito.when(dao.doRetrieveByPage(30,pagina*30)).thenReturn(lista);
        Mockito.when(dao2.doRetrieve(utente.getId(), storia.getId())).thenReturn(reazione);

        CaricaStorie controller = new CaricaStorie(dao, dao2);
        controller.caricaStorie(request,response);
        response.setContentType("plain/text");
        response.setCharacterEncoding("UTF-8");
        assertEquals(response.getContentAsString(),risultato);





    }
}
