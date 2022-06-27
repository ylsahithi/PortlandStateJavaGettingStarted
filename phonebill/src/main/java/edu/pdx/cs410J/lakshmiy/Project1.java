package edu.pdx.cs410J.lakshmiy;

import com.google.common.annotations.VisibleForTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    if(phoneNumber.length() < 10){
      System.err.println("Invalid phone number, number of digits less than 10");
    }
    if (phoneNumber.startsWith("0")) {
      System.err.println(" Invalid phone number, a phone number cannot start with zero");
    }
    return true;
  }



  public static void main(String[] args) throws IOException {
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
//    System.err.println("Missing command line arguments");
    for (String arg : args) {
//      System.out.println(arg);
    }
    PhoneBill cust = new PhoneBill(args[0]);
    System.out.println(cust.getCustomer());
    PhoneCall callData = new PhoneCall(args);
    isValidPhoneNumber(args[2]);
    cust.addPhoneCall(callData);
    System.out.println(callData.toString());
    System.out.println(callData.getEndTimeString());
    PrintREADMEOption();
  }

  public static void PrintREADMEOption() throws IOException {
    BufferedReader br = null;
    try {
      InputStream ReadmeFile = Project1.class.getResourceAsStream("README.txt");
      InputStreamReader temp = new InputStreamReader(ReadmeFile);
      br = new BufferedReader(temp);
      String lines;
      while ((lines = br.readLine()) != null) {
        System.out.println(lines);
      }
    }
    catch (Exception IO){
      IO.printStackTrace();
    }
    finally {
      br.close();
    }



  }

}