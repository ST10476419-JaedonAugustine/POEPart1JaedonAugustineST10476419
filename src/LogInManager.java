
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jaedon
 */
//#
public class LogInManager
{

    // Creating global objects of the application's main UI components
    static StartPage SPobj = new StartPage();
    static LogIn LIobj = new LogIn();
    static SignUp SUobj = new SignUp();
    static MessageUI MUI = new MessageUI();

    // Global error message holders
    private String ErrorU = "";
    private String ErrorP = "";
    private String ErrorPh = "";
    private String ErrorLI = "";

    // Number of users currently loaded from file
    private int size = 0;

    // Arrays to store user credentials (basic in-memory database)
    private String[] username = new String[100];
    private String[] password = new String[100];
    private String[] phone = new String[100];

    // Loads users from Users.txt and populates in-memory arrays
    public void CurrentUser()
    {
        try
        {
            Scanner sc = new Scanner(new File("Users.txt"));

            while (sc.hasNext())
            {
                String line = sc.nextLine();
                String[] parts = line.split(",");

                // Ensure correct format: username,phone,password
                if (parts.length == 3)
                {
                    username[size] = parts[0];
                    phone[size] = parts[1];
                    password[size] = parts[2];
                    size++;
                }
            }

            sc.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Validates username rules and checks for uniqueness
    public boolean CheckUsername()
    {
        ErrorU = "";  // Reset error string
        boolean CompleteU = true;

        String userInput = SUobj.getUsernameOut();

        // Username cannot contain commas (used as delimiter)
        if (userInput.contains(","))
        {
            CompleteU = false;
            ErrorU += "UserName cannot contain ','\n";
        }

        // Username must contain underscore
        if (!userInput.contains("_"))
        {
            CompleteU = false;
            ErrorU += "UserName must contain an underscore '_'\n";
        }

        // Username should be no longer than 5 characters (may need revision)
        if (userInput.length() > 5)
        {
            CompleteU = false;
            ErrorU += "UserName must not be longer than 5 characters\n";
        }

        // Check if username already exists
        for (int i = 0; i < size; i++)
        {
            if (userInput.equals(username[i]))
            {
                CompleteU = false;
                ErrorU += "UserName already exists\n";
                break;
            }
        }

        return CompleteU;
    }

    // Validates password format, length, and matching confirmation
    public boolean CheckPasswordComplexity()
    {
        ErrorP = "";  // Reset error string
        boolean CompleteP = true;

        String pass = SUobj.getPasswordOut();
        String confirm = SUobj.getPasswordCheckOut();

        // Password must be at least 8 characters
        if (pass.length() < 8)
        {
            CompleteP = false;
            ErrorP += "Password must be at least 8 characters\n";
        }

        // Passwords must match
        if (!pass.equals(confirm))
        {
            CompleteP = false;
            ErrorP += "Passwords do not match\n";
        }

        // Check for uppercase letter
        boolean hasUpperCase = false;
        for (char ch : pass.toCharArray())
        {
            if (Character.isUpperCase(ch))
            {
                hasUpperCase = true;
                break;
            }
        }

        if (!hasUpperCase)
        {
            CompleteP = false;
            ErrorP += "Password must contain at least one uppercase letter\n";
        }

        // Check for digit
        boolean hasNumber = false;
        for (char ch : pass.toCharArray())
        {
            if (Character.isDigit(ch))
            {
                hasNumber = true;
                break;
            }
        }

        if (!hasNumber)
        {
            CompleteP = false;
            ErrorP += "Password must contain at least one number\n";
        }

        // Check for special character (not letter or digit)
        boolean hasSpecial = false;
        for (char ch : pass.toCharArray())
        {
            if (!Character.isLetterOrDigit(ch))
            {
                hasSpecial = true;
                break;
            }
        }

        if (!hasSpecial)
        {
            CompleteP = false;
            ErrorP += "Password must contain at least one special character\n";
        }

        return CompleteP;
    }

    // Validates phone number format: South African format with international code
    public Boolean CheckCellPhoneNumber()
    {
        ErrorPh = "";  // Reset error string
        boolean CompletePh = true;

        // +27 followed by 9 digits (allows optional spaces)
        if (!SUobj.getPhoneOut().matches("^\\+27(\\s?\\d){9}$"))
        {
            CompletePh = false;
            ErrorPh = "Cellphone number incorrectly formatted or missing international code\n";
        }

        return CompletePh;
    }

    // Returns combined error messages from registration validations
    public String reisterUser()
    {
        return ErrorU + "\n" + ErrorP + "\n" + ErrorPh;
    }

    // Writes new valid user data to Users.txt
    public void Newuser()
    {
        // Load existing users
        CurrentUser();

        // Validate input once and store results
        boolean validUsername = CheckUsername();
        boolean validPassword = CheckPasswordComplexity();
        boolean validPhone = CheckCellPhoneNumber();

        if (validUsername && validPassword && validPhone)
        {
            try
            {
                FileWriter writer = new FileWriter("Users.txt", true);

                // Append new user in format: username,phone,password
                writer.write("\n" + SUobj.getUsernameOut() + "," + SUobj.getPhoneOut() + "," + SUobj.getPasswordOut());
                writer.close();

                System.out.println("Successfully added to the file.");
            } catch (Exception e)
            {
                System.out.println("An error occurred: " + e.getMessage());
            }

            // Close signup and show login screen
            SUobj.dispose();
            LIobj.setVisible(true);
        } else
        {
            // Debug output
            System.out.println(validUsername + " " + validPassword + " " + validPhone);
        }
    }

    // Checks login credentials against user database
    public Boolean loginUser()
    {
        CurrentUser();  // Load users

        boolean foundUsername = false;

        for (int i = 0; i < size; i++)
        {
            if (LIobj.getUsernameOut().equals(username[i]))
            {
                foundUsername = true;

                if (LIobj.getPhoneOut().equals(phone[i]))
                {
                    if (LIobj.getPasswordOut().equals(password[i]))
                    {
                        return true;  // Login successful
                    } else
                    {
                        ErrorLI = "Incorrect password";
                        return false;
                    }
                } else
                {
                    ErrorLI = "Phone number does not match username";
                    return false;
                }
            }
        }

        if (!foundUsername)
        {
            ErrorLI = "Username does not exist";
        }

        return false;
    }

    // Returns appropriate message after login attempt
    public String returnLoginStatus()
    {
        String message;

        if (!loginUser())
        {
            message = ErrorLI;
        } else
        {
            // Show message UI and close login window
            MUI.setVisible(true);
            LIobj.dispose();
            message = "Welcome " + LIobj.getUsernameOut() + ". This is your phone number: " + LIobj.getPhoneOut();
        }

        return message;
    }

    // Program entry point
    public static void main(String[] args)
    {
        SPobj.setVisible(true);  // Show the Start Page
    }
}

//-------------------------------------References:-------------------------------------------\\
/*
OpenAI. (2023). ChatGPT (Mar 14 version) [Large language model]. https://chat.openai.com/chat

Prompt:
        What is regex?
Response:
        🔍 What is Regex?
        Regex (short for Regular Expression) is a pattern-matching language used to search, match, 
        and manipulate text. It's like a powerful search tool built into programming languages — including Java.

        📌 Basic Example in Java:
        String input = "Hello123!";
        boolean hasDigit = input.matches(".*\\d.*");  // true if input contains a digit
        
        The pattern .*\\d.* means:
            .* = any characters (zero or more)
            \\d = any digit

        So the full pattern means: "any text that contains at least one digit"

        ✅ Common Regex Patterns:

        Pattern	Meaning	Example Match
            \\d	A digit (0–9)	4
            \\w	A word character (a–z, A–Z, 0–9, _)	a, Z, 9
            \\s	A whitespace character	space, tab
            .	Any character (except newline)	a, !, 4
            [A-Z]	Any uppercase letter	A, B, Z
            [^a-zA-Z0-9]	Any special character (not a letter or digit)	@, #, !
 */
