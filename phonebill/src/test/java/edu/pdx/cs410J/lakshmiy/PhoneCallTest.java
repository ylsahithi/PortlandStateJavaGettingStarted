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
     * This unit test will test get begin time function by passing args
     */
    @Test
    void getBeginTimeStringNeedsToBeImplemented() {
        PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
        assertThat(call.getBeginTimeString(), equalTo("09/10/1993 12:15"));
    }

    /**
     * This unit test will test get callee function by passing args
     */
    @Test
    void initiallyAllPhoneCallsHaveTheSameCallee() {
        PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
        assertThat(call.getCallee(), containsString("7878787878"));
    }

    /**
     * This unit test is expected to pass even begin time is null,
     * so null is passed to begin time to check if it returns true
     */
    @Test
    void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
        PhoneCall call = new PhoneCall((new String[]{"sahithi", "8989898989", "7878787878", "","", "10/09/1993", "4:34", "-readme", "-print"}));
        assertThat(call.getBeginTimeString(), is(nullValue()));
    }

    /**
     * This unit test will test get end time function by passing args
     *
     */
    @Test
    void getEndTimeStringTest() {
        PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
        assertThat(call.getEndTimeString(), equalTo("10/09/1993 4:34"));
    }

    /**
     * This unit test will test get caller function by passing args
     *
     */
    @Test
    void getCallerStringTest() {
        PhoneCall call = new PhoneCall(new String[]{"sahithi", "8989898989", "7878787878", "09/10/1993", "12:15", "10/09/1993", "4:34", "-readme", "-print"});
        assertThat(call.getCaller(), equalTo("8989898989"));
    }

}