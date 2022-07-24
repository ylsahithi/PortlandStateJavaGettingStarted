package edu.pdx.cs410J.lakshmiy;

import java.io.*;
import java.util.Collection;

public class TextDumper {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

//  public void dump(Map<String, String> dictionary) {
//    try (
//      PrintWriter pw = new PrintWriter(this.writer)
//    ){
//      for (Map.Entry<String, String> entry : dictionary.entrySet()) {
//        pw.println(entry.getKey() + " : " + entry.getValue());
//      }
//
//      pw.flush();
//    }
//  }

//  public TextDumper(String filename)  {
//    System.out.println(filename);
//    if (filename.isEmpty() || filename == null) {
//      throw new IllegalArgumentException("Expected filename but got a null");
//    }
//    File fle = new File(filename);
//    if(fle.length() == 0){
//      System.out.println("File is empty creating a file with given name");
//      fle.createNewFile();
//    }
//    FileWriter FW = new FileWriter(filename);
//    this.writer = FW;
//    this.filename = filename;
//  }


  public void dump(PhoneBill bill) {
    try {
      PrintWriter PW = new PrintWriter(this.writer);
//      BufferedWriter bw = new BufferedWriter(FW);
      Collection<PhoneCall> custlog = bill.getPhoneCalls();
      if(custlog.isEmpty()){
        System.err.println("cutomser log is empty");
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
