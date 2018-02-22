/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import entity.Word;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class WordFacade extends AbstractFacade<Word> {

    @PersistenceContext(unitName = "Ptvr15WordsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WordFacade() {
        super(Word.class);
    }
    
    public List<Word> findAll(User regUser){
        return em.createQuery("SELECT w FROM Word w WHERE w.user=:regUser")
                .setParameter("regUser", regUser)
                .getResultList();
    }
    
}
