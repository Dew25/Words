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
public class AdminCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            request.setAttribute("info", "Войдите!");
            return RoutingManager.getRoute("path.page.login");
        }
        request.setAttribute("adminName", regUser.getUserName());
        return RoutingManager.getRoute("path.page.admin");
    }
    
}
