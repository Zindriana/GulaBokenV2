public class LogIn {

    private Output out = new Output();

    private Input inp = new Input();

    public User logIn(UserList users){
        User user = null;
        out.logIn(users);
        int chosenUser = inp.chooseInt();
        if (chosenUser == -1 || chosenUser > users.userProfiles.size() ){
            out.adminLogInError();
            return user;
        }
        out.password();
        String loggedPassword = inp.chooseString();
        String password = users.userProfiles.get(chosenUser).getPassword();
        if (loggedPassword.equals(password)){
            out.confirmLogIn(users.userProfiles.get(chosenUser).getUserName());
            user = users.userProfiles.get(chosenUser);
        } else {
            out.wrongPassword();
        }
        return user;
    }
}
