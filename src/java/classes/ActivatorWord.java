/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.SessionUserLocator;
import classes.RandomWordLocator;
import command.creator.RoutingManager;
import entity.User;
import entity.Word;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.WordFacade;

/**
 *
 * @author Melnikov
 */
public class ActivatorWord{

    private WordFacade wordFacade;

    public ActivatorWord() {
        try {
            Context context;
            context = new InitialContext();
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ActivatorWord.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
        }
    }
    

    public int setActive(boolean active, String wordId, User regUser) {
        try {
            if(active){
                Word activeWord = wordFacade.find(new Long(wordId));
                activeWord.setActive(Boolean.TRUE);
                wordFacade.edit(activeWord);
                return 1;
            }else{
                Word deactiveWord = wordFacade.find(new Long(wordId));
                deactiveWord.setActive(Boolean.FALSE);
                wordFacade.edit(deactiveWord);
                return 1;
            }
            
        } catch (Exception e) {
            return -1;
        }
    }
     public int deleteWord(String wordId, User regUser) {
        return wordFacade.remove(wordId,regUser);
     }
        
    
}
