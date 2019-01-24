import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab8 {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in); //Scanner mcScanFace - used to scan in user input
        //declare and initialize variables
        boolean again = true;  //used to store the input of whether the user wants to run the program again
        boolean more = false;  //used to store input of whether user wants more data between house and occupation
        int index = 0; //stores index value of the name user selects
        int oHCheck = 0; //used to check validity of choice between house and occupation
   
        // declare & initialize arrays and store data
        String[] sName = { "Harry", "Ron", "Hermione", "Albus", "Draco", "Severus", "Luna" };
        String[] sHouse = { "Gryffindor", "Gryffindor", "Gryffindor", "Gryffindor", "Slytherin", "Slytherin",
                "Ravenclaw" };

        String[] sOccupation = { "Student", "Student", "Student", "Headmaster", "Student", "Professor", "Student" };

        do { //main do while loop
            
            do { //intro and name selection
            printTitle(); //prints title and opening question
            printStudents(sName); //prints list of students
            index = checkName(scan, sName); //check the input for validity and if valid return user input stored in index
            } while (index == -1); // repeat do-while loop if input is invalid (set to -1)
           
            //print the name and clear the line
            System.out.println("Name: " + sName[index]); 
            scan.nextLine(); //clear the next line            
           
            do { //do-while for house/occupation choice; will repeat until the user decides they don't want to ask any more questions   
                do { // do-while for house occupation validity    
                    oHCheck = houseOcc(scan, sName, index); //method call to process and validate choice between house or occupation
                } while (oHCheck == -1); //if the user input is invalid repeat do while loop    
            
                printHouseOcc(sName, sHouse, sOccupation, index, oHCheck);
                more =  moreData(scan, more);
                } while (more == true); //continue asking the user for house or occupation until they choose to stop
            
           again = playAgain(scan, again);
          } while (again == true); //restart the program if the user wants to inquire about another student
    } //end main

    
    
    
    //prints the opening title and first question
    private static void printTitle() {
        System.out.println("Welcome to the Hogwarts Member Database");
        System.out.println("Which member would you like to learn about?");
    } //end printTitle method
      
    //prints the list of students
    private static void printStudents(String[] name ) {
        for (int i=0; i < name.length; i++) {
            System.out.println((i+1) + ")" + name[i]);
            }
        return;
        } //end printStudents method
    
    //checks user input for validity and returns user input if valid otherwise returns -1
    private static int checkName(Scanner scan, String[] name) {
        String checkInput;
       int input;
        try {
            input = scan.nextInt() - 1;
             System.out.println(input);
             checkInput = name[input];  //test to see if user entered valid choice
             System.out.println(name[input]);
        }  catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("That student does not exist. Please try again.");
            return -1;
            
        } catch (InputMismatchException error  ) {
            System.out.println("Please enter a valid number. ");
            scan.nextLine();
            return -1;
        } 
        return input;
    }

    //processes and validates house/occupation choice; returns -1 if invalid, 1 for house and 2 for occupation
    private static int houseOcc(Scanner scan, String[] name, int index) {
    String oHInput = "";
    System.out.println();    
    System.out.println("What would you like to know about " + name[index] + "?");
    System.out.println("You can write \"house\" or \"occuptation\"");
   // scan.nextLine(); //clear the next line
    
    oHInput = scan.nextLine(); //get user input
    oHInput.toLowerCase(); //convert input to lower case
    if (!(oHInput.matches("house") || oHInput.matches("occupation"))) { //if input does not match either option return -1 to have user try again
        System.out.println("Invalid input. Please enter either: \"house\" or \"occupation\"");
        
        return -1;
    } 
    
    if (oHInput.equals("house")) {return 1;}                //what to do if user input is correct
    else if (oHInput.equals("occupation")) {return 2;} 
    else return -1;   
} //end houseOcc method

    //prints the results of user input from the house/occupation question
    private static void printHouseOcc(String[] name, String[] house, String[] occupation, int index, int choice) {
        if (choice == 1) {
            System.out.println(name[index] + " belongs to House " + house[index] + ".");
        } else {
            System.out.println(name[index] + " is a " + occupation[index] + ".");
        }     
    } //end printHouseOcc
  
    //it's a secret to everybody
    private static void secret() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Congratulations Mariah! You found the secret method! :)");
        System.out.println("This was a test to see how closely you examine our code.");
        System.out.println();
        System.out.println("To confirm you've read this, please use the word \"Balderdash\" ");
        System.out.println("in the comments section when you grade this assignment.");
        System.out.println("Press enter to return to the play again question. And thanks for helping us all out!!!");
        scan.nextLine();
    } //end Secret

    //checks to see if user wants more data (either house or occupation) on the currently selected student
    private static boolean moreData(Scanner scan, boolean more) {
        String decision = ""; //declare/initialize the variable to accept user input upon entering method
        do { //do while loop for question prompt and validation
        System.out.println("Would you like to know more? (y/n) ");
        decision = scan.nextLine();
        if (!(decision.equals("y")) && !(decision.equals("n"))) { //validate user input. if anything other than y or n is entered repeat the question
            System.out.println("Invalid input. Please enter \"y\" or \"n\" ");
        }
        } while (!(decision.equals("y")) && (!(decision.equals("n")))); //repeat the loop if we get an invalid response
        
        if (decision.equals("y")) {return true;} //if user enters y return true
        else return false; //n will return false
    }

    //checks to see if the user would like to start over and check on another student
    public static boolean playAgain(Scanner scan, boolean again) {
        String decision = ""; //declare/initialize the variable to accept user input upon entering method
        do { //do while loop for question prompt and validation
            System.out.println("Thanks for playing! Would you like to check on another student? (y/n)");
        decision = scan.nextLine();
        if (decision.equals("secret")) {
            secret(); 
            System.out.println("Thanks for playing! Would you like to check on another student? (y/n)");
            decision = scan.nextLine();
        }
        if (!(decision.equals("y")) && !(decision.equals("n")) && (!(decision.equals("secret")))) { //validate user input. if anything other than y or n is entered repeat the question
            System.out.println("Invalid input. Please enter \"y\" or \"n\" ");
        }
        } while (!(decision.equals("y")) && (!(decision.equals("n")))); //repeat the loop if we get an invalid response
        
        
        if (decision.equals("y")) {return true;} //if user enters y return true
        else return false; //n will return false
        
        
    }

} //end class
