/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import classes.AccessUser;
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
import javax.servlet.http.HttpSession;
import session.UserFacade;
import session.WordFacade;

/**
 *
 * @author Melnikov
 */
public class EditWordCommand implements ActionCommand{

private WordFacade wordFacade;

    public EditWordCommand() {
        try {
            Context context;
            context = new InitialContext();
            
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EditWordCommand.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
        }
    }
    @Override
    public String execute(HttpServletRequest request) {
        AccessUser au = new AccessUser();
        User regUser = au.onAccsess(request);
        if(regUser == null){
            request.setAttribute("info", "Войдите!");
            return RoutingManager.getRoute("path.page.login");
        }
        String id = request.getParameter("id");
        if(id != null && !id.isEmpty()){
            Word editWord = wordFacade.find(new Long(id));
            request.setAttribute("editWord", editWord);
        }
        return RoutingManager.getRoute("path.page.editWord");
    }
    
}
