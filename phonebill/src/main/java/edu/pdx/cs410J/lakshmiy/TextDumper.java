package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AppointmentBookDumper;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.util.Collection;

public class TextDumper implements PhoneBillDumper<PhoneBill> {
  private final Writer writer;
//  private String fileName;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  public TextDumper(String filename) throws IOException,IllegalArgumentException {
    if(filename.isEmpty() || filename == null){
      throw new IllegalArgumentException("Expected filename but got a null");
    }
    File fle = new File(filename);
    Writer Writ = new FileWriter(fle);
    this.writer = Writ;
//    this.fileName = filename;
    }



  @Override
  public void dump(PhoneBill bill) {
    try {
      Collection<PhoneCall> custlog = bill.getPhoneCalls();
      if(custlog.isEmpty()){
         this.writer.append(bill.getCustomer());
      }
      else {
//      PrintWriter pw = new PrintWriter(this.writer);
//      File tf = new File(this.fileName);
//      FileWriter fw = new FileWriter(tf);
        for (PhoneCall call : custlog) {
//          System.out.println(call.getBeginTimeString().split(" "));
          this.writer.append(bill.getCustomer() + "," + call.getCaller() + "," + call.getCallee() + "," + call.getBeginTimeString().split(" ")[0] + "," +
                  call.getBeginTimeString().split(" ")[1] + "," + call.getEndTimeString().split(" ")[0] + "," + call.getEndTimeString().split(" ")[1] + "\n");
        }
//      pw.println(bill.getCustomer());
      }
      this.writer.flush();
//      pw.close();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
