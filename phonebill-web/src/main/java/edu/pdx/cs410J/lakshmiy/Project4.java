package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.*;
import java.net.ConnectException;
import java.util.ArrayList;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

//    public static final String MISSING_ARGS = "Missing command line arguments";
    public static final String Missing_args = "No args provided to run, required atleast 7 args";
    public static final String Invalid_args = "Invalid arguments passed";
    public static final String Invalid_options = "Invalid options provided as arguments";
    public static final String No_Print_args = "No arguments passed to print";
    public static final String Less_Num_args = "less number of arguments passed cannot execute the class";
    public static final String More_Num_args = "Too Many arguments for the class";
    public static final String Readme_txt = "This is a README file! Readme.txt";
    public static final String text_File = "Written data to dumper ";
    public static final String Search_option_error = "Missing search arguments ";


    public static void main(String... args) throws IOException, ParserException {
//        System.out.println(args.length);
        validateInputArgsCount(args);

    }

    /**
     * This function returns void and prints contents of readme file
     * It is called when user arguments has readme option
     * return void
     *
     */
    public static void printREADMEOption(){
        BufferedReader br = null;
        try {
            InputStream ReadmeFile = Project4.class.getResourceAsStream("README.txt");
            InputStreamReader temp = new InputStreamReader(ReadmeFile);
            br = new BufferedReader(temp);
            String lines;
            while ((lines = br.readLine()) != null) {
                System.out.println(lines);
            }
            printErrorMessage(Readme_txt);
            return;
        } catch (IOException IO) {
            System.err.println("No file found on source system");
        }
    }

    /**
     *  This method is used to print error message in the application
     *  @param  message
     *
     */
    public static void printErrorMessage(String message) {
        System.err.println(message);
        return;
    }

    /**
     * host port search print readme
     * 5, 9
     * @param args
     * @throws ParserException
     * @throws IOException
     */

    public static void validateInputArgsCount(String[] args) throws ParserException, IOException {
        try {
            ValidateArgs va = new ValidateArgs();
            ArrayList list = new ArrayList<>();
            int search = 0;
            int index = 1;
            /**
             * picking up text file and pretty file from user arguments
             */
            for (String arg : args) {
                list.add(arg.toLowerCase().trim());
                if (arg.equalsIgnoreCase("-search")) {
                    search = index;

                }
                index++;
            }
            /**
             * If there are no arguments passed to main function
             */
            if (index == 0) {
                printErrorMessage(Missing_args);
                return;
            }
            /**
             * If readme is one of the arguments then it prints readme and exit
             * prints read me in system error
             */

            if (list.contains("-readme") && list.indexOf("-readme") <= 5) {
                printREADMEOption();
                return;
            }

            /**
             * If host and port name is not present in user arguments
             */
            if ((!list.contains("-host")) && !(list.contains("-port"))) {
                printErrorMessage(Missing_args);
                return;
            }
            String hostname = args[list.indexOf("-host") + 1];
            String portString = args[list.indexOf("-port") + 1];
            int port;
            try {
                port = Integer.parseInt(portString);

            } catch (NumberFormatException ex) {
                System.err.println("Port \"" + portString + "\" must be an integer");
                return;
            }

            /**
             * Less number of arguments
             */
            if (args.length >= 1 && args.length <= 4) {
                if (list.contains("-print")) {
                    printErrorMessage(No_Print_args);
                    return;
                } else if (list.contains("-search")) {
                    printErrorMessage(Less_Num_args);
                    return;
                } else {
                    printErrorMessage(Less_Num_args);
                    return;
                }
            }

            /**
             * Get call if only customer name is passed
             */
            PhoneBillRestClient client = new PhoneBillRestClient(hostname, port);
            if (args.length == 5) {
                ArrayList searchargs = new ArrayList<>();
                searchargs.add(args[4]);
                String response = client.getCallsBetweenDates(searchargs);
                System.out.println(response);
            }
            /**
             * If more than 15 args are passed
             */
            if (args.length >= 15) {
                printErrorMessage(More_Num_args);
                return;
                /**
                 * If more than 6 and less than 9 args are passed
                 */
            } else if ((args.length <= 9) && (args.length >= 6)) {
                printErrorMessage(Less_Num_args);
                return;
                /**
                 * If valid arguments are passed in expected order.
                 * GEt call in case of -search option
                 */
            } else if (search != 0) {
                ArrayList searchargs = new ArrayList<>();
                for (int i = search; i < args.length; i++) {
                    searchargs.add(args[i]);
                }
                if (searchargs.size() == 7) {
                    if (!va.validateSelectedArg(args, search)) {
                        printErrorMessage(Invalid_args);
                        return;
                    } else {
                        String response = client.getCallsBetweenDates(searchargs);
                        System.out.println(response);
                    }
                } else {
                    printErrorMessage(Invalid_args);
                }
            } else if (args.length >= 10 && args.length < 13) {
                if (!(list.contains("-print") || list.contains("-search") || list.contains("-readme"))) {
                    printErrorMessage(Invalid_options);
                    return;
                }
                printErrorMessage(Invalid_args);
            }
            /**
             * Expected number of arguments passed for post call
             */
            else if (args.length == 13) {
                if ((list.contains("-print") || list.contains("-search") || list.contains("-readme"))) {
                    printErrorMessage(Less_Num_args);
                    return;
                } else {
                    if (!va.validateEachArg(args, 4)) {
                        printErrorMessage(Invalid_args);
                        return;
                    } else {
                        PhoneCall callData = new PhoneCall(args, 4);
                        PhoneBill cust = new PhoneBill(args[4], callData);
                        if (list.contains("-print")) {
                            System.out.println(callData.toString());
                        } else {
                            client.addPhoneCallEntry(callData);
                            System.out.println(PrettyPrinter.formatphoneBookEntry(cust));
                            System.out.println(
                                    Messages.definedWordAs(callData.getCustomer(), callData.getCaller(), callData.getCallee(), callData.getBeginTimeString(), callData.getEndTimeString()));
                        }
                    }
                }
            } else if (args.length == 14) {
                if (!va.validateEachArg(args, 5)) {
                    printErrorMessage(Invalid_args);
                    return;
                } else {
                    PhoneCall callData = new PhoneCall(args, 5);
                    PhoneBill cust = new PhoneBill(args[4], callData);
                    if (list.contains("-print")) {
                        System.out.println(callData.toString());
                    }
                    else {
                        client.addPhoneCallEntry(callData);
                        System.out.println(Messages.definedWordAs(callData.getCustomer(), callData.getCaller(), callData.getCallee(), callData.getBeginTimeString(), callData.getEndTimeString()));
                        System.out.println(PrettyPrinter.formatphoneBookEntry(cust));
                    }
                    if (!(list.contains("-print") || list.contains("-readme") || list.contains("-search"))) {
                        printErrorMessage(Invalid_options);
                    }
                }
            }
            return;
        }
        catch (ConnectException ce) {
            System.err.println("Connection not created check for jetty run");

        }    }

    /**
         * Prints usage information for this program and exits
         * @param message An error message to print
         */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [word] [definition]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  word         Word in dictionary");
        err.println("  definition   Definition of word");
        err.println();
        err.println("This simple program posts call logs and their customers");
        err.println("to the server.");
        err.println("If no call is specified, then it returns empty");
        err.println();
    }
}
