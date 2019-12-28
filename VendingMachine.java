
/**
 * You MUST implement this interface.
 */
interface VendingMachine {
    /**
     * User Function - This is called when a user presses a button for a
     particular product. This is used for both price
     * checking and purchasing.
     */
    void buttonPress(Integer productPosition);
    /**
     * User Function - This is called when the user adds money to the
     machine. The cents parameter represent the value
     * of the particular currency added to the machine. For example,
     when the user adds a Nickel, this function will be
     * called with a value of 5.
     * <p>
     * Note: Only one coin will be added at a time. Only Nickels, Dimes,
     and Quarters will be added.
     */
    void addUserMoney(Integer cents);
}
