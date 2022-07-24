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
//  private final String url;

    /**
     * Creates a client to the Phone Bil REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public PhoneBillRestClient( String hostName, int port )
    {
      this(new HttpRequestHelper(String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET)));
//      this.url = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);

    }

  @VisibleForTesting
  PhoneBillRestClient(HttpRequestHelper http) {
    this.http = http;
  }

  /**
   * Returns all dictionary entries from the server
   */
  public String getAllDictionaryEntries(String customer) throws IOException, ParserException {
    Response response = http.get(Map.of("customer",customer));
    throwExceptionIfNotOkayHttpStatus(response);
    String call = response.getContent();
    return call;
  }


  public String getentirelog() throws IOException, ParserException {
    Response response = http.get(Map.of());
    throwExceptionIfNotOkayHttpStatus(response);
    String call = response.getContent();
    TextParser parser = new TextParser(new StringReader(call));
    PhoneBill bill = parser.parse("");
    PrettyPrinter pp = new PrettyPrinter();
    String entries = pp.formatphoneBookEntry(bill);
    return entries;
  }


  /**
   * Returns the definition for the given word
   */
  public String getCallsBetweenDates(ArrayList <String> args) throws IOException, ParserException {
    String customer = args.get(0);
    String start = "";
    String end = "";
    if(args.size() > 1) {
      start = args.get(1) + " " + args.get(2) + " " + args.get(3);
      end = args.get(4) + " " + args.get(5) + " " + args.get(6);
    }
    Response response = http.get(Map.of("customer",customer, "start", start, "end", end));
    if(response.getContent().equalsIgnoreCase("Accepted")){
      return Messages.SearchNocalls();
    }
    throwExceptionIfNotOkayHttpStatus(response);
    String calls = response.getContent();
//
//    TextParser parser = new TextParser(new StringReader(calls));
//    PhoneBill log = parser.parse(customer);
//    PrettyPrinter pp = new PrettyPrinter();
//    String entries = pp.formatphoneBookEntry(log);
    return calls;
  }

    public void addPhoneCallEntry(PhoneCall call) throws IOException {
//      System.out.println(call.getCustomer());
      Response response = http.post(Map.of("customer", call.getCustomer(),
              "callee", call.getCallee(),
              "caller", call.getCaller(),
              "begintime", call.getBeginTimeString(),
              "endtime", call.getEndTimeString()));
      throwExceptionIfNotOkayHttpStatus(response);
    }

  public void removeAllDictionaryEntries() throws IOException {
      Response response = http.delete(Map.of());
      throwExceptionIfNotOkayHttpStatus(response);
    }

    private void throwExceptionIfNotOkayHttpStatus(Response response) {
      int code = response.getHttpStatusCode();

      if (code != HTTP_OK) {
        String message = response.getContent();
        System.err.println(message);
//        Messages.missingRequiredParameter(response, C)
//        throw new RestException(code, message);
      }
      else {
        System.err.println((code) + " :Successful response");
      }
    }

}
