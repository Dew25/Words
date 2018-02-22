/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

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
public class AddNewWordCommand implements ActionCommand{
private UserFacade userFacade;
private WordFacade wordFacade;

    public AddNewWordCommand() {
        try {
            Context context;
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AddNewWordCommand.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
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
        
        String word = request.getParameter("word");
        String trans = request.getParameter("trans");
        String phrases = request.getParameter("phrases");
        Word newWord = new Word(word, trans, phrases, regUser);
        wordFacade.create(newWord);
        List<Word> words = wordFacade.findAll(regUser);
        request.setAttribute("words", words);
        return RoutingManager.getRoute("path.page.addWord");
        
    }
    
}
