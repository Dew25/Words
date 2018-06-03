/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import classes.ActivatorWord;
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
import session.WordFacade;

/**
 *
 * @author Melnikov
 */
public class ListWordsCommand implements ActionCommand{

    private WordFacade wordFacade;

    public ListWordsCommand() {
        try {
            Context context;
            context = new InitialContext();
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ListWordsCommand.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
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
        ActivatorWord aw = new ActivatorWord();
        String deactiveWordId = request.getParameter("deactiveWordId");
        if(deactiveWordId != null){
            if(aw.setActive(Boolean.FALSE, deactiveWordId, regUser)>0){
                request.setAttribute("info", "Слово выучено");
            }
        }
        String activeWordId = request.getParameter("activeWordId");
        if(activeWordId != null){
            if(aw.setActive(Boolean.TRUE, activeWordId, regUser)>0){
                request.setAttribute("info", "Слово активировано");
            }
        }
        String deleteWordId = request.getParameter("deleteWordId");
        if(deleteWordId != null){
            if(aw.setActive(Boolean.TRUE, deleteWordId, regUser)>0){
                request.setAttribute("info", "Слово удалено");
            }
        }
        
        List<Word> listWords = wordFacade.findAll(regUser, Boolean.FALSE);
        request.setAttribute("listWords", listWords);
        return RoutingManager.getRoute("path.page.listwords");
    }
    
}
