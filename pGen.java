import java.util.Scanner;

public class PassGen2 {

  private static String[] previousInput;
  private static int numGens = 5;
  private static boolean previousGen = false;
  private static String linebreak = "_________________________________________________":
  private static String advanced = 
    "	This program generates passwords. It has two main modes, "+
    "generating completely random passwords with given specifications and generating passwords "+
    "that are derived from some phrase that you give it. \n\n 	GENERAL: this program can take "+
    "multiple inputs from the user. To destinguish between inputs, use the ';' character. Invalid "+
    "inputs won't do anything and you'll be prompted for valid input. \n\n	RANDOM PASSWORDS: the "+
    "default values are length of 10 characters. By using 'r; length' where length is a number, "+
    "you can specify how long you want the random password to be. \n\n	DERIVED PASSWORDS: this "+
    "mode requires a phrase to be converted into something usable as a password. You don't need "+
    "to put the phrase in quotes; everything after the seperating character ';' will be included. "+
    "\n\n	NUMBER OF GENERATIONS: the default number of generations is 5, meaning that the program "+
    "gives you 5 options every time you tell it to generate a password. This number can be changed "+
    "by 's; number', where 'number' is the number of generations you want to be displayed. \n\n	"+
    "REGENERATE: this command tells the program to run again using whatever input you gave it the "+
    "last time it ran. This command has no effect if you call it without previously running either "+
    "a 'r' or 'd' command";

  // main
  public static void main (String[] args) {
    int defaultLength = 10;
    boolean runAgain = true;
    Scanner console = new Scanner(System.in);

    System.out.println("\n\nPassword Generator 2.0\n	Austin Voecks, Spring 2014\n");
    showSyntax(); 
    System.out.println(linebreak);

    while (runAgain == true) {
      System.out.print("Input: ");
      String[] input = console.nextLine().split(";");
      System.out.println();

      //Check if the user wants to use the previous input
      if (input[0].equals("g") && previousGen == true)
        input = previousInput;

      //Remove leading and trailing spaces from the input sections
      for (int i = 0; i < input.length; i++)
        input[i] = input[i].trim();

      //CASES
      //Advanced instructions
      if (input [0].equals("a") ) {
        System.out.println("Full Instructions:\n " + advanced);
        previousGen = false;
      }
      //Reshow the syntax chart
      else if (input[0].equals("x") ){
        showSyntax(); 
        previousGen = false;
      }
      //Change number of generations
      else if (input[0].equals("s") && input.length == 2) {
        numGens = Integer.parseInt(input[1]);
        System.out.println("New number of generations set");
        previousGen = false;
      }
      //Exit or no input	
      else if (input[0].equals("e") || input.length == 0) {
        System.out.println("Exiting\n");
        System.exit(1);
      }
      //Random Password 
      else if (input[0].equals("r") ){

        //No further input
        if (input.length == 1) {
          System.out.println("Random Password of default length");

          for (int i = 1; i < numGens+1; i++) {
            System.out.println("		"+ randomPassword( defaultLength, 60, 20, 20 ) ); 
            if (i % 5 == 0) System.out.println(); 
          }
          previousGen = true;
        }

        //Random with given length
        else if (input.length == 2) {
          System.out.println("Random Password of length: "+ input[1]);

          for (int i = 1; i < numGens+1; i++) {
            System.out.println("		"+ randomPassword( Integer.parseInt(input[1]), 60, 20, 20 ) ); 
            if (i % 5 == 0) System.out.println(); 
          }
          previousGen = true;
        }
        //Random with given length and custom weights
        else if (input.length == 5) {
          System.out.println("Random Password of custom length and custom weights");

          for (int i = 1; i < numGens+1; i++) {
            System.out.println("		"+ randomPassword( 
                  Integer.parseInt(input[1]), 
                  Integer.parseInt(input[2]), 
                  Integer.parseInt(input[3]), 
                  Integer.parseInt(input[4]) ) ); 	

            if (i % 5 == 0) System.out.println();
          }	
          previousGen = true;
        }

        //Derived Password
      } else if (input[0].equals("d") ) {

        //Given string
        if (input.length == 2) {
          System.out.println("Derived Password");

          for (int i = 1; i < numGens+1; i++) {
            System.out.println("		"+ derivedPassword( input[1], 75, 15, 10 ) );
            if (i % 5 == 0) System.out.println();
          }
          previousGen = true;
        }
        //Given string and all custom weights
        else if (input.length == 5) {
          System.out.println("Derived Password with custom weights");

          for (int i = 1; i < numGens+1; i++) {
            System.out.println("		"+ derivedPassword( 
                  input[1], 
                  Integer.parseInt(input[2]), 
                  Integer.parseInt(input[3]), 
                  Integer.parseInt(input[4])) );

            if (i % 5 == 0) System.out.println();
          }
          previousGen = true;
        }
      }
      System.out.println(linebreak);
      previousInput = input;		
    }
  }

  //derivedPassword
  //This generates a password based off of the input of the user and optional weights
  private static String derivedPassword (String input, int A, int B, int C) { 

    double total = A + B + C;
    int defaultLetter = (int) ((double)A / total * 100 );
    int defaultNumber = (int) ((double)(A + B) / total * 100 );
    int defaultSymbol = (int) ((double)(A + B + C) / total * 100 );

    String finish =""; 
    int length = input.length(); 
    String[] password = new String[length]; 

    //Move the input into an array of characters
    for (int i = 0; i < length; i++) password[i] = Character.toString( input.charAt(i) ); 

    for (int i = 0; i < length; i++) { 
      double check = Math.random()*100;

      //All spaces are replaced with _'s 
      if ( password[i].charAt(0) == ' ') password[i] = "_"; 

      else { 
        //Letters 
        if (check < defaultLetter )  
          password[i] = randomCase(password[i]); 
        //Numbers 
        else if (check > defaultLetter && check < defaultNumber)  
          password[i] = randomNumber(); 
        //Symbols 
        else if (check > defaultNumber && check < defaultSymbol) 
          password[i] = randomSymbol(); 
      }
    }

    //Construct finished password
    for (int i = 0; i < length; i++) finish += password[i]; 

    return finish; 
  } 


  //randomPassword
  //This generates the random password with optional length and weight parameters
  private static String randomPassword (int length, int A, int B, int C ) { 
    String[] password = new String[length]; 
    String finish =""; 

    double total = A + B + C;
    int defaultLetter = (int) ((double)A / total * 100 );
    int defaultNumber = (int) ((double)(A + B) / total * 100 );
    int defaultSymbol = (int) ((double)(A + B + C) / total * 100 );

    for (int i = 0; i < length; i++) { 
      double check = Math.random()*100; 

      //Letters 
      if (check < defaultLetter )  
        password[i] = randomLetter();
      //Numbers 
      else if (check > defaultLetter && check < defaultNumber)  
        password[i] = randomNumber(); 
      //Symbols 
      else if (check > defaultNumber && check < defaultSymbol) 
        password[i] = randomSymbol(); 
    } 

    //Construct the finished password
    for (int i = 0; i < length; i++) finish += password[i]; 

    return finish; 
  } 

  //randomLetter
  //Returns a random letter a - z, A - Z as a string
  private static String randomLetter () { 
    double check = Math.random(); 
    int rand; 

    //Uppercase 
    if (check > 0.5) rand = (int)Math.floor( 65 + ( Math.random() * 25 ) );  
    //Lowercase 
    else rand = (int)Math.floor( 97 + ( Math.random() * 25 ) );         

    return Character.toString( (char)rand ); 
  } 

  //randomNumber
  //Returns one random number from 0 - 9, inclusive, as a string
  private static String randomNumber () { 
    return Integer.toString( (int)Math.floor( Math.random() * 9) ); 
  } 

  //randomSymbol
  // Returns a one random symbol as a string. The weighting is 
  // set to 0.2631 so all symbols have equal weight
  private static String randomSymbol () { 
    double check = Math.random(); 
    int rand; 

    // ! through / ( 33 to 47 ) 
    if (check > 0.2631) rand = (int)Math.floor( 33 + ( Math.random() * 14 ) );  
    // [ through ' ( 91 to 96 ) 
    else  rand = (int)Math.floor( 91 + ( Math.random() * 5 ) );         

    return Character.toString( (char)rand );    
  } 

  //randomCase
  //Returns the input string either uppercase or lowercase
  private static String randomCase (String input) { 
    double check = Math.random(); 
    String temp = input; 

    //Upper 
    if ( check >.5 )  temp = input.toUpperCase(); 
    //Lower 
    else  temp = input.toLowerCase(); 

    return temp; 
  } 

  //showSyntax
  //Prints the input syntax to the console
  private static void showSyntax () {
    System.out.println(
        "Input syntax:\n	Information & Instructions: 'a' \n"+
        "	Reshow Syntax: 'x' \n	Set Number Generations: 's; number' \n"+
        "	Random Password: 'r' OR 'r; length' OR 'r; length; letter weight; "+
        "number weight; symbol weight' \n"+
        "	Derived Password: 'd; phrase' OR 'd; phrase; letter weight; number "+
        "weight; symbol weight; \n	Regenerate: 'g' \n	Exit: 'e' ");
  }
}

