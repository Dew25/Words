/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import classes.RandomWord;
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
import javax.servlet.http.HttpSession;
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
        
        HttpSession session = request.getSession(false);
        if(session == null){
            return RoutingManager.getRoute("path.page.login");
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser==null){
            return RoutingManager.getRoute("path.page.login");
        }
        List<Word> words = wordFacade.findAll(regUser);
        RandomWord rw =new RandomWord();
        Word word = rw.getRandomWord(words);
        request.setAttribute("word", word);
        
        return RoutingManager.getRoute("path.page.memoWords");
    }
    
}
