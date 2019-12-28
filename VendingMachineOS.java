import java.util.Scanner;
public class VendingMachineOS {
    private static boolean Admin = false;
    public static boolean exit = false;
    public static boolean isAdmin(){
        return Admin;
    }
    private static void setPermission(String input){
        if (input.equals("USER")){ Admin = false;}
        else if (input.equals("ADMIN")){Admin = true;}
    }

    public static void main(String[] args){
        System.out.print("\033[H\033[2J");
        ElectronicMachine vendingmachine = new ElectronicMachine();
        String userinput = "";
        Scanner input = new Scanner(System.in);
        while(!exit){
            vendingmachine.inputHandler(userinput);;
            if (!isAdmin()) {
                VendingMachineScreen.mainMenu();
            }
            userinput = input.nextLine();
            userinput = userinput.toUpperCase();
            if(userinput.equals("ADMIN") || userinput.equals("USER")){
                setPermission(userinput);
            }
            else if(userinput.equals("EXIT")) {
                exit = true;
            }
            System.out.print("\033[H\033[2J");
        }
    }
}
