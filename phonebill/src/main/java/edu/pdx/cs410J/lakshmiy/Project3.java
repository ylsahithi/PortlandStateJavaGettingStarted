package edu.pdx.cs410J.lakshmiy;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Project3 {
    /**  Public variables to pass error strings to print error function
     * These variables are used in test case validations
     *
     */
    public static final String Missing_args = "No args provided to run, required atleast 7 args";
    public static final String Invalid_args = "Invalid arguments passed";
    public static final String Invalid_options = "Invalid options provided as arguments";
    public static final String No_Print_args = "No arguments passed to print";
    public static final String Less_Num_args = "less number of arguments passed cannot execute the class";
    public static final String More_Num_args = "Too Many arguments for the class";
    public static final String Readme_txt = "This is a README file! Readme.txt";
    public static final String text_File = "Written data to text file";
    public static final String text_File_error = "Missing text file and arguments ";
    public static final String File_not_found = "Exception caught file not found";
    public static final String File_parse_exception = "Invalid text in input file cannot parse";
    public static final String Pretty_print_File_error = "Cannot execute pretty print, error in file passed or file name";
    public static final String  Pretty_success = "Written data to pretty print file ";


    /**
     * author @yalam2@pdx.edu
     * Main function for the application
     * args are in the format : "opt1" " opt2" "name" "callee number"
     * "caller number" "begin time" "end time"
     * -option1 -option2 (-readme -print)
     *  optional args
     * -name
     *  Customer name
     *  -caller number
     *  caller phone number
     *  -callee number
     *    callee phone number
     *  - begin time
     *     Start time for the phone call
     *  - end time
     *      End time for phone call
     * @param args
     *
     *
     */
    public static void main(String[] args) throws IOException, ParserException {
        validateInputArgsCount(args);
    }

    /**
     * This function calls text parser, dumper and pretty print dumper thus read and writes data to file
     * @param args
     * @param filename
     * @param argPos
     * @throws IOException
     * @throws ParserException
     */

    public static void parseAndDump(String [] args, String filename, int argPos, String prettyFile) throws IOException, ParserException{
        if(filename.length() == 0 && prettyFile.length() == 0 ) {
            System.err.println("File names passed in argument are Null");
            return;
        }
        PhoneCall callData = new PhoneCall(args, argPos);
        if(filename.length() == 0 && prettyFile.length() != 0){
            PhoneBill prettybill = new PhoneBill(args[argPos]);
            prettybill.addPhoneCall(callData);
            if(prettyFile.equals("-")){
                PrettyPrint pp = new PrettyPrint();
                pp.PrettyPrintToConsole(prettybill);
                System.err.println("Written pretty print to Console");
            }
            else{ PrettyPrint pp = new PrettyPrint(prettyFile);
                pp.dump(prettybill);
                System.err.println(Pretty_success);
            }
        }
        if(filename.length() != 0) {
            File textFile = new File(filename);
            if (textFile.length() == 0) {
                textFile.createNewFile();
                PhoneBill pb = new PhoneBill(args[argPos]);
                pb.addPhoneCall(callData);
                TextDumper dump = new TextDumper(filename);
                dump.dump(pb);
                if (prettyFile.contains(".txt")) {
                    PrettyPrint pp = new PrettyPrint(prettyFile);
                    pp.dump(pb);
                    System.err.println(Pretty_success);
                }
                if (prettyFile.equals("-")) {
                    PrettyPrint pc = new PrettyPrint();
                    pc.PrettyPrintToConsole(pb);
                    System.err.println("Written pretty print to Console");
                }
                System.err.println(text_File);
                System.err.println("File not present creating new file");
            }
            else {
                try{
                FileReader fr = new FileReader(textFile);
                TextParser parse = new TextParser(fr, args[argPos]);
                PhoneBill pb = (PhoneBill) parse.parse();
                if (pb.getCustomer().length() == 0) {
                    printErrorMessage(File_parse_exception);
                    return;
                }
                pb.addPhoneCall(callData);
                TextDumper dump = new TextDumper(filename);
                dump.dump(pb);
                if (prettyFile.contains("txt")) {
                    PrettyPrint pp = new PrettyPrint(prettyFile);
                    pp.dump(pb);
                    System.err.println(Pretty_success);
                }
                if (prettyFile.equals("-")) {
                    PrettyPrint pc = new PrettyPrint();
                    pc.PrettyPrintToConsole(pb);
                    System.err.println("Written pretty print to Console");
                }
                    System.err.println(text_File);
            } catch (FileNotFoundException fe) {
                    System.err.println("invalid entry as file name parameter");
                }
            }
        }
        return;
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
            InputStream ReadmeFile = Project1.class.getResourceAsStream("README.txt");
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
     *  This method validated number of arguments passed by user,
     *  it returns false and appropriate error message if invalid arguments are passed
     *  return type is  boolean
     *  @param  args
     *
     */
    public static void validateInputArgsCount(String[] args) throws ParserException, IOException {
        ValidateArgs va = new ValidateArgs();
        ArrayList list = new ArrayList<>();
        String inputFileArg = "";
        String prettyFileArg = "";
        int index = 0;
        /**
        * picking up text file and pretty file from user arguments
         */
        for (String arg :args) {
            if(args.length > 2){
                if (arg.startsWith("-textFile")) {
                        if(index+1 < args.length){
                            if(args[index+1].contains(".txt")) {inputFileArg = args[index + 1];}
                        }
                }
                if (arg.startsWith("-pretty")) {
                    if(index+1 < args.length){
                        if(args[index+1].contains(".txt") || (args[index+1].contains("-"))){
                        prettyFileArg = args[index + 1];}
                    }
                }
            }
            list.add(arg.trim().toLowerCase());
            index++;
        }
        /**
         * If there are no arguments passed to main function
         */
        if (args.length == 0) {
            printErrorMessage(Missing_args);
            return;
        }
        /**
         * If readme is one of the arguments then it prints readme and exit
         * prints read me in system error
         */
        if(list.contains("-readme") && list.indexOf("-readme") <= 5){
            printREADMEOption();
            return;
        }
        /**
         * If there are only less number of argument passed
         */
        if (args.length >= 1 && args.length <= 5 ) {
            if (list.contains("-print")) { printErrorMessage(No_Print_args);  return;}
            else if (list.contains("-textfile")) { printErrorMessage(text_File_error);  return;}
            else if (list.contains("-pretty")) { printErrorMessage(Pretty_print_File_error);  return;}
            else {
                printErrorMessage(Invalid_args);
                return;
            }
        }
        /**
         * If more than 13 args are passed
         */
        else if (args.length >= 13) {
            printErrorMessage(More_Num_args);
            return;
            /**
             * If more than 4 and less than 7 args are passed
             */
        } else if ((args.length < 7) && (args.length >= 4)) {
            printErrorMessage(Less_Num_args);
            return;
            /**
             * If valid arguments are passed in expected order.
             */
        } else if (args.length == 7) {
            if ((list.contains("-print") || list.contains("-textfile") || list.contains("-readme") || list.contains("-pretty"))) {
                printErrorMessage(Less_Num_args);
                return;
            }
            else {
                if (!va.validateEachArg(args,0)) {
                    printErrorMessage(Invalid_args);
                    return;
                } else {
                    PhoneCall callData = new PhoneCall(args,0);
                    PhoneBill cust = new PhoneBill(args[0], callData);
                    System.out.println(callData.toString());
                }
            }
        } else if (args.length == 8) {
            if (!va.validateEachArg(args,1)) {
                printErrorMessage(Invalid_args);
                return;
            } else {
                if (args[0].equalsIgnoreCase("-print")) {
                    PhoneCall callData = new PhoneCall(args, 1);
                    PhoneBill cust = new PhoneBill(args[1], callData);
                    System.out.println(callData.toString());
                }
                else if (list.contains("-textfile")) {
                    printErrorMessage(text_File);
                }
                else if (list.contains("-pretty")) {
                    printErrorMessage(Pretty_print_File_error);
                }
                if (!(list.contains("-print") || list.contains("-textfile") || list.contains("-readme") || list.contains("-pretty"))) {
                    printErrorMessage(Invalid_options);
                }

            }
        }
        else if (args.length >= 9  && args.length <= 12) {
            int argpos = 0;
            if(args.length == 9){
                argpos = 2;
            } else if (args.length == 10) {
                argpos = 3;
            }
            else if (args.length == 11) {
                argpos = 4;
            }
            else if (args.length == 12) {
                argpos = 5;
            }
            if (!va.validateEachArg(args,argpos)) {
                printErrorMessage(Invalid_args);
                return;
            }
            else {
                if (list.contains("-print") && list.indexOf("-print") <= argpos) {
                    PhoneCall callData = new PhoneCall(args, argpos);
                    PhoneBill cust = new PhoneBill(args[argpos], callData);
                    System.out.println(callData.toString());
                }
                if ((list.contains("-textfile") && list.indexOf("-textfile") <= argpos) || (list.contains("-pretty") && list.indexOf("-pretty") <= argpos) ) {
                    parseAndDump(args, inputFileArg,argpos,prettyFileArg);
                }
                if (!(list.contains("-print") || list.contains("-textfile") || list.contains("-readme")  || list.contains("-pretty"))) {
                    printErrorMessage(Invalid_options);
                }
            }
        }
        return;
    }
}
