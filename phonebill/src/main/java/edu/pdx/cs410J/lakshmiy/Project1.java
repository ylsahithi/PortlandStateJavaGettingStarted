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
  public static final String Missing_args = "No args provided to run, required atleast 7 args";
  public static final String Invalid_args = "Invalid arguments passed";
  public static final String Invalid_options = "Invalid options provided as arguments";
  public static final String No_Print_args = "No arguments passed to print";
  public static final String Less_Num_args = "less number of arguments passed cannot execute the class";
  public static final String More_Num_args = "Too Many arguments for the class";



  public static void main(String[] args) {
    System.out.println(args.length);
//   return printErrorMessage("Missing command line arguments");
//    System.exit(1);
//    System.out.println("check line");

//        System.exit(0);

    if(!validateInputArgsCount(args)) {
      return;
    }

//    System.exit(0);
    return;
  }

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
//      System.exit(1);
      return;
    } catch (IOException IO) {
      System.err.println("No file found on source system");
    }
  }

  public static boolean printErrorMessage(String message) {
    System.err.println(message);
//    System.exit(1);
    return false;
  }

  public static boolean validateInputArgsCount(String[] args) {
    if (args.length == 0) {
       return printErrorMessage(Missing_args);

    } else if (args.length == 1) {
      if (args[0].equalsIgnoreCase("-readme")) {
        printREADMEOption();
        return printErrorMessage("No arguments passed other than readme");
//        System.exit(0);
      } else if (args[0].equalsIgnoreCase("-print")) {
       return printErrorMessage(No_Print_args);
      } else {
       return printErrorMessage(Invalid_args);
      }
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("-readme") && args[1].equalsIgnoreCase("-print") ||
              args[0].equalsIgnoreCase("-print") && args[1].equalsIgnoreCase("-readme")) {
        printREADMEOption();

       return printErrorMessage(No_Print_args);
        ///add print method
//        System.exit(0);
      } else {
       return printErrorMessage(Invalid_options);
      }
    } else if (args.length > 9) {
     return printErrorMessage(More_Num_args);
    } else if ((args.length < 7) && (args.length > 2)) {
     return printErrorMessage("");
    } else if (args.length == 7) {
      System.out.println("entered");
      if (args[0].equalsIgnoreCase("-readme") || args[0].equalsIgnoreCase("-print")) {
        if (args[0].equalsIgnoreCase("-readme")){ printREADMEOption();}
        return printErrorMessage(Less_Num_args);
      } else {
        if (!validateEachArg(args)) {
          return printErrorMessage(Invalid_args);
        } else {
          PhoneCall callData = new PhoneCall(args);
          PhoneBill cust = new PhoneBill(args[0], callData);
        }
      }
  } else if (args.length == 8) {
//      Fill it up with validity
      if (args[0].equalsIgnoreCase("-readme") || args[0].equalsIgnoreCase("-print")) {
        if (!validateEachArg(args)) {
          return printErrorMessage(Invalid_args);
        } else {
          PhoneCall callData = new PhoneCall(args);
          PhoneBill cust = new PhoneBill(args[1], callData);
          if (args[0].equalsIgnoreCase("-print")) {
            for (String arg : args) {
              System.out.println(arg);
              System.out.println(callData.toString());
            }
          } else if (args[0].equalsIgnoreCase("-readme")) {
            printREADMEOption();
          }
        }
      } else {
        printErrorMessage(Invalid_args);
      }
    }
      else if (args.length == 9) {
//        System.out.println("enteres 9 case");
        if (!(args[0].equalsIgnoreCase("-readme") || args[0].equalsIgnoreCase("-print"))) {
         return printErrorMessage(Invalid_options);
        } else if ((args[1].equalsIgnoreCase("-print") || args[1].equalsIgnoreCase("-readme"))) {
          printREADMEOption();
        }
        else {
          printErrorMessage(Invalid_options);
        }
      }
    return true;
  }

  @VisibleForTesting
  public static boolean validateEachArg(String[] args) {
//    boolean begindate = checkForValidDate(args[3]+" "+args[4]);
//    boolean enddate = checkForValidTime(args[6]);
//    System.out.println(enddate);
    if (args.length == 7){
//      System.out.println(checkForValidDate(args[3]);
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

  @VisibleForTesting
  public static boolean checkForvalidString(String name) {
    if (name.trim().isEmpty() || name.length() == 1 || (name.replaceAll("[^a-zA-Z]", "").length() == 0)) {
     return printErrorMessage("Invalid customer name");
//      return false;
    }
    return true;
  }

  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    if (phoneNumber.length() < 10) {
      printErrorMessage("Invalid phone number, number of digits less than 10");
//      return false;
    } else if (phoneNumber.startsWith("0")) {
      printErrorMessage(" Invalid phone number, a phone number cannot start with zero");
//      return false;
    } else if (!phoneNumber.matches("[0-9]+")) {
      printErrorMessage("Invalid input for phone number, it cannot contain letters");
//      return false;
    }
    return true;
  }

  @VisibleForTesting
  public static boolean checkForValidDate(String date) {
    try {
//      String input = "03/03/2022 12:10";
      SimpleDateFormat validFormat = new SimpleDateFormat("MM/dd/yyyy");
      Date formattedDate = validFormat.parse(date);
      System.out.println(formattedDate);
      if (date.equals(validFormat.format(formattedDate))) {
        return true;
      } else {
        return printErrorMessage("Invalid input for date");
      }
    } catch (ParseException PE) {
     return printErrorMessage("Invalid input for date");
//      System.exit(1);
//      return false;
    }
  }

  public static boolean checkForValidTime(String time) {
    try {
      String[] hourMin = time.split(":");
//      System.out.println(hourMin[0]);
      if ((Integer.parseInt(hourMin[0]) < 24) && (Integer.parseInt(hourMin[1]) < 60)) {
//         System.out.println("if in");
        return true;
      }
      return printErrorMessage("Invalid input for time");
    } catch (Exception e) {
      return false;
    }
  }
}



