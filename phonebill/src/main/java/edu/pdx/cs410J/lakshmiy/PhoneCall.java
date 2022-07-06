package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class PhoneCall extends AbstractPhoneCall {
  /**
  private member functions for storing args in Phonecall class
   */
  private String callerNumber;
  private String calleeNumber;
  private String begin;
  private String end;

  /**
   Custom constructor with list of strings as input
   it is specific to testing Phonecall Test java class
   as it does not requires options to be passed in arguments
   it assigns input values to private members of class
   */
  public PhoneCall(String[] args) {
    this.callerNumber = args[1];
    this.calleeNumber = args[2];
    this.begin = args[3] + " " + args[4];
    this.end = args[5] + " " + args[6];
  }

  /**
   Custom constructor with list of strings and position of customer name as input (if options present)
   it assigns input values to private members of class
   */
  public PhoneCall(String[] args, int argPos) {
    this.callerNumber = args[argPos+1];
    this.calleeNumber = args[argPos+2];
    this.begin = args[argPos+ 3] + " " + args[argPos + 4];
    this.end = args[argPos + 5] + " " + args[argPos + 6];

  }

  @Override
  /**
   This is get function to return caller number
   */
  public String getCaller() {
    return this.callerNumber;

  }

  @Override
  /**
   This is get function to return callee number
   */
  public String getCallee() {
    return this.calleeNumber;

  }

  @Override
  /**
   This is get function to return begin time
   */
  public String getBeginTimeString(){
    return this.begin;

  }

  @Override
  /**
   This is get function to return end time
   */
  public String getEndTimeString() {
    return this.end;

  }
}


