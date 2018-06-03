/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.User;
import entity.Word;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.WordFacade;

/**
 *
 * @author Melnikov
 */
public class RandomWordLocator {
    private WordFacade wordFacade;
    public RandomWordLocator() {
        try {
            Context context;
            context = new InitialContext();
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(RandomWordLocator.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
        }
    }
 
    public Word getRandomWord(String wordId, User regUser){
        List<Word> words = wordFacade.findAll(regUser);
        if(words.size() < 2){
            wordId="";
        }
        Random random = new Random();
        Word word = null;
        do {   
           word = words.get(random.nextInt(words.size()));       
        } while (wordId.equals(word.getId().toString()));
        return word;
    }
}
