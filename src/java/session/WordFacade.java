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
    
    public List<Word> findAll(User regUser, Boolean active){
        if(active){
            return em.createQuery("SELECT w FROM Word w WHERE w.user=:regUser AND w.active=:active")
                .setParameter("regUser", regUser)
                .setParameter("active", active)
                .getResultList();
        }else{
            return em.createQuery("SELECT w FROM Word w WHERE w.user=:regUser")
                .setParameter("regUser", regUser)
                .getResultList();
        }
        
    }
    public List<Word> findAll(User regUser){
        return findAll(regUser, true);
    }

    public int deactiveWord(User regUser, boolean active) {
        try {
            return  em.createQuery("UPDATE Word w SET w.active=:active WHERE w.id=:id AND w.active=:active")
                .setParameter("active", active)
                .setParameter("id", regUser.getId())
                .executeUpdate();
        } catch (Exception e) {
            return -1;
        }
        
    }

    public int remove(String wordId, User regUser) {
        try {
            em.createQuery("DELETE Word w WHERE w.id = :wordId AND w.user=:regUser")
                .setParameter("wordId", new Long(wordId))
                .setParameter("regUser", regUser)
                .executeUpdate();
            return 1;
        } catch (Exception e) {
            return -1;
        }
            
    }
}
