/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import command.creator.RoutingManager;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Melnikov
 */
public class LogoutCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            request.setAttribute("info", "Вы успешно вышли");
        }
        return RoutingManager.getRoute("path.page.login");
    }
    
}
