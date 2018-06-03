/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import classes.RandomWordLocator;
import classes.SessionUserLocator;
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
import session.UserFacade;
import session.WordFacade;

/**
 *
 * @author Melnikov
 */
public class DoChangeWordCommand implements ActionCommand{
private UserFacade userFacade;
private WordFacade wordFacade;

    public DoChangeWordCommand() {
        try {
            Context context;
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DoChangeWordCommand.class.getName()).log(Level.SEVERE, "Не удалось найти бин.", ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {
       
        SessionUserLocator sul = new SessionUserLocator();
        User regUser = sul.getUser(request);
        if(regUser==null){
            return RoutingManager.getRoute("path.page.login");
        }
        String wordId = request.getParameter("word_id");
        String word = request.getParameter("word");
        String trans = request.getParameter("trans");
        String phrases = request.getParameter("phrases");
        if(wordId != null && !wordId.isEmpty()){
            Word editWord=wordFacade.find(new Long(wordId));
            editWord.setWord(word);
            editWord.setTrans(trans);
            editWord.setPhrases(phrases);
            wordFacade.edit(editWord);
        }else{
            request.setAttribute("info", "Возможно не указано слово для изменения!");
        }
        List<Word> words = wordFacade.findAll(regUser);
        if(words.isEmpty()){
            request.setAttribute("info", "Нет ни одного слова для изучения");
            return RoutingManager.getRoute("path.page.memoWords");
        }
        RandomWordLocator rwl =new RandomWordLocator();
        Word w = rwl.getRandomWord(wordId,regUser);
        request.setAttribute("word", w);
        return RoutingManager.getRoute("path.page.memoWords");
        
    }
    
}
