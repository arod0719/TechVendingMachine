public class VendingMachineScreen {

    protected static void getScreenMessage(String message){
        if (VendingMachineOS.isAdmin()){
            adminScreenMessage(message);
            return;
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("-------------- Welcome to the airport electronic vending machine ---------------");
        System.out.println("----------------- The cheapest vending machine in the world! -------------------");
        System.out.println("-------------------- We accept Nickels, Dimes and Quarters ---------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(decodeMessage(message));
        System.out.printf("-----------------------------Current Balance: $%.2f-----------------------------\n", ElectronicMachine.currentMoney);
        System.out.println("--------------------------------------------------------------------------------");
    }

    private static void adminScreenMessage(String message){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(decodeMessage("ADMIN INTERFACE"));
        System.out.println(decodeMessage(message));
        String stockString = "";
        for (Electronics i : Electronics.values()){
            stockString = stockString + stockToString(i);
            if ((i.getSlot() % 3) == 0){
                System.out.println(decodeMessage(stockString));
                stockString = "";
            }
        }
        System.out.printf("------------------------Current Machine Balance: $%.2f--------------------------\n", ElectronicMachine.machineMoney);
        System.out.println("--------------------------------------------------------------------------------");
    }

    protected static void mainMenu(){
        System.out.println("                                [1]   [2]    [3]");
        System.out.println("                                [4]   [5]    [6]");
        System.out.println("                                [7]   [8]    [9]");
    }

    protected static String decodeMessage(String message){
        int messageLength = message.length();
        if (messageLength != 0){
            int wantedChars = (80 - messageLength -2)/2;
            String sides = new String(new char[wantedChars]).replace("\0", "-");
            return(sides + " " + message + " " + sides);
        }
        String repeated = new String(new char[80]).replace("\0", "-");
        return (repeated);
    }

    protected static String stockToString(Electronics item){
        String answer = "| " + ElectronicMachine.stock.get(item) + " " + item.getName() + "s (" + item.getSlot() + ")|";
        return answer;
    }

}
