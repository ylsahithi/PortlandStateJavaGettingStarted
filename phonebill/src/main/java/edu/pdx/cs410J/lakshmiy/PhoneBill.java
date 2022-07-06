package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

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

  @Override
  /**
   * get function to return customer name
   */
  public String getCustomer() {
    return this.customer;
  }

  @Override
  /**
   * Function to ass phone call to the class object
   */
  public void addPhoneCall(PhoneCall call) {
    this.phoneLog.add(call);

  }

  @Override
  /**
   * get function to return phone call array list
   */
  public Collection<PhoneCall> getPhoneCalls() {
    return this.phoneLog;

  }
}
