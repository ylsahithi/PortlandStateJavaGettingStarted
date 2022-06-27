package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private final String customer;
  private ArrayList phoneLog = new ArrayList();

  public PhoneBill(String customer) {
    this.customer = customer;

  }

  public PhoneBill(String customer, PhoneCall call){
    this.customer = customer;
    addPhoneCall(call);
  }

  @Override
  public String getCustomer() {
    return this.customer;
  }

  @Override
  public void addPhoneCall(PhoneCall call) {
    this.phoneLog.add(call);
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return this.phoneLog;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
