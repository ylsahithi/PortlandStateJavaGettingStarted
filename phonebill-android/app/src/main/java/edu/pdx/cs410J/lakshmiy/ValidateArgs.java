package edu.pdx.cs410J.lakshmiy;

import android.widget.EditText;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.pdx.cs410J.lakshmiy.PhoneCall;

public class ValidateArgs {

    private String Error_msg;
    public ValidateArgs(){
        this.Error_msg = "";
    }
    /**
     * This method validates each argument individually and returns true if arguments are valid
     * it checks for valid phone numbers date and time in input args return type is boolean
     * @param customer
     * @param callee
     * @param caller
     * @param begin
     * @param end
     * @return
     *
     */
    public String validateEachArg(String customer, String callee, String caller, String begin, String end) {
        String[] start_time = begin.split(" ");
        String [] end_time = end.split(" ");
        if ((checkForvalidString(customer)) && (isValidPhoneNumber(callee)) && (isValidPhoneNumber(caller)) && (checkForValidDate(start_time[0])) && (checkForValidTime(start_time[1])) && (checkforvalidFormat(start_time[2]))
                && (checkForValidDate(end_time[0])) && (checkForValidTime(end_time[1])) && (checkforvalidFormat(end_time[2]))) {
            Date begin_date = parseInputDate(begin);
            Date end_date = parseInputDate(end);
            if(!checkBeginAfterEnd(begin_date,end_date)){
                this.Error_msg = this.Error_msg +  "Input end and begin time are equal or end time is before begin time \n";
                System.err.println("Input end and begin time are equal or end time is before begin time");
                return this.Error_msg;
            }
            return this.Error_msg;
        }
        return this.Error_msg;
    }

    /**
     * This method is used to check for valid arguments in case of -search option present in user arguments
     * @param customer
     * @param begin
     * @param end
     * @return
     */

    public String validateSelectedArg(String customer, String begin, String end) {
        System.out.println(begin + end +" selected args ");
        String[] start_time = begin.split(" ");
        String [] end_time = end.split(" ");
        if ((checkForvalidString(customer)) && (checkForValidDate(start_time[0])) && (checkForValidTime(start_time[1])) && (checkforvalidFormat(start_time[2]))
                && (checkForValidDate(end_time[0])) && (checkForValidTime(end_time[1])) && (checkforvalidFormat(end_time[2]))) {
            Date begin_date = parseInputDate(begin);
            Date end_date = parseInputDate(end);
            if(!checkBeginAfterEnd(begin_date,end_date)){
                this.Error_msg = this.Error_msg +  "Input end and begin time are equal or end time is before begin time \n";
                System.err.println("Input end and begin time are equal or end time is before begin time");
                return this.Error_msg;
            }
            return this.Error_msg;
        }
        return this.Error_msg;
    }

    /**
     * This method validates each customer name argument, it checks for valid string as input
     * return type is boolean
     * @param name
     *
     */

    public boolean checkForvalidString(String name) {
        if (name.trim().isEmpty() || name.length() == 1 || (name.replaceAll("[^a-zA-Z]", "").length() == 0)) {
            this.Error_msg = this.Error_msg +  "Invalid customer name \n";
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

    public boolean  isValidPhoneNumber(String Number) {
        if(Number.isEmpty()||Number == null){
            return false;
        }
        String phoneNumber = Number.replaceAll("-","");
        if (phoneNumber.length() < 10) {
            this.Error_msg = this.Error_msg + "Invalid phone number, number of digits less than 10 \n";
            System.err.println("Invalid phone number, number of digits less than 10");
            return false;
        } else if (phoneNumber.startsWith("0")) {
            this.Error_msg = this.Error_msg + " Invalid phone number, a phone number cannot start with zero \n";
            System.err.println(" Invalid phone number, a phone number cannot start with zero");
            return false;
        } else if (!phoneNumber.matches("[0-9]+")) {
            this.Error_msg = this.Error_msg + "Invalid input for phone number, it cannot contain letters \n";
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
    public  boolean checkForValidDate(String date) {
        if(date.isEmpty()||date == null){
            return false;
        }
        else {
            System.out.println(date);
            try {
                SimpleDateFormat validFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date formattedDate = validFormat.parse(date);
                if (date.equals(validFormat.format(formattedDate))) {
                    return true;
                } else {
                    this.Error_msg = this.Error_msg + "Invalid input for date \n";
                    System.err.println("Invalid input for date");
                    return false;
                }
            } catch (ParseException PE) {
                this.Error_msg = this.Error_msg + "Invalid input for date \n";
                System.err.println("Invalid input for date");
                return false;
            }
        }
    }

    /**
     * This method validates Date value in args checks if it is valid
     * @param date
     * @return
     */

    public  Date  parseInputDate(String date) {
        if(date.isEmpty()||date == null){
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            Date result = df.parse(date);
            DateFormat frmt = DateFormat.getDateTimeInstance();
            System.out.println(result);
            return result;
        }
        catch (ParseException PE) {
            this.Error_msg = this.Error_msg + "Invalid input for date \n";
            System.err.println("Invalid input for date");
            return null;
        }
    }

    /**
     * This method validates Time value in args checks if it is valid
     * return type is  boolean
     * @param time
     *
     */
//    @VisibleForTesting
    public  boolean checkForValidTime(String time) {
        if(time.isEmpty()||time == null){
            return false;
        }
        try {
            System.out.println(time);
            String[] hourMin = time.split(":");
            if ((Integer.parseInt(hourMin[0]) < 24) && (Integer.parseInt(hourMin[1]) < 60)) {
                return true;
            }
            this.Error_msg = this.Error_msg + "Invalid input for time \n";
            System.err.println("Invalid input for time");
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * This method validates Time format value in args checks if it is valid
     * return type is  boolean
     * @param time
     *
     */
    public  boolean checkforvalidFormat(String time) {
        System.out.println(time);
        if(time.isEmpty()||time == null){
            return false;
        }
        if(time.equalsIgnoreCase("AM") || time.equalsIgnoreCase("PM")){
            return true;
        }
        this.Error_msg = this.Error_msg + "Invalid input for time \n";
        System.err.println("Invalid input for time");
        return false;
    }


    /**
     * This method validates Time format if begin time is after end time
     * @param begin
     * @param end
     * @param call
     * @return boolean
     */
    public boolean checkBeginAfterEnd(PhoneCall call, EditText begin, EditText end) {
        Date callbegin = call.getBeginTimeDate();
        Date callend = call.getEndTimeDate();
        if(callbegin.after(callend) || callbegin.equals(callend)){
            System.err.println("Begin time is after or equal to end time");
            begin.setError("Begin time is after or equal to end time");
            end.setError("End time is before or equal to begin time");
            return false;
        }
        return true;
    }

    public boolean checkBeginAfterEnd( Date begin, Date end) {
        if(begin.after(end) || begin.equals(end)){
            System.err.println("Begin time is after or equal to end time");
            return false;
        }
        return true;
    }
}
