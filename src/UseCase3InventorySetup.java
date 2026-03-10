public class UseCase3InventorySetup {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("Single Room Available: " + inventory.getAvailability("Single Room"));

        inventory.updateAvailability("Single Room", 4);

        System.out.println("Updated Single Room Available: " + inventory.getAvailability("Single Room"));
    }
}