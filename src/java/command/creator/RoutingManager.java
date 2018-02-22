/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.creator;

import java.util.ResourceBundle;

/**
 *
 * @author Melnikov
 */
public class RoutingManager {
    private final static ResourceBundle rout = ResourceBundle.getBundle("routing.route");
    private RoutingManager(){}
    public static String getRoute(String key){
        return rout.getString(key);
    }
}
