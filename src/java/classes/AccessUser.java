/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import command.creator.RoutingManager;
import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Melnikov
 */
public class AccessUser {
    public User onAccsess(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return null;
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser==null){
            return null;
        }
        return regUser;
    }
}
