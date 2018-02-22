/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import command.creator.RoutingManager;
import entity.User;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import security.EncriptPass;
import session.UserFacade;

/**
 *
 * @author Melnikov
 */
public class NewUserCommand implements ActionCommand{
private UserFacade userFacade;

    public NewUserCommand() {
        try {
            Context context;
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(NewUserCommand.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("info", "Hello from NewUserCommand");
        String userName = request.getParameter("username");
        String login = request.getParameter("login");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if(!password1.equals(password2)){
            request.setAttribute("info", "Не совпадают пароли");
            request.setAttribute("username", userName);
            request.setAttribute("login", login);
            return RoutingManager.getRoute("path.page.registration");
        }
         
        EncriptPass encriptPass = new EncriptPass();
        String salts = encriptPass.getSalts();
        String password = salts+password1;
        password = encriptPass.getEncriptString(password);
        if(password == null){
            throw new Error("Возникал ошибка, обратитесь к разработчику!");
        }
        User newUser = new User(userName, login, password, salts);
        userFacade.create(newUser);
        return RoutingManager.getRoute("path.page.login");
    }
    
}
