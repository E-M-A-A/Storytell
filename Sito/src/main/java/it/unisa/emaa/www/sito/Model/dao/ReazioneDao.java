package it.unisa.emaa.www.sito.Model.dao;

import it.unisa.emaa.www.sito.Model.ConnPool;
import it.unisa.emaa.www.sito.Model.entity.Reazione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * La classe ReazioneDao è il dao dell'entità reazione la classe estende un interfaccia dao contenente i metodi da
 * effettuare
 * @author Antonio Scotellaro
 */
public class ReazioneDao implements IReazioneDao{
    /**
     * effettua una query di selezione di ogni reazione in base alla storia
     * @param idStoria
     * @return
     */
    @Override
    public List<Reazione> doRetrieveByStoria(int idStoria) {
        try(Connection conn = ConnPool.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("Select * from reazione where idStoria =?")){
                ps.setInt(1,idStoria);
                ResultSet rs = ps.executeQuery();
                ArrayList<Reazione> list =  new ArrayList<>();
                while(rs.next()){
                    Reazione reazione = new Reazione();
                    reazione.setIdStoria(rs.getInt("idStoria"));
                    reazione.setEmailUtente(rs.getString("emailUtente"));
                    list.add(reazione);
                }
                rs.close();
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * effettua una query di selezione di ogni reazione in base all'emailUtente
     * @param email
     * @return
     */
    @Override
    public List<Reazione> doRetrieveByEmail(String email) {
        try(Connection conn = ConnPool.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("Select * from reazione where emailUtente =?")){
                ps.setString(1,email);
                ResultSet rs = ps.executeQuery();
                ArrayList<Reazione> list =  new ArrayList<>();
                while(rs.next()){
                    Reazione reazione = new Reazione();
                    reazione.setIdStoria(rs.getInt("idStoria"));
                    reazione.setEmailUtente(rs.getString("emailUtente"));
                    list.add(reazione);
                }
                rs.close();
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     *
     * @param email
     * @param idStoria
     * @return
     */
    @Override
    public Reazione doRetrieve(String email,int idStoria) {
        try(Connection conn = ConnPool.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("Select * from reazione where emailUtente =? AND idStoria = ?")){
                ps.setString(1,email);
                ps.setInt(2,idStoria);
                ResultSet rs = ps.executeQuery();
                if(!rs.isBeforeFirst())
                    return null;
                rs.next();
                Reazione reazione = new Reazione();
                reazione.setIdStoria(rs.getInt("idStoria"));
                reazione.setEmailUtente(rs.getString("emailUtente"));
                rs.close();
                return reazione;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * salva una reazione nella base di dati
     * @param reazione
     * @return
     */
    @Override
    public boolean doSave(Reazione reazione) {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT into reazione (idStoria,emailUtente) values (?,?)")){
                ps.setInt(1, reazione.getIdStoria());
                ps.setString(2, reazione.getEmailUtente());

                return ps.executeUpdate()>0;

            }catch (SQLException e){
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
