package edu.pdx.cs410J.lakshmiy;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import static edu.pdx.cs410J.web.HttpRequestHelper.Response;
import static edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class PhoneBillRestClient {

    private static final String WEB_APP = "phonebill";
    private static final String SERVLET = "calls";

  private final HttpRequestHelper http;

    /**
     * Creates a client to the Phone Bil REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public PhoneBillRestClient( String hostName, int port )
    {
      this(new HttpRequestHelper(String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET)));
    }

  /**
   * Used for creating http object for testing purpose
   * @param http
   */
  @VisibleForTesting
  PhoneBillRestClient(HttpRequestHelper http) {
    this.http = http;
  }

  /**
   * Returns the call log of customer based on search dates, If customer name is the only argument passed,
   * it will return all call data for customer
   */
  public String getCallsBetweenDates(ArrayList <String> args) throws IOException, ParserException {
    String customer = args.get(0);
    String start = "";
    String end = "";
    if(args.size() > 1) {
      start = args.get(1) + " " + args.get(2) + " " + args.get(3);
      end = args.get(4) + " " + args.get(5) + " " + args.get(6);
    }
//    System.out.println("customer" + customer +  "start" + start + "end"+ end);
    Response response = http.get(Map.of("customer",customer, "begintime", start, "endtime", end));
    throwExceptionIfNotOkayHttpStatus(response);
    String calls = response.getContent();
    return calls;
  }

  /**
   * Adds call details to log with post action
   * @param call
   * @throws IOException
   */
    public int addPhoneCallEntry(PhoneCall call) throws IOException {
      Response response = http.post(Map.of("customer", call.getCustomer(),
              "callee", call.getCallee(),
              "caller", call.getCaller(),
              "begintime", call.getBeginTimeString(),
              "endtime", call.getEndTimeString()));
      throwExceptionIfNotOkayHttpStatus(response);
      return response.getHttpStatusCode();
    }

  /**
   * Delete entire log
   * @throws IOException
   */
  public void removeAllDictionaryEntries() throws IOException {
      Response response = http.delete(Map.of());
      throwExceptionIfNotOkayHttpStatus(response);
    }

  /**
   * used to catch exception from http response object
   * @param response
   */
  private void throwExceptionIfNotOkayHttpStatus(Response response) {
      int code = response.getHttpStatusCode();

      if (code != HTTP_OK) {
        String message = response.getContent();
        System.err.println(message);
      }
      else {
        System.err.println((code) + " :Successful response");
      }
    }

}
