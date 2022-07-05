package edu.pdx.cs410J.lakshmiy;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out your program.
 */
public class PhoneCallTest {

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  @Test
  void getBeginTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
//    Exception ex = call.
//    assertThrows(UnsupportedOperationException.class, call.getBeginTimeString());
    assertThat(call.getBeginTimeString(), equalTo("09/10/1993 12:15"));
  }

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  @Test
  void initiallyAllPhoneCallsHaveTheSameCallee() {
    PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
    assertThat(call.getCallee(), containsString("7878787878"));
  }

  @Test
  void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    PhoneCall call = new PhoneCall((new String[]{"sahithi", "8989898989", "7878787878", "","", "10/09/1993", "4:34", "-readme", "-print"}));
    assertThat(call.getBeginTime(), is(nullValue()));
  }

  @Test
  void getEndTimeStringTest() {
    PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
    assertThat(call.getEndTimeString(), equalTo("10/09/1993 4:34"));
  }

  @Test
  void getCallerStringTest() {
    PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
    assertThat(call.getCaller(), equalTo("8989898989"));
  }
  
}
