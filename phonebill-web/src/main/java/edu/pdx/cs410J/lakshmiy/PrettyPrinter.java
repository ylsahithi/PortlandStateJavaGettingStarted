package edu.pdx.cs410J.lakshmiy;

import com.google.common.annotations.VisibleForTesting;

import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

public class PrettyPrinter {

  public static String formatphoneBookEntry(PhoneBill bill)
  {
    String res = "";
    res = res + "Call log of Customer : " + bill.getCustomer() + "\n";
    int count = 1;
    SimpleDateFormat prettydate = new SimpleDateFormat("dd-MMM-yy hh:mm a");
    for (PhoneCall call : bill.getPhoneCalls()) {
      res = res + "call :" + count + " Caller number  : " + call.getCaller() + "\n"
              + " Callee number  : " + call.getCallee() + "\n" + " Begin call time : " + prettydate.format(call.getBeginTimeDate()) + "\n"
              + " End call time : " + prettydate.format(call.getEndTimeDate()) + "\n";
      count += 1;
    }
    return res;
  }


  public PrettyPrinter() {
  }

}
