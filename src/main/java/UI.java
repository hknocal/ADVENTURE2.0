import java.util.Scanner;

public class UI {

    private Adventure adventure = new Adventure();
    private Scanner sc = new Scanner(System.in);


    public String getUserInput() {
        return sc.nextLine().toLowerCase();
    }

    public void startUI() {
        System.out.println("Choose a player name:");
        adventure.setName(getUserInput());

        System.out.println("""
                Welcome to our text adventure
                                
                ------------------------------------------
                To navigate within the rooms use the following commands:
                    
                Enter go north if you wish to go north
                Enter go south if you wish to go south
                Enter go east if you wish to go east
                Enter go west if you wish to go west
                    
                -------------------------------------------
                Acces commands:
                    
                Enter inventory if you wish to see current item inventory
                Enter look to see current room and items in the room
                Enter take item if you wish to take an item in your inventory
                Enter drop item if you wish to drop an item in your inventory
                Enter health if you wish to see current player health
                Enter equip if you wish to equip a weapon from your inventory
                Enter attack to attack an enemy                        
                Enter help for list of options and useful information
                -------------------------------------------
                """);
        command();
    }

    private void command() {
        boolean player = true;
        while (player) {
            String[] userInputs = getUserInput().split(" ");
            String command = userInputs[0];
            String userChoice = "";
            if (userInputs.length > 1) {
                userChoice = userInputs[1];
            }

            switch (command) {
                case "go":
                    boolean succes = adventure.go(userChoice);
                    if (succes) {
                        System.out.println("You have gone: " + userChoice);
                    } else {
                        System.out.println("You cannot go that way");
                    }
                    break;
                case "equip":
                    System.out.println("Enter the name of the weapon you wanna equip: ");
                    System.out.println(adventure.inventoryShow());
                    String weaponEquip = sc.nextLine().toLowerCase();
                    adventure.weaponEquip(weaponEquip);
                    System.out.println("You have now equiped: " + weaponEquip);
                    break;
                case "inventory":
                    System.out.println(adventure.inventoryShow());
                    System.out.println("Enter command: ");
                    break;
                case "take":
                    boolean takeSucces = adventure.take(userChoice);
                    if (takeSucces) {
                        System.out.println("You have taken: " + userChoice);
                    } else {
                        System.out.println("You can't take this item");
                    }
                    System.out.println("Enter command: ");
                    break;
                case "drop":
                    boolean dropSucces = adventure.drop(userChoice);
                    if (dropSucces) {
                        System.out.println("You have dropped: " + userChoice);
                    } else {
                        System.out.println("You can't drop this item");
                    }
                    break;
                case "health":
                    System.out.println("Current HP: " + adventure.playerHealth());
                    System.out.println("Enter command: ");
                    break;
                case "eat":
                    if (adventure.getPlayerInventory() != null) {
                        System.out.println("Please enter the name of the food you wish to eat");
                        String foodName = sc.nextLine().toLowerCase();
                        adventure.eat(foodName);
                    } else {
                        System.out.println("You currently dont have any items in your bag");
                    }
                    System.out.println("Enter command: ");
                    break;
                case "attack":
                    adventure.attackEnemy();
                    break;
                case "help":
                    System.out.println("""
                            -------------------------------------------
                            Typing "Look" and pressing enter --> Your character looks around the current room the person is in, highlighting usable object.
                            Typing "Exit" and pressing enter --> Closes the program. Your progress wont be saved!
                            Typing North and pressing enter --> Checks to see if you can move north. Informing the player if its doable.
                            Typing East and pressing enter --> Checks to see if you can move East. Informing the player if its doable.
                            Typing West and pressing enter --> Checks to see if you can move West. Informing the player if its doable.
                            Typing South and pressing enter --> Checks to see if you can move south. Informing the player if its doable.
                            -------------------------------------------
                                    """);
                    System.out.println("Enter command: ");
                    break;
                case "look":
                    System.out.println(adventure.look());
                    System.out.println("Enter command: ");
                    break;
                case "exit":
                    System.out.println("Closing adventure.");
                    player = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    break;
            }
        }
    }
}