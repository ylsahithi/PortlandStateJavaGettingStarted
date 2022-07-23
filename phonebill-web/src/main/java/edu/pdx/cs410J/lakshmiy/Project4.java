package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.ParserException;

import java.io.FileReader;
import java.io.IOException;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) throws IOException, ParserException {
        String hostName = args[1];
        String portString = args[3];
        String word = null;
        String definition = null;
//
//        for (String arg : args) {
//            if (hostName == null) {
//                hostName = arg;
//
//            } else if ( portString == null) {
//                portString = arg;
//
//            } else if (word == null) {
//                word = arg;
//            } else if (definition == null) {
//                definition = arg;
//
//            } else {
//                usage("Extraneous command line argument: " + arg);
//            }
//        }
//
//        if (hostName == null) {
//            usage( MISSING_ARGS );
//
//        } else if ( portString == null) {
//            usage( "Missing port" );
//        }

        int port;
        try {
            port = Integer.parseInt(portString);

        } catch (NumberFormatException ex) {
            System.out.println("Port \"" + portString + "\" must be an integer");
            return;
        }

        PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
        String message;
        PhoneCall pc = new PhoneCall(args, 4);
//        System.out.println(pc.toString());
        System.out.println("customer"+ pc.getCustomer() +
                "callee" + pc.getCallee() +
                "caller" + pc.getCaller() +
                "begintime" + pc.getBeginTimeString() +
                "endtime" + pc.getEndTimeString());
//        FileReader fr = new FileReader("/Users/sahithiyalamarthi/Desktop/merge/PortlandStateJavaSummer2022/phonebill-web/dumper.txt");
//        TextParser parse = new TextParser(fr);
//        PhoneBill pb =  parse.parse("abc");
////        System.out.println(pb.getCustomer());
//        PrettyPrinter pp = new PrettyPrinter();
//        String res = pp.formatphoneBookEntry(pb);
//        System.out.println(res);
//        client.addPhoneCallEntry(pc);
//        String res = client.getCallsBetweenDates("sahithi","12/12/2020 1:23 am", "12/12/2020 5:23 am");
       client.removeAllDictionaryEntries();
//        System.out.println(res);
//        PhoneBill pb  = new PhoneBill(pc.getCustomer());
//        pb.addPhoneCall(pc);
//        TextDumper dump = new TextDumper("/Users/sahithiyalamarthi/Desktop/merge/PortlandStateJavaSummer2022/phonebill-web/dumper.txt");
//        dump.dump(pb);


            }

        }
//        client.getentirelog();
//        System.out.println(client.getAllDictionaryEntries(pc.getCustomer()));
//        message = PrettyPrinter.formatphoneBookEntry(new PhoneBill(args[4], pc));
//        System.out.println(message);
//        try {
//            if (word == null) {
//                // Print all word/definition pairs
//                Map<String, String> dictionary = client.getAllDictionaryEntries();
//                StringWriter sw = new StringWriter();
//                PrettyPrinter pretty = new PrettyPrinter(sw);
//                pretty.dump(dictionary);
//                message = sw.toString();
//
//            } else if (definition == null) {
//                // Print all dictionary entries
//                message = PrettyPrinter.formatDictionaryEntry(word, client.getDefinition(word));
//
//            } else {
//                // Post the word/definition pair
//                client.addDictionaryEntry(word, definition);
//                message = Messages.definedWordAs(word, definition);
//            }
//
//        } catch (IOException | ParserException ex ) {
//            error("While contacting server: " + ex);
//            return;
//        }
//
//        System.out.println(message);
//    }

        /**
         * Makes sure that the give response has the expected HTTP status code
         * @param code The expected status code
         * @param response The response from the server
         */
//    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
//    {
//        if (response.getHttpStatusCode() != code) {
//            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
//                                response.getHttpStatusCode(), response.getContent()));
//        }
//    }

//    private static void error( String message )
//    {
//        PrintStream err = System.err;
//        err.println("** " + message);
//    }

        /**
         * Prints usage information for this program and exits
         * @param message An error message to print
         */
//    private static void usage( String message )
//    {
//        PrintStream err = System.err;
//        err.println("** " + message);
//        err.println();
//        err.println("usage: java Project4 host port [word] [definition]");
//        err.println("  host         Host of web server");
//        err.println("  port         Port of web server");
//        err.println("  word         Word in dictionary");
//        err.println("  definition   Definition of word");
//        err.println();
//        err.println("This simple program posts words and their definitions");
//        err.println("to the server.");
//        err.println("If no definition is specified, then the word's definition");
//        err.println("is printed.");
//        err.println("If no word is specified, all dictionary entries are printed");
//        err.println();
//    }
//    }
//}