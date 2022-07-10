package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class TextParser implements PhoneBillParser<PhoneBill> {
  private final Reader reader;
  private String customer;
//  private String filename;

  /**
   * Customer is added as parameter because if the file is null,
   * we need to pass customer name for phone bill object creation
   * @param reader
   * @param customer
   * @throws IOException
   */
  public TextParser(Reader reader,String customer)  {
    this.reader = reader;
     this.customer = customer;
    }

  public TextParser(Reader reader) throws IOException {
    String line = "";
    this.reader = reader;
    ArrayList<String> linesList = new ArrayList<>();
    BufferedReader br = new BufferedReader(this.reader);
    while ((line = br.readLine()) != null) {
      linesList.add(line);
    }
    this.customer = linesList.get(0).split(",")[0];
  }

  @Override
  public PhoneBill parse() throws ParserException {
    String line = "";
    PhoneBill bill = new PhoneBill(this.customer);
    ArrayList<String> linesList = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(this.reader);
      while ((line = br.readLine()) != null) {
//        System.out.println("lines in parsers");
//        System.out.println(line);
        linesList.add(line);
      }
      if (linesList == null) {
        System.err.println("File is empty no lines found");
        return new PhoneBill(this.customer);
      }
      if(customer.length() == 0){
        this.customer = linesList.get(0).split(",")[0];
      }
//       for (int i = 0; i < linesList.size(); i++){
//          String [] phonebook = linesList.get(i).toString().split(",");
        for (String calls : linesList) {
          String[] phonebook = calls.split(",");
//          for (String l : phonebook) {
//            System.out.println("split" + l);
//          }
          if (phonebook.length >= 7) {
            bill.addPhoneCall(new PhoneCall(phonebook, 0));
//            System.out.println(bill.getCustomer());
          }
          else {
            return bill;
          }
//          this.customer = calls.split(",")[0];
        }
      return bill;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
//    catch ( IOException pe){
//      throw new ParserException("File is empty no lines found");
//    }
  }
}