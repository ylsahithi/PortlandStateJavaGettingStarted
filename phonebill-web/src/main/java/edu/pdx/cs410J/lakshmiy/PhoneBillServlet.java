package edu.pdx.cs410J.lakshmiy;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.pdx.cs410J.ParserException;


/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class PhoneBillServlet extends HttpServlet
{
    static final String CUSTOMER_PARAMETER = "customer";
    static final String CALLEE_PARAMETER = "callee";
    static final String CALLER_PARAMETER = "caller";
    static final String BEGINTIME_PARAMETER = "begintime";
    static final String ENDTIME_PARAMETER = "endtime";
//    static final String DEFINITION_PARAMETER = "definition";
//ASK on slack
    private final Map<String, PhoneBill> customer_log = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException {
        response.setContentType("text/plain");

        String customer = getParameter(CUSTOMER_PARAMETER, request);
        String begin = getParameter(BEGINTIME_PARAMETER, request);
        String end = getParameter(ENDTIME_PARAMETER, request);
        if (customer == null) {
            missingRequiredParameter(response, CUSTOMER_PARAMETER);
//            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            return;
        }
        if (begin == null && end == null) {
            try {
                getCallsRequested(customer, begin, end, response);
            } catch (ParserException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (begin == null) {
                missingRequiredParameter(response, BEGINTIME_PARAMETER);
                return;
            } else if (end == null) {
                missingRequiredParameter(response, ENDTIME_PARAMETER);
                return;
            } else {
                try {
                    getCallsRequested(customer, begin, end, response);
                } catch (ParserException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


//    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ParserException {
//        response.setContentType( "text/plain" );
//        FileReader fr = new FileReader("dumper.txt");
//        TextParser parse = new TextParser(fr);
//        PhoneBill pb = (PhoneBill) parse.parse();
//        PrintWriter pw = response.getWriter();
//        pw.println(pb.getCustomer());
//        pw.println(pb.toString());
//    }




    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );
        String customer = getParameter(CUSTOMER_PARAMETER, request );
        if (customer == null) {
            missingRequiredParameter(response, CUSTOMER_PARAMETER);
            return;
        }
        String caller = getParameter(CALLER_PARAMETER, request );
        if (caller == null) {
            missingRequiredParameter(response, CALLER_PARAMETER);
            return;
        }
        String callee = getParameter(CALLEE_PARAMETER, request );
        if (callee == null) {
            missingRequiredParameter(response, CALLEE_PARAMETER);
            return;
        }
        String begintime = getParameter(BEGINTIME_PARAMETER, request );
        if ( begintime == null) {
            missingRequiredParameter( response, BEGINTIME_PARAMETER );
            return;
        }
        String endtime = getParameter(ENDTIME_PARAMETER, request );
        if ( endtime == null) {
            missingRequiredParameter( response, ENDTIME_PARAMETER );
            return;
        }
        PhoneBill pbill = this.customer_log.get(customer);
        if(pbill == null){
            pbill = new PhoneBill(customer);
        }
        PhoneCall call = new PhoneCall(new String[] {customer,callee,caller,begintime,endtime});
        PrintWriter pw = response.getWriter();
        if(call.getBeginTimeDate().equals(call.getEndTimeDate()) || call.getEndTimeDate().before(call.getBeginTimeDate())){
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            pw.println(Messages.InvalidinputToPost());
        } else {
            pbill.addPhoneCall(call);
            this.customer_log.put(customer,pbill);
            TextDumper dumper = new TextDumper(pw);
            dumper.dump(customer_log.get(customer));
            response.setStatus( HttpServletResponse.SC_OK);
            pw.println(Messages.SuccessfulPost());
        }
        pw.flush();
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/plain");
//        this.customer_log.clear();
//        PrintWriter pw = response.getWriter();
//        pw.println(Messages.allDictionaryEntriesDeleted());
//        pw.flush();
//        response.setStatus(HttpServletResponse.SC_OK);
//
//    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
        response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
    }

    /**
     * Writes the definition of the given word to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void getCallsRequested(String customer, String begin, String end, HttpServletResponse response) throws IOException, ParserException {
        PrintWriter pw = response.getWriter();
        PrettyPrinter pp = new PrettyPrinter();
        ValidateArgs val = new ValidateArgs();
        if(begin == null && end == null && customer != null) {
        if (customer.trim().isEmpty() || customer.length() == 1 || (customer.replaceAll("[^a-zA-Z]", "").length() == 0)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid arguments for search ");
        }
        else {
            PhoneBill custlog = getPhoneBill(customer);
            if (custlog == null) {
                String msg = Messages.InvalidSearchResult();
                response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
            }
            else {
                pw.println(pp.formatphoneBookEntry(custlog));
                TextDumper dumper = new TextDumper(pw);
                dumper.dump(custlog);
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }
        }
        else {
        PhoneBill custlog = getPhoneBill(customer);
        if (custlog == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, Messages.InvalidSearchResult());
        } else {
            boolean check_args = val.validateSelectedArg(customer, begin, end);
            if (!check_args) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid arguments for search ");
            }
            Date begin_date = val.parseInputDate(begin);
            Date end_date = val.parseInputDate(end);
            PhoneBill requestedCalls = new PhoneBill(customer);
            for (PhoneCall call : this.customer_log.get(customer).getPhoneCalls()) {
                if((call.getBeginTimeDate().equals(begin_date) || call.getBeginTimeDate().after(begin_date)) && (call.getBeginTimeDate().equals(end_date) || call.getBeginTimeDate().before(end_date)))
                {
                    System.out.println(call.toString());
                    requestedCalls.addPhoneCall(call);
                }
            }
            if (requestedCalls == null) {
                response.sendError(HttpServletResponse.SC_ACCEPTED, "No calls in the specific period");
            }
            else {
                pw.println(pp.formatphoneBookEntry(requestedCalls));
                TextDumper dumper = new TextDumper(pw);
                dumper.dump(requestedCalls);
                System.out.println(pp.formatphoneBookEntry(requestedCalls));
                response.setStatus(HttpServletResponse.SC_OK);
            }
            }
        }
    }


    /**
     * Writes all of the dictionary entries to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
//    private void writeAllDictionaryEntries(HttpServletResponse response ) throws IOException
//    {
//        PrintWriter pw = response.getWriter();
//        TextDumper dumper = new TextDumper(pw);
//        dumper.dump(dictionary);
//
//        response.setStatus( HttpServletResponse.SC_OK );
//    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;
      } else {
        return value;
      }
    }


    @VisibleForTesting
    PhoneBill getPhoneBill(String customer) {
        if(this.customer_log.isEmpty()){
            return null;
        }
        else {
            return (this.customer_log.get(customer));
        }
    }


}
