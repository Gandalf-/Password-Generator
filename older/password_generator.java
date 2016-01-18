/*
Password generator
Austin Voecks        Feb, 2014

Description!
This program generates a password. Input a length for a random password or a phrase to be converted
Takes two arguements from the command line:

The first agruement can be either: 1) a number denoting the length of a purely random password, or
                                                             2) a string of words that you want to be converted into a password
                     Note that if you enter a string of words, they must be encased by quotes " ". eg: "Hello"

The second arguement must be a number denoting the number of times you want to generate the
new passwords. eg: 6 gives 6 generations of the password you described (random or derived)

Examples of valid inputs:   5 5, "My dogs name" 10, "Howdy" 83
                                                  
          invalid inputs:  My dogs name 4, Watermelon Apple, 54 5.5
          
Check your passwords' strength here! http://www.passwordmeter.com/
*/

public class PasswordGenerator {
   
   public static void main (String[] args) {
   
      int numGenerations = 5;
      String input;
      
      System.out.println("Generating...");
      
      if (args.length != 2) {
         System.out.println("Invalid number of arguements. Exiting.");
         System.exit(1);
      }
      else{
      
         input = args[0];
         numGenerations = Integer.parseInt(args[1] );
         System.out.println();
      
         boolean isInt;
         boolean tryAgain = true;
         
         if (input.length() != 0) {
         
            try { //Random password
               int userInput = Integer.parseInt(input);
               System.out.print("Password: ");
            
               for (int i = 1; i < numGenerations+1; i++) {
                  if (i == 1) {
                     System.out.println(randomPassword( userInput ) );
                  } 
                  else {
                     System.out.println("          "+ randomPassword( userInput ) );
                  }
                  if (i % 5 == 0) {
                     System.out.println();
                  }
               }
            } 
            catch ( Exception e) { //Derived password
               System.out.print("Password: ");
               
               for (int i = 1; i < numGenerations+1; i++) {
                  if (i == 1) {
                     System.out.println(derivedPassword( input ) );
                  } 
                  else {
                     System.out.println("          "+ derivedPassword( input ) );
                  }
                  if (i % 5 == 0) {
                     System.out.println();
                  }
               }
            }
         }
      }
   }
   
   //-------------------------------------------------------------------------------------------------
   public static String derivedPassword (String input) {
      String finish ="";
      int length = input.length();
      String[] password = new String[length];
      
      for (int i = 0; i < length; i++) {
         password[i] = Character.toString( input.charAt(i) );
      }
      
      for (int i = 0; i < length; i++) {
         double check = Math.random();
         
         if ( password[i].charAt(0) == ' ') {
            password[i] = "_";
         }
         else {
         
            if (check > .3) { //Letters
               password[i] = randomCase(password[i]);
            }
            else if (check > .08 && check < .3) { //Numbers
               password[i] = randomNumber();
            }
            else { //Symbols
               password[i] = randomSymbol();
            }
         }
      }
      
      for (int i = 0; i < length; i++) {
         finish += password[i];
      }
      
      return finish;
   }
   
   //-------------------------------------------------------------------------------------------------
   public static String randomPassword (int length ) {
      String[] password = new String[length];
      String finish ="";
      
      for (int i = 0; i < length; i++) {
         double whichChar = Math.random();
         
         if ( whichChar > 0.4 ) { //Letters
            password[i] = randomLetter();
         }
         else if ( whichChar > 0.2 && whichChar < .4 ) { //Numbers
            password[i] = randomNumber();
         }
         else { //Symbols
            password[i] = randomSymbol();
         }
      }
      
      for (int i = 0; i < length; i++) {
         finish += password[i];
      }
     
      return finish;
   }
   
   //-------------------------------------------------------------------------------------------------
   public static String randomLetter () {
      double check = Math.random();
      int rand;
      
      if (check > 0.5) { //Uppercase
         rand = (int)Math.floor( 65 + ( Math.random() * 25 ) ); 
      }
      else { //Lowercase
         rand = (int)Math.floor( 97 + ( Math.random() * 25 ) );        
      }
      
      return Character.toString( (char)rand );
   }
   
   //-------------------------------------------------------------------------------------------------
   public static String randomNumber () {
      return Integer.toString( (int)Math.floor( Math.random() * 9) );
   }
   
   //-------------------------------------------------------------------------------------------------    
   public static String randomSymbol () {
      double check = Math.random();
      int rand;
      
      if (check > 0.2631) { // ! through / ( 33 to 47 )
         rand = (int)Math.floor( 33 + ( Math.random() * 14 ) ); 
      }
      else { // [ through ' ( 91 to 96 )
         rand = (int)Math.floor( 91 + ( Math.random() * 5 ) );        
      }
      
      return Character.toString( (char)rand );   
   }
   
   //-------------------------------------------------------------------------------------------------
   public static String randomCase (String input) {
      double check = Math.random();
      String temp = input;
      
      if ( check >.5 ) { //Upper
         temp = input.toUpperCase();
      }
      else { //Lower
         temp = input.toLowerCase();
      }
      
      return temp;
   }
   
}// End of file