/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import command.creator.RoutingManager;
import entity.User;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Melnikov
 */
public class AddWordCommand implements ActionCommand{

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
        
        request.setAttribute("info", "Hello from AddWordCommand");
        return RoutingManager.getRoute("path.page.addWord");
    }
    
}
