//Kända problem:
//Om användaren bara trycker på retur istället för en giltig siffra i
// menyval så beter sig programmet lite konstigt, beroende på vilken meny det görs i.
//OutOfBoundError kan ske ifall användaren försöker uppdatera en kontakt och trycker in något annat än en giltig siffra
//programmet kommer by default tro att användaren har valt -1 i ovanstående fall

public class Main {

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.contactList.contactProfiles = menu.contactList.loadContacts("saveFile");
        menu.mainMenu();
    }
}
