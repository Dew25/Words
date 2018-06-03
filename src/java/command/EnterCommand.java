/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


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
import javax.servlet.http.HttpSession;
import security.EncriptPass;
import session.UserFacade;
import session.WordFacade;

/**
 *
 * @author Melnikov
 */
public class EnterCommand implements ActionCommand{
private UserFacade userFacade;
private WordFacade wordFacade;

    public EnterCommand() {
        try {
            Context context;
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.wordFacade = (WordFacade) context.lookup("java:module/WordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EnterCommand.class.getName()).log(Level.SEVERE, "Не удалось нацти бин.", ex);
        }
    }
    @Override
    public String execute(HttpServletRequest request){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User regUser = userFacade.find(login);
        if(regUser==null){
            request.setAttribute("info", "Нет такого логина или пароля!");
            return RoutingManager.getRoute("path.page.login");
        }
        EncriptPass encriptPass = new EncriptPass();
        String salts = regUser.getSalts();
        password = salts+password;
        password = encriptPass.getEncriptString(password);
        if(password == null){
            throw new Error("Возникал ошибка, обратитесь к разработчику!");
        }
        if(!password.equals(regUser.getPassword())){
            request.setAttribute("info", "Нет такого логина или пароля!");
            return RoutingManager.getRoute("path.page.login");
        }
        
        HttpSession session = request.getSession(true);
        
        session.setAttribute("regUser", regUser);
        
        request.setAttribute("info", "Hello from MemoWordsCommand");
        List<Word> words = wordFacade.findAll(regUser);
        if(words.isEmpty()){
            request.setAttribute("info", "Нет ни одного слова для изучения");
        }else{
            RandomWordLocator rw = new RandomWordLocator();
            request.setAttribute("word", rw.getRandomWord("",regUser));
        }
        return RoutingManager.getRoute("path.page.memoWords");
    }
    
}
