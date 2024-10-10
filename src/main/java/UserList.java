//Den här klassen skulle kunna implementera ContactMethods ifall det skulle finnas behov
// av att kunna lägga till flera användare.

import java.util.ArrayList;

public final class UserList { //singleton, ingen annan klass ska ärva

    private static UserList userList;
    ArrayList<User> userProfiles = new ArrayList<>();

    private UserList(){
        userProfiles.add(
                new User.UserBuilder().userName("Admin").userPassword("admin").userIsAdmin(true).newUser());
    }

    public static UserList getUserList(){
        if (userList == null){
            userList = new UserList();
        }
        return userList;
    }
}
