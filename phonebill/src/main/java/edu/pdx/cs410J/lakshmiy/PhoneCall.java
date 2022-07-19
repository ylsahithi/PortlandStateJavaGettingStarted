package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class PhoneCall extends AbstractPhoneCall {
  /**
   * private member functions for storing args in Phonecall class
   */
  private String callerNumber;
  private String calleeNumber;
  private String begin;
  private String end;

  /**
   * Custom constructor with list of strings as input it is specific to testing Phonecall Test java class
   * as it does not requires options to be passed in arguments
   * it assigns input values to private members of class
   * @param args
   */
  public PhoneCall(String[] args) {
    this.callerNumber = args[1];
    this.calleeNumber = args[2];
    this.begin = args[3] + " " + args[4];
    this.end = args[5] + " " + args[6];
    if((getEndTimeDate().equals(getBeginTimeDate())) || (getEndTimeDate().before(getBeginTimeDate()))){
      System.err.println("Input end and begin time are equal or end time is before begin time");
      return;
    }
  }

  /**
   * Custom constructor with list of strings and position of customer name as input (if options present)
   * it assigns input values to private members of class
   * This constructor takes care if options are present and varying total user arguments length
   * @param args
   * @param argPos
   *
   */
  public PhoneCall(String[] args, int argPos) {
    this.callerNumber = args[argPos+1];
    this.calleeNumber = args[argPos+2];
    this.begin = args[argPos+ 3] + " " + args[argPos + 4];
    this.end = args[argPos + 5] + " " + args[argPos + 6];
    if((getEndTimeDate().equals(getBeginTimeDate())) || (getEndTimeDate().before(getBeginTimeDate()))){
      System.err.println("Input end and begin time are equal or end time is before begin time");
      return;
    }
  }


  /**
   * This is get function to return caller number
   *
   */
  @Override
  public String getCaller() {
    return this.callerNumber;

  }


  /**
   * This is get function to return callee number
   *
   */
  @Override
  public String getCallee() {
    return this.calleeNumber;

  }


  /**
   * This is get function to return begin time
   *
   */
  @Override
  public String getBeginTimeString(){
    return this.begin;

  }


  /**
   * This is get function to return end time
   */
  @Override
  public String getEndTimeString() {
    return this.end;

  }

  /**
   * This is get function to return bbegin time parsed in date format
   */
  public Date getBeginTimeDate(){
    return parseInputDate(this.begin);

  }

  /**
   * This is get function to return begin time parsed in date format
   */
  public Date getEndTimeDate(){
    return parseInputDate(this.end);

  }

  /**
   * Calculates duration of phone call
   * @return
   */
  public long calculateDuration(){
    long diff = Math.abs(getBeginTimeDate().getTime() - getEndTimeDate().getTime());
    return (diff / (60 * 1000) );
  }

  /**
   * Parse input date in string format to date
   * @param date
   * @return
   */
  public static Date  parseInputDate(String date) {
    try {
      SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm");
      Date result = df.parse(date);
      return result;
    }
    catch (ParseException PE) {
      System.err.println("Invalid input for date");
      return null;
    }
  }
}


