/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.creator;

import command.AddNewWordCommand;
import command.DeleteWordCommand;
import command.AddWordCommand;
import command.AdminCommand;
import command.EmptyCommand;
import command.EnterCommand;
import command.LoginCommand;
import command.LogoutCommand;
import command.MemoWordsCommand;
import command.NewUserCommand;
import command.RegistrationCommand;
import interfaces.ActionCommand;

/**
 *
 * @author Melnikov
 */
public enum CreatorClassEnum {
    EMPTY{{this.command = new EmptyCommand();}},
    LOGIN{{this.command = new LoginCommand();}},
    MEMOWORDS{{this.command = new MemoWordsCommand();}},
    ADDWORD{{this.command = new AddWordCommand();}},
    ADDNEWWORD{{this.command = new AddNewWordCommand();}},
    REGISTRATION{{this.command = new RegistrationCommand();}},
    NEWUSER{{this.command = new NewUserCommand();}},
    ENTER{{this.command = new EnterCommand();}},
    ADMIN{{this.command = new AdminCommand();}},
    LOGOUT{{this.command = new LogoutCommand();}},
    DELETEWORD{{this.command = new DeleteWordCommand();}},
    ;
    ActionCommand command;
    public ActionCommand getActionCommand(){
        return this.command;
    }
}
