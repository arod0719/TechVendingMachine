import java.text.DecimalFormat;
import java.util.HashMap;

public class ElectronicMachine implements VendingMachine, VendingMachineHardwareFunctions, VendingMachineAdmin {

    protected static HashMap<Electronics, Integer> stock = new HashMap<>();
    private static HashMap<String, Integer> currency = new HashMap<>();
    private static HashMap<Integer, Integer> changeamount = new HashMap<>();
    public static double currentMoney = 0.00;
    public static double machineMoney = 0.00;

    ElectronicMachine() {
        fillHash();
    }

    public void showMessage(String message) {
        VendingMachineScreen.getScreenMessage(message);
    }

    public void dispenseProduct(Integer productPosition, String productName) {
        Electronics purchased = transcribe(productPosition);
        stock.replace(purchased, stock.get(purchased) -1);
    }

    public void dispenseChange(Integer changeInCents) {
        int change = ((int)(currentMoney*100)) - changeInCents;
        int amount;
        changeamount.put(25, 0);
        changeamount.put(10, 0);
        changeamount.put(5, 0);
        if (change >= 25) {
            amount = change / 25;
            changeamount.replace(25, changeamount.get(25) + amount);
            change = change - (25 * amount);
        }
        if (change >= 10){
            amount = change / 10;
            changeamount.replace(10, changeamount.get(10) + amount);
            change =  change - (10 * amount);
        }
        if (change >= 5){
            amount = change / 5;
            changeamount.replace(5, changeamount.get(5) + amount);
            change =  change - (10 * amount);
        }
        currentMoney = 0;
    }

    public void buttonPress(Integer productPosition) {
        Electronics requested = transcribe(productPosition);
        if (requested == Electronics.ERROR)
            showMessage(requested.getName());
        else {
            DecimalFormat df = new DecimalFormat("0.00");
            String cost = df.format(requested.getPrice());
            int stocked = stock.get(requested);
            if (currentMoney < requested.getPrice())
                showMessage(requested.getName() + " costs $" + cost + ". There are " + stocked + " in stock");
            else if (stocked < 1)
                showMessage(requested.getName() + " is out of stock!");
            else{
                dispenseProduct(requested.getSlot(), requested.getName());
                dispenseChange((int)((requested.getPrice())*100));
                int quarters = changeamount.get(25);
                int dimes = changeamount.get(10);
                int nickels = changeamount.get(5);
                showMessage(requested.getName() + " has been dispensed for $" + cost + " from slot " + requested.getSlot() +
                        ". Stock: " + (stocked-1));
                machineMoney += requested.getPrice();
                String changeString = "Your change is Quarters x" + quarters + ", Dimes x" + dimes + ", Nickels x" + nickels + ".";
                System.out.println(VendingMachineScreen.decodeMessage(changeString));
                System.out.println(VendingMachineScreen.decodeMessage(""));
            }
        }
    }

    public void addUserMoney(Integer cents) {
        double money = cents;
        currentMoney += money/100;
        showMessage("");
    }

    public void inputHandler(String input) {
        if (!VendingMachineOS.isAdmin()) {
            if (input.isEmpty())
                showMessage("");
            else if (isInt(input))
                buttonPress(Integer.parseInt(input));
            else if (currency.containsKey(input.toUpperCase()))
                addUserMoney(currency.get(input.toUpperCase()));
            else if (input.equals("USER"))
                showMessage("User mode active");
            else
                showMessage("Invalid Input");
        }
        else if (input.equals("ADMIN"))
            showMessage("");
        else if (isInt(input))
            refillProduct(Integer.parseInt(input));
        else if (input.equals("REFILL ALL"))
            refillAll();
        else
            showMessage("Invalid Input");

    }

///////////////// ADMIN ///////////////////////////////
    public void refillProduct(int product) {
        Electronics i = transcribe(product);
        if (i == Electronics.ERROR)
            showMessage(i.getName());
        else if (stock.get(i) != 5){
            int refilled = 5-stock.get(i);
            stock.replace(i, 5);
            showMessage(refilled + " " + i.getName() + "(s) have been refilled");
        }
        else
            showMessage(i.getName() + " is already full");
    }

    public void refillAll() {
        if (isFull())
            showMessage("Everything is already full");
        else{
            for (Electronics i : Electronics.values()){
                stock.replace(i, 5);
                showMessage("Everything has been refilled");
            }
        }
    }

///////////////// HELPER METHODS //////////////////////
    private Electronics transcribe(int number){
        for (Electronics i : Electronics.values()){
            if (i.getSlot() == number)
                return i;
        }
        return Electronics.ERROR;
    }

    private boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void fillHash(){
        for (Electronics i : Electronics.values())
            stock.put(i, 5);
        currency.put("QUARTER", 25);
        currency.put("DIME", 10);
        currency.put("NICKEL", 5);
        currency.put("Q", 25);
        currency.put("D", 10);
        currency.put("N", 5);
    }
    private boolean isFull(){
        for (Electronics i : Electronics.values()){
            if (stock.get(i) < 5)
                return false;
        }
        return true;
    }
}