import java.util.Scanner;

public class Input {

    private Scanner sc = new Scanner(System.in);

    public int chooseInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException x) {
            System.out.println("Du behöver välja ett heltalsnummer \n");
        } return -1;
    }

    public String chooseString(){
        return sc.nextLine();
    }
}
