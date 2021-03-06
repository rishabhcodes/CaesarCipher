package CaesarCipherEncrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

/**
 * This is a wrapper class for CaesarCipher class. Enables the Caesar Cipher class to be triggered in following ways
 * 1. Input a String and key for encryption
 * 2. Input a String for decryption
 * 3. Encrypt a single file and then decrypt the same
 * 4. Encrypt multiple files and then decrypt them
 * 
 * @author risshah
 *
 */

public class ExecuteCaesarCipher {
	
	public static void main(String[] args){
		ExecuteCaesarCipher cc = new ExecuteCaesarCipher();
		Scanner in = new Scanner(System.in);
		
		System.out.println("Please enter the mode ");
		int mode = in.nextInt();
		
		in.nextLine();
		
		if (mode == 1){
			cc.simpleTestOne();
		} else if (mode == 2) {
			cc.simpleTestTwo();
		} else if (mode == 3) {
			cc.simpleTestThree();
		} else if (mode == 4) {
			cc.simpleTestFour();
		}
    }
	

    /**
     * This method takes a string & key as input from user and prints the encrypted String
     * The same encrypted string is then decrypted to produce the original text
     * 
     */
    
    public void simpleTestOne(){
    	
//    	FileResource fr = new FileResource();
//    	String encryption_string = fr.asString();
    	
        //Create a new CaesarCipher object with key as 18
        CaesarCipher cipher = new CaesarCipher();
        
        //Create the scanner object
        Scanner in = new Scanner(System.in);
        
               
        //Ask user for key
        System.out.print("Please enter the key you want to use for encryption ");
        int key = in.nextInt();
        System.out.println();
        
        in.nextLine();
        
        //Ask user for the String to be encrypted
        System.out.println("Please enter the String you wish to encrypt ");
        String s = in.nextLine();
        System.out.println();
        
        //initialize the cipher
        cipher.set_key(key);
        cipher.set_string_under_action(s);
            
        //Get the encrypted String
        String encrypted_string = cipher.encryptString();
              
        //Print the encrypted String
        System.out.println("The original string is "+s);
        System.out.println("The encrypted string is "+encrypted_string);
            
        //Decrypt the encrypted string
        cipher.set_string_under_action(encrypted_string);
        String original_text = cipher.decryptString(1);
        
        //Print the decrypted text
        System.out.println("The decrypted text is "+original_text);
        
    }
    
    /**
     * This method takes a String from user. This string needs to be decrypted. The method figures out the key used for encryption and produces 
     * the decrypted text
     */
    
    public void simpleTestTwo(){
    	
        //Create a new CaesarCipher object with key as 18
        CaesarCipher cipher = new CaesarCipher();
        
        //Create the scanner object
        Scanner in = new Scanner(System.in);
        
        //Ask user for the String to be decrypted
        System.out.println("Please enter the String you wish to decrypt ");
        String s = in.nextLine();
        System.out.println();
        
        //initialize the cipher
        cipher.set_string_under_action(s);
            
        //Get the encrypted String
        String decrypted_string = cipher.decryptString(2);
              
        //Print the encrypted String
        System.out.println("The encrypted string is "+s);
        System.out.println("The decrypted string is "+decrypted_string);

    	
    }
    
    /**
     * This method reads a single file, outputs the encrypted text
     * 
     */
   
    public void simpleTestThree(){
    	FileResource fr = new FileResource();
    	String text_to_encrypt = fr.asString();
    	
        //Create a new CaesarCipher object with key as 18
        CaesarCipher cipher = new CaesarCipher();
        
        //Create the scanner object
        Scanner in = new Scanner(System.in);
        
               
        //Ask user for key
        System.out.print("Please enter the key you want to use for encryption ");
        int key = in.nextInt();
        System.out.println();
        
        in.nextLine();  
        
        //initialize the cipher
        cipher.set_key(key);
        cipher.set_string_under_action(text_to_encrypt);
            
        //Get the encrypted String
        String encrypted_string = cipher.encryptString();
              
        //Print the encrypted String
        System.out.println("The original string is "+text_to_encrypt);
        System.out.println("The encrypted string is "+encrypted_string);
            
        //Decrypt the encrypted string
        cipher.set_string_under_action(encrypted_string);
        String original_text = cipher.decryptString(1);
        
        //Print the decrypted text
        System.out.println("The decrypted text is "+original_text);
    	
    }
    
    /**
     * This method can be used for selecting multiple files, encrypting them and storing them in a new file with the prefix "encrypt" with the file name
     */
    
    public void simpleTestFour(){
        String file_path = "";
        String file_name = "";
        
        //Initialize the scanner
        Scanner in = new Scanner(System.in);
        
        //Ask user for key
        System.out.print("Please enter the key you want to use for encryption ");
        int key = in.nextInt();
        System.out.println();
        
        in.nextLine();   
        
        //Create a new CaesarCipher object with key as 18
        CaesarCipher cipher = new CaesarCipher();
        cipher.set_key(key);
        
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            //Get the file name and file path of the selected file
            file_path = f.getAbsolutePath();
            file_name = f.getName();
            int index = file_path.lastIndexOf(file_name);
            file_path = file_path.substring(0,index);
            
            //Get the file content as a string literal 
            FileResource fr = new FileResource(f);
            String encryption_string = fr.asString();
            
            //Send the encryption string to function encryptString(encryption_string)
            cipher.set_string_under_action(encryption_string);
            String encrypted_string = cipher.encryptString();
            
            //Create a new file at the same location with new file name prefixed with encrypted and write the encrypted contents in the new file
            try {
                String new_file_name = "encrypt_"+file_name;
                FileWriter new_file = new FileWriter(file_path + new_file_name);
                new_file.write(encrypted_string);
                new_file.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }

   
}
