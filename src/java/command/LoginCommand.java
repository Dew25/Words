/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import command.creator.RoutingManager;
import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Melnikov
 */
public class LoginCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("info", "Hello from LoginCommand");
        return RoutingManager.getRoute("path.page.login");
    }
    
}
