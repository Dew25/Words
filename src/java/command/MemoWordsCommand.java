/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import classes.ActivatorWord;
import classes.SessionUserLocator;
import classes.RandomWordLocator;
import command.creator.RoutingManager;
import entity.User;
import entity.Word;
import interfaces.ActionCommand;
import java.util.List;
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
public class MemoWordsCommand implements ActionCommand{
    
    private WordFacade wordFacade;

    public MemoWordsCommand() {
        try {
            Context context;
            context = new InitialContext();
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(MemoWordsCommand.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        
        SessionUserLocator sul = new SessionUserLocator();
        User regUser = sul.getUser(request);
        if(regUser == null){
            request.setAttribute("info", "Войдите!");
            return RoutingManager.getRoute("path.page.login");
        }
            
        List<Word> words = wordFacade.findAll(regUser);
        if(words.isEmpty()){
            request.setAttribute("info", "Нет ни одного слова для изучения");
            return RoutingManager.getRoute("path.page.memoWords");
        }
        
        
        String deactiveWordId = request.getParameter("deactiveWordId");
        if(deactiveWordId != null){
            ActivatorWord aw = new ActivatorWord();
            aw.setActive(Boolean.FALSE, deactiveWordId, regUser);
        }
        String wordId = request.getParameter("word_id");
        if(wordId == null){
            wordId="";
        }
        RandomWordLocator rwl =new RandomWordLocator();
        Word word = rwl.getRandomWord(wordId,regUser);
        request.setAttribute("word", word);
        
        return RoutingManager.getRoute("path.page.memoWords");
    }
    
}
