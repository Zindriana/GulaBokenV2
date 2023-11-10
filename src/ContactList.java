import java.io.*;
import java.util.ArrayList;

public final class ContactList implements ContactMethods, SearchMethods, Serializable { //singleton, ingen annan klass ska ärva

        private Input inp = new Input();
        private Output out = new Output();
        private static ContactList contactList;

        ArrayList<Contact> contactProfiles = new ArrayList<>();

        private ContactList(){}

        public static ContactList getContactList(){
            if (contactList == null){
                contactList = new ContactList ();
            }
            return contactList;
        }

    @Override
    public void addContact() {
        out.chooseFirstName();
        String firstName = inp.chooseString();
        out.chooseSurname();
        String surname = inp.chooseString();
        out.chooseAge();
        int age = inp.chooseInt();
        out.choosePrivatePhoneNumber();
        String privatePhoneNumber = inp.chooseString();
        out.chooseWorkPhoneNumber();
        String workPhoneNumber = inp.chooseString();
        out.chooseAdress();
        String adress = inp.chooseString();
        contactProfiles.add(
                new Contact.ContactBuilder().
                        contactFirstName(firstName).
                        contactSurname(surname).
                        contactAge(age).
                        contactPrivatePhoneNumber(privatePhoneNumber).
                        contactWorkPhoneNumber(workPhoneNumber).
                        contactAdress(adress).newContact());
        out.confirmNewContact(contactProfiles.get(contactProfiles.size()-1));
    }

    @Override
    public void updateContact() {
        contactList.search();
        out.chooseUpdate();
        int updateContact = inp.chooseInt();
        out.chooseFirstName();
        String firstName = inp.chooseString();
        out.chooseSurname();
        String surname = inp.chooseString();
        out.chooseAge();
        int age = inp.chooseInt();
        out.choosePrivatePhoneNumber();
        String privatePhoneNumber = inp.chooseString();
        out.chooseWorkPhoneNumber();
        String workPhoneNumber = inp.chooseString();
        out.chooseAdress();
        String adress = inp.chooseString();
        contactProfiles.get(updateContact).setFirstName(firstName);
        contactProfiles.get(updateContact).setSurname(surname);
        contactProfiles.get(updateContact).setAge(age);
        contactProfiles.get(updateContact).setPrivatePhoneNumber(privatePhoneNumber);
        contactProfiles.get(updateContact).setWorkPhoneNumber(workPhoneNumber);
        contactProfiles.get(updateContact).setAdress(adress);
        out.confirmUpdate(contactProfiles.get(updateContact));

    }

    @Override
    public void deleteContact() {
        contactList.search();
        out.chooseDelete();
        int deleteContact = inp.chooseInt();
        out.confirmDeletion(contactProfiles.get(deleteContact));
        contactProfiles.remove(deleteContact);
    }

    public void search(){
            out.search();
            int type = inp.chooseInt();
            out.chooseSearchTerm();
            String searchTerm = inp.chooseString();
            switch(type){
                case 1: searchBySurname(searchTerm); break;
                case 2: searchByAdress(searchTerm); break;
                case 3: searchByFirstName(searchTerm); break;
                case 4: searchByAny(searchTerm); break;
                default:
            }
    }

    @Override
    public void searchBySurname(String searchTerm) {
        boolean findAny = false;
            for (int i = 0; i<contactProfiles.size(); i++){
            if (searchTerm.equalsIgnoreCase(contactProfiles.get(i).getSurname())){
                out.foundContact(contactProfiles.get(i), contactProfiles);
                findAny = true;
                break;
            }
        }if(!findAny){
            out.noResult();
        }
    }

    @Override
    public void searchByFirstName(String searchTerm) {
        boolean findAny = false;
        for (int i = 0; i < contactProfiles.size(); i++) {
            if (searchTerm.equalsIgnoreCase(contactProfiles.get(i).getFirstName())) {
                out.foundContact(contactProfiles.get(i), contactProfiles);
                findAny = true;
            }
        }if(!findAny){
            out.noResult();
        }
    }

    @Override
    public void searchByAdress(String searchTerm) {
        boolean findAny = false;
        for (int i = 0; i < contactProfiles.size(); i++) {
            if (searchTerm.equalsIgnoreCase(contactProfiles.get(i).getAdress())) {
                out.foundContact(contactProfiles.get(i), contactProfiles);
                findAny = true;
            }
        }if(!findAny){
            out.noResult();
        }
    }

    @Override
    public void searchByAny(String searchTerm) {
            String lowCase = searchTerm.toLowerCase();
            for(int i = 0; i < contactProfiles.size(); i++){
                if (contactProfiles.get(i).getFirstName().toLowerCase().contains(lowCase)
                || contactProfiles.get(i).getSurname().toLowerCase().contains(lowCase)
                || contactProfiles.get(i).getPrivatePhoneNumber().toLowerCase().contains(lowCase)
                || contactProfiles.get(i).getWorkPhoneNumber().toLowerCase().contains(lowCase)
                || contactProfiles.get(i).getAdress().toLowerCase().contains(lowCase))
                {
                    out.foundContact(contactProfiles.get(i), contactProfiles);
                }
            }
    }

    public void saveContacts(String saveFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            out.writeObject(contactProfiles);
        } catch (IOException x) {
            out.exceptionErrorSave();
        }
    }

    public ArrayList<Contact> loadContacts(String loadFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(loadFile))) {
            return (ArrayList<Contact>) in.readObject();
        } catch (IOException | ClassNotFoundException x) {
            out.exceptionErrorLoad();
            return new ArrayList<>();
        }
    }
}
