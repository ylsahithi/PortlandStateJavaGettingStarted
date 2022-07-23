package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  public PhoneBill parse(String Searchcustomer) throws ParserException {
    String line = "";
    ArrayList<String> linesList = new ArrayList<>();
    ValidateArgs va = new ValidateArgs();
    try {
      BufferedReader br = new BufferedReader(this.reader);
      while ((line = br.readLine()) != null) {
          linesList.add(line);
      }
    } catch (Exception e) {
      throw new ParserException("cannot read given file, incorrect file format");
    }
    if (linesList.isEmpty()) {
      System.err.println("File is empty no lines found");
      throw new ParserException("File is empty no lines found");
    }
//    System.out.println(linesList);
    String customer = linesList.get(0).split(",")[0];
    PhoneBill bill = new PhoneBill(Searchcustomer);
    for (String calls : linesList) {
      String[] phonebook = calls.split(",");
      if (phonebook.length >= 9) {
        boolean checkFlag = va.validateEachArg(phonebook, 0);
        if (checkFlag == true && phonebook[0].equalsIgnoreCase(Searchcustomer)) {
//          System.out.println("inner cond" + phonebook[1]);
            bill.addPhoneCall(new PhoneCall(phonebook, 0));
//          System.out.println(bill.getPhoneCalls());
        }
      } else {
        return bill;
      }
    }
    return bill;
  }
}