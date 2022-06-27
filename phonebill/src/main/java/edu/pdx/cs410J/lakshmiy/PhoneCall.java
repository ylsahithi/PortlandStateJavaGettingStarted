package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class PhoneCall extends AbstractPhoneCall {
  private String callerNumber;
  private String calleeNumber;
  private String begin;
  private String end;

  public PhoneCall (){
  }

  public PhoneCall (String [] args){
    this.callerNumber = args[1];
    this.calleeNumber = args[2];
    this.begin = args[3] +" " +  args[4];
    CheckforDateFormat(this.begin);
    this.end = args[5] +" " +  args[6];
//    CheckforDateFormat(this.);
  }
  @Override
  public String getCaller() {
    return this.callerNumber;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getCallee() {
    return this.calleeNumber;
//    return "This method is not implemented yet";
  }

  @Override
  public String getBeginTimeString() {
    return this.begin;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    return this.end;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  public Date CheckforDateFormat(String date){
    try {
//      String input = "03/03/2022 12:10";
      SimpleDateFormat validFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
      Date formattedDate = validFormat.parse(date);
      return formattedDate;
    }
    catch (ParseException PE){
      System.err.println("Invalid input for date");
      System.exit(1);
    }
    return null;
  }
}
