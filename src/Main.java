//Kommentera massa stuff

public class Main {

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.contactList.contactProfiles = menu.contactList.loadContacts("test");
        menu.mainMenu();
    }
}
