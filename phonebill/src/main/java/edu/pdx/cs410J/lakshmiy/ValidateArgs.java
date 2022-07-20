package edu.pdx.cs410J.lakshmiy;

import com.google.common.annotations.VisibleForTesting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidateArgs {

    /**
     * This method validates each argument individually and returns true if arguments are valid
     * it checks for valid phone numbers date and time in input args return type is boolean
     * @param args
     *
     */
    @VisibleForTesting
    public boolean validateEachArg(String[] args, int pos) {
            if ((checkForvalidString(args[pos])) && (isValidPhoneNumber(args[pos+1])) && (isValidPhoneNumber(args[pos+2])) && (checkForValidDate(args[pos+3])) && (checkForValidTime(args[pos+4])) && (checkforvalidFormat(args[pos+5]))
                    && (checkForValidDate(args[pos+6])) && (checkForValidTime(args[pos+7])) && (checkforvalidFormat(args[pos+8]))) {
                return true;
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
            System.err.println("Invalid customer name");
            return false;
        }
        return true;
    }


    /**
     * This method validates phone number value of the arguments
     * return type is boolean
     * @param Number
     *
     */
    @VisibleForTesting
    static boolean isValidPhoneNumber(String Number) {
       String phoneNumber = Number.replaceAll("-","");
        if (phoneNumber.length() < 10) {
            System.err.println("Invalid phone number, number of digits less than 10");
            return false;
        } else if (phoneNumber.startsWith("0")) {
            System.err.println(" Invalid phone number, a phone number cannot start with zero");
            return false;
        } else if (!phoneNumber.matches("[0-9]+")) {
            System.err.println("Invalid input for phone number, it cannot contain letters");
            return false;
        }
        return true;
    }


    /**
     * This method validates DAte value in args checks if date format is as expected
     * return type is boolean
     * @param date
     *
     */
    @VisibleForTesting
    public static boolean checkForValidDate(String date) {
        try {
            SimpleDateFormat validFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date formattedDate = validFormat.parse(date);
            if (date.equals(validFormat.format(formattedDate))) {
                return true;
            } else {
                System.err.println("Invalid input for date");
                return false;
            }
        } catch (ParseException PE) {
            System.err.println("Invalid input for date");
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
            System.err.println("Invalid input for time");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @VisibleForTesting
    public static boolean checkforvalidFormat(String time) {
       if(time.equalsIgnoreCase("AM") || time.equalsIgnoreCase("PM")){
//           System.out.println("time valid ");
           return true;
       }
       return false;
    }
}
