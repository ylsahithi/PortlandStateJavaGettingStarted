package edu.pdx.cs410J.lakshmiy;

import com.google.common.annotations.VisibleForTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {
  /**  Public variables to pass error strings to print error function
   * These variables are used in test case validations
   *
   */
  public static final String Missing_args = "No args provided to run, required atleast 7 args";
  public static final String Invalid_args = "Invalid arguments passed";
  public static final String Invalid_options = "Invalid options provided as arguments";
  public static final String No_Print_args = "No arguments passed to print";
  public static final String Less_Num_args = "less number of arguments passed cannot execute the class";
  public static final String More_Num_args = "Too Many arguments for the class";
  public static final String Readme_txt = "This is a README file! Readme.txt";


  /**
   * author @yalam2@pdx.edu
   * Main function for the application
   * args are in the format : "opt1" " opt2" "name" "callee number"
   * "caller number" "begin time" "end time"
   * -option1 -option2 (-readme -print)
   *  optional args
   * -name
   *  Customer name
   *  -caller number
   *  caller phone number
   *  -callee number
   *    callee phone number
   *  - begin time
   *     Start time for the phone call
   *  - end time
   *      End time for phone call
   * @param args
   *
   *
   */
  public static void main(String[] args) {
    validateInputArgsCount(args);
  }

  /**
   * This function returns void and prints contents of readme file
   * It is called when user arguments has readme option
   * return void
   *
   */
  public static void printREADMEOption(){
    BufferedReader br = null;
    try {
      InputStream ReadmeFile = Project1.class.getResourceAsStream("README.txt");
      InputStreamReader temp = new InputStreamReader(ReadmeFile);
      br = new BufferedReader(temp);
      String lines;
      while ((lines = br.readLine()) != null) {
        System.out.println(lines);
      }
      printErrorMessage(Readme_txt);
      return;
    } catch (IOException IO) {
      System.err.println("No file found on source system");
    }
  }


  /**
   *  This method is used to print error message in the application
   *  @param  message
   *
   */
  public static void printErrorMessage(String message) {
    System.err.println(message);
    return;
  }

  /**
   *  This method validated number of arguments passed by user,
   *  it returns false and appropriate error message if invalid arguments are passed
   *  return type is  boolean
   *  @param  args
   *
   */
  public static void validateInputArgsCount(String[] args) {
    /**
     * If there are no arguments passed to main function
     */
    if (args.length == 0) {
      printErrorMessage(Missing_args);
      return;
    }
    /**
     * If there are only one argument passed if the argument is type
     * of optional arguments it returns error message
     */
    else if (args.length == 1) {
      if (args[0].equalsIgnoreCase("-readme")) {
        printREADMEOption();
        printErrorMessage("No arguments passed other than readme");
        return; }
      else if (args[0].equalsIgnoreCase("-print")) { printErrorMessage(No_Print_args);  return;}
      else {
        printErrorMessage(Invalid_args);
        return; }
    }
    /**
     * If readme is one of the arguments then it prints readme and exit
     * prints read me in system error
     */
    else if (args[0].equalsIgnoreCase("-readme") || args[1].equalsIgnoreCase("-readme")) {
      printREADMEOption();
      printErrorMessage(Readme_txt);
      return;
    }
    /**
     * If more than 9 args are passed
     */
    else if (args.length > 9) {
      printErrorMessage(More_Num_args);
      return;
      /**
       * If more than 2 and less than 7 args are passed
       */
    } else if ((args.length < 7) && (args.length > 2)) {
      printErrorMessage(Less_Num_args);
      return;
      /**
       * If valid arguments are passed in expected order.
       */
    } else if (args.length == 7) {
      if (args[0].equalsIgnoreCase("-print")) {
        printErrorMessage(Less_Num_args);
        return;
      } else {
        if (!validateEachArg(args)) {
          printErrorMessage(Invalid_args);
          return;
        } else {
          PhoneCall callData = new PhoneCall(args,0);
          PhoneBill cust = new PhoneBill(args[0], callData);
          System.err.println(callData.toString());
        }
      }
    } else if (args.length == 8) {
      if (!validateEachArg(args)) {
        printErrorMessage(Invalid_args);
        return;
      } else {
        PhoneCall callData = new PhoneCall(args, 1);
        PhoneBill cust = new PhoneBill(args[1], callData);
        if (args[0].equalsIgnoreCase("-print")) {
          System.out.println(callData.toString());
          printErrorMessage(callData.toString());
        }
        else {
          printErrorMessage(Invalid_options);
        }
      }
    }
    else if (args.length == 9) {
      if (!validateEachArg(args)) {
        printErrorMessage(Invalid_args);
        return;
      } else {
        PhoneCall callData = new PhoneCall(args, 2);
        PhoneBill cust = new PhoneBill(args[2], callData);
        if (args[0].equalsIgnoreCase("-print") || args[1].equalsIgnoreCase("-print") ) {
          System.out.println(callData.toString());
          printErrorMessage(callData.toString());
        }
      }
      if (!(args[0].equalsIgnoreCase("-readme") || args[1].equalsIgnoreCase("-print") ||
              args[1].equalsIgnoreCase("-readme") || args[0].equalsIgnoreCase("-print"))) {
        printErrorMessage(Invalid_options);
      }
    }
    return;
  }


  /**
   * This method validates each argument individually and returns true if arguments are valid
   * it checks for valid phone numbers date and time in input args return type is boolean
   * @param args
   *
   */
  @VisibleForTesting
  public static boolean validateEachArg(String[] args) {
    if (args.length == 7){
      if ((checkForvalidString(args[0])) && (isValidPhoneNumber(args[1])) && (isValidPhoneNumber(args[2])) && (checkForValidDate(args[3])) && (checkForValidTime(args[4]))
              && (checkForValidDate(args[5])) && (checkForValidTime(args[6]))) {
        return true;
      }
    }
    else  if (args.length == 8) {
      if ((checkForvalidString(args[1])) && (isValidPhoneNumber(args[2])) && (isValidPhoneNumber(args[3])) && (checkForValidDate(args[4])) && (checkForValidTime(args[5]))
              && (checkForValidDate(args[6])) && (checkForValidTime(args[7]))) {
        return true;
      }
    }
    else  if (args.length == 9) {
      if ((checkForvalidString(args[2])) && (isValidPhoneNumber(args[3])) && (isValidPhoneNumber(args[4])) && (checkForValidDate(args[5])) && (checkForValidTime(args[6]))
              && (checkForValidDate(args[7])) && (checkForValidTime(args[8]))) {
        return true;
      }
    }
    return false;
  }


  /**
   * This method validates each customer name argument, it checks for valid string as input
   * return type is boolean
   * @param name
   *
   */
  @VisibleForTesting
  public static boolean checkForvalidString(String name) {
    if (name.trim().isEmpty() || name.length() == 1 || (name.replaceAll("[^a-zA-Z]", "").length() == 0)) {
      printErrorMessage("Invalid customer name");
      return false;
//      return false;
    }
    return true;
  }


  /**
   * This method validates phone number value of the arguments
   * return type is boolean
   * @param phoneNumber
   *
   */
  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    if (phoneNumber.length() < 10) {
      printErrorMessage("Invalid phone number, number of digits less than 10");
    } else if (phoneNumber.startsWith("0")) {
      printErrorMessage(" Invalid phone number, a phone number cannot start with zero");
    } else if (!phoneNumber.matches("[0-9]+")) {
      printErrorMessage("Invalid input for phone number, it cannot contain letters");
    }
    return true;
  }


  /**
   * This method validates DAte value in args checks fi date format is as expected
   * return type is boolean
   * @param date
   *
   */
  @VisibleForTesting
  public static boolean checkForValidDate(String date) {
    try {
      SimpleDateFormat validFormat = new SimpleDateFormat("MM/dd/yyyy");
      Date formattedDate = validFormat.parse(date);
      System.out.println(formattedDate);
      if (date.equals(validFormat.format(formattedDate))) {
        return true;
      } else {
        printErrorMessage("Invalid input for date");
        return false;
      }
    } catch (ParseException PE) {
      printErrorMessage("Invalid input for date");
      return false;
    }
  }

  /**
   * This method validates Time value in args checks if it is valid
   * return type is  boolean
   * @param time
   *
   */
  @VisibleForTesting
  public static boolean checkForValidTime(String time) {
    try {
      String[] hourMin = time.split(":");
      if ((Integer.parseInt(hourMin[0]) < 24) && (Integer.parseInt(hourMin[1]) < 60)) {
        return true;
      }
      printErrorMessage("Invalid input for time");
      return false;
    } catch (Exception e) {
      return false;
    }
  }
}



