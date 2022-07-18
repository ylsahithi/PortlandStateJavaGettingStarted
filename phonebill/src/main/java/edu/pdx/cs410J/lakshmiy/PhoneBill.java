package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.*;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  /**
   * customer name is the private member of the class
   * array list named phone log is created to store multiple phone calls
   */
  private final String customer;
  private ArrayList phoneLog = new ArrayList();

  public PhoneBill(String customer) {
    this.customer = customer;

  }

  /**
   * custom constructor assign values to private variables of the class
   */
  public PhoneBill(String customer, PhoneCall call){
    this.customer = customer;
    addPhoneCall(call);
  }


  /**
   * get function to return customer name
   */
  @Override
  public String getCustomer() {
    return this.customer;
  }


  /**
   * Function to add phone call to the class object
   */
  @Override
  public void addPhoneCall(PhoneCall call) {
    this.phoneLog.add(call);

  }


  /**
   * get function to return phone call array list
   */
  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    Collections.sort(phoneLog,comparecalls);
    System.out.println(comparecalls);
    return this.phoneLog;
  }

  public static Comparator<PhoneCall> comparecalls  = new Comparator<PhoneCall> (){
  @Override
    public int compare(PhoneCall c1, PhoneCall c2){
    Date begin1 = c1.getBeginTimeDate();
//    Date end1 = c1.getEndTimeDate();
    Date begin2 = c2.getBeginTimeDate();
//    Date end2 = c2.getEndTimeDate();
    String caller1 = c1.getCaller();
    String caller2 = c2.getCaller();
    if(begin1.compareTo(begin2) == 0) {
        return caller1.compareTo(caller2);
      }
    else {
      return begin1.compareTo(begin2);
    }
  }
  };

}
