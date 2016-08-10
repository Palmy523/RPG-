/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component.user;

import java.io.Serializable;

/**
 *
 * @author Dave
 */
public class User implements Serializable {
    
    private String id;
    private String userName;
    private String firstName;
    private String middleInit;
    private String lastName;
    private GameOptions options;
    
    public User(String id) {
        this.id = id;
    }
    
    
    
}
