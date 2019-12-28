public enum Electronics {
    AIRPODS     (1, 2.20, "Apple AirPod"),
    SWITCH      (2, 1.35, "Nintendo Switch"),
    CHARGE_CASE (3, 0.95, "Mophie Case"),
    LAPTOP      (4, 1.25, "Asus Laptop"),
    IPHONE      (5, 1.05, "Apple iPhone"),
    CAMERA      (6, 1.35, "Cannon Camera"),
    CONTROLLER  (7, 0.70, "Xbox One Controller"),
    SELFIE      (8, 0.35, "Selfie Stick"),
    DRONE       (9, 2.00, "Dji Mavic Pro"),
    ERROR       (100, 100, "ITEM DOES NOT EXIST, ONLY 1-9 ARE VALID");

    private int slot;
    private double price;
    private String name;

    private Electronics(int slot, double price, String name){
        this.slot = slot;
        this.price = price;
        this.name = name;
    }

    public int getSlot(){
        return this.slot;
    }

    public double getPrice(){
        return this.price;
    }

    public String getName(){
        return this.name;
    }
}
