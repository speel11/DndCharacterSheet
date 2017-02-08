package dungeons_and_dragons.util;

import dungeons_and_dragons.entity.Users;

public class UserAuthentication {

    private boolean bUsername = false;
    private boolean bPassword = false;

    public boolean authenticate(String username, String password) {
        
            DatabaseQuery dbQuery = new DatabaseQuery();
            
            //Obtain the user object from dungeons_and_dragons database
            Users user = (Users) dbQuery.executeUserQuery(username);
            
            if(user == null) {
                return false;
            }
            
            //Obtain SHA-256 version of user's password
            Password userPass = new Password(password);
            
            if(username.equalsIgnoreCase(user.getUsername())) {
                bUsername = true;
            }
            if(userPass.hash().equals(user.getPassword())) {
                bPassword = true;
            }
            
        return bUsername&&bPassword;
    }
}
