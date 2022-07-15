package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class TextParser implements PhoneBillParser<PhoneBill> {
  private final Reader reader;
  private String customer;

  /**
   * Customer is added as parameter because if the file is null,
   * we need to pass customer name for phone bill object creation
   * @param reader
   */
  public TextParser(Reader reader,String customer)  {
    this.reader = reader;
    this.customer = customer;
  }

  /**
   * Constructor to add reader to private reader variable
   * @param reader
   * @throws IOException
   */
  public TextParser(Reader reader) throws IOException {
    this.reader = reader;
  }


  /**
   * This method parse the input file passed and created phonebill
   * object out of the input parameters
   * @return PhoneBill object
   * @throws ParserException
   */

  @Override
  public PhoneBill parse() throws ParserException {
    String line = "";
    ArrayList<String> linesList = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(this.reader);
      while ((line = br.readLine()) != null) {
        linesList.add(line);
      }
    }
    catch (Exception e) {
      throw new ParserException("cannot read given file, incorrect file format");
    }
    if (linesList.isEmpty()) {
      System.err.println("File is empty no lines found");
      throw new ParserException("File is empty no lines found");
    }
    if(this.customer != null){
      if(!(this.customer.equalsIgnoreCase(linesList.get(0).split(",")[0]))){
        System.err.println("customer name is different from the entry in file");
        return new PhoneBill(this.customer);
      }
    }
    this.customer = linesList.get(0).split(",")[0];
    PhoneBill bill = new PhoneBill(this.customer);
    for (String calls : linesList) {
      String[] phonebook = calls.split(",");
      if (phonebook.length >= 7) {
        bill.addPhoneCall(new PhoneCall(phonebook, 0));
      }
      else {
        return bill;
      }

    }
    return bill;
  }
}