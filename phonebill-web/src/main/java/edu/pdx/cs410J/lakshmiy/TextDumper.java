package edu.pdx.cs410J.lakshmiy;

import java.io.*;
import java.util.Collection;

/**
 * Dumper class to write customer call logs
 */
public class TextDumper {
  private final Writer writer;

  /**
   * This is custom constructor to Dumper class it creates writer object to write call logs
   * @param writer
   */
  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  /**
   * This function recieves phone bill object and writes log to console and to web API
   * @param bill
   */

  public void dump(PhoneBill bill) {
    try {
      PrintWriter PW = new PrintWriter(this.writer);
      Collection<PhoneCall> custlog = bill.getPhoneCalls();
      if(custlog.isEmpty()){
        System.err.println("customer log is empty");
        throw new Exception("customer log is empty");
      }
      else {
        for (PhoneCall call : custlog) {
          PW.println(Messages.definedWordAs(bill.getCustomer(),call.getCallee(),call.getCaller(),call.getBeginTimeString(),call.getEndTimeString()));
//          PW.println(bill.getCustomer() + "," + call.getCaller() + "," + call.getCallee() + "," + call.getBeginTimeString().split(" ")[0] +
//                   "," + call.getBeginTimeString().split(" ")[1] + "," + call.getBeginTimeString().split(" ")[2] + "," + call.getEndTimeString().split(" ")[0] +
//                  "," + call.getEndTimeString().split(" ")[1] + "," + call.getEndTimeString().split(" ")[2] + "\n");
        }
      }
      PW.flush();
      PW.close();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
