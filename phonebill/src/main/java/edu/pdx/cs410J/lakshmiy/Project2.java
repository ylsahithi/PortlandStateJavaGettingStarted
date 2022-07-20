//package edu.pdx.cs410J.lakshmiy;
//
//import com.google.common.annotations.VisibleForTesting;
//import edu.pdx.cs410J.ParserException;
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class Project2 {
//    /**  Public variables to pass error strings to print error function
//     * These variables are used in test case validations
//     *
//     */
//    public static final String Missing_args = "No args provided to run, required atleast 7 args";
//    public static final String Invalid_args = "Invalid arguments passed";
//    public static final String Invalid_options = "Invalid options provided as arguments";
//    public static final String No_Print_args = "No arguments passed to print";
//    public static final String Less_Num_args = "less number of arguments passed cannot execute the class";
//    public static final String More_Num_args = "Too Many arguments for the class";
//    public static final String Readme_txt = "This is a README file! Readme.txt";
//    public static final String text_File = "Written data to text file";
//    public static final String text_File_error = "Missing text file and arguments ";
//    public static final String File_not_found = "Exception caught file not found";
//
//
//
//    /**
//     * author @yalam2@pdx.edu
//     * Main function for the application
//     * args are in the format : "opt1" " opt2" "name" "callee number"
//     * "caller number" "begin time" "end time"
//     * -option1 -option2 (-readme -print)
//     *  optional args
//     * -name
//     *  Customer name
//     *  -caller number
//     *  caller phone number
//     *  -callee number
//     *    callee phone number
//     *  - begin time
//     *     Start time for the phone call
//     *  - end time
//     *      End time for phone call
//     * @param args
//     *
//     *
//     */
//    public static void main(String[] args) throws IOException, ParserException {
//        validateInputArgsCount(args);
//    }
//
//    /**
//     * This function calls text parser and dumper thus read and writes data to file
//     * @param args
//     * @param filename
//     * @param argPos
//     * @throws IOException
//     * @throws ParserException
//     */
//
//    public static void parseAndDump(String [] args, String filename, int argPos) throws IOException, ParserException{
//        String file = "";
//        file = args[1];
//        PhoneCall callData = new PhoneCall(args, argPos);
//        File textFile = new File(file);
//        if (textFile.length() == 0) {
//            textFile.createNewFile();
//            PhoneBill pb = new PhoneBill(args[argPos]);
//            pb.addPhoneCall(callData);
//            TextDumper dump = new TextDumper(filename);
//            dump.dump(pb);
//            printErrorMessage(text_File);
//            System.out.println(text_File);
//            printErrorMessage(File_not_found);
//        }
//        else {
//            try {
//                FileReader fr = new FileReader(textFile);
//                TextParser parse = new TextParser(fr, args[argPos]);
//                PhoneBill pb = (PhoneBill) parse.parse();
//                pb.addPhoneCall(callData);
//
//                TextDumper dump = new TextDumper(filename);
//                dump.dump(pb);
//                printErrorMessage(text_File);
//            }
//            catch (FileNotFoundException fe){
//                System.err.println("invalid entry as file name parameter");
//            }
//        }
//    }
//    /**
//     * This function returns void and prints contents of readme file
//     * It is called when user arguments has readme option
//     * return void
//     *
//     */
//    public static void printREADMEOption(){
//        BufferedReader br = null;
//        try {
//            InputStream ReadmeFile = edu.pdx.cs410J.lakshmiy.Project1.class.getResourceAsStream("README.txt");
//            InputStreamReader temp = new InputStreamReader(ReadmeFile);
//            br = new BufferedReader(temp);
//            String lines;
//            while ((lines = br.readLine()) != null) {
//                System.out.println(lines);
//            }
//            printErrorMessage(Readme_txt);
//            return;
//        } catch (IOException IO) {
//            System.err.println("No file found on source system");
//        }
//    }
//
//
//    /**
//     *  This method is used to print error message in the application
//     *  @param  message
//     *
//     */
//    public static void printErrorMessage(String message) {
//        System.err.println(message);
//        return;
//    }
//
//    /**
//     *  This method validated number of arguments passed by user,
//     *  it returns false and appropriate error message if invalid arguments are passed
//     *  return type is  boolean
//     *  @param  args
//     *
//     */
//    public static void validateInputArgsCount(String[] args) throws ParserException, IOException {
//        ArrayList list = new ArrayList<>();
//        String inputFileArg = "";
//        int index = 0;
//        for (String arg :args) {
//            if(args.length > 2){
//            if (arg.startsWith("-textFile")) {
//                if(args[index+1].contains(".txt")) {
//                    inputFileArg = args[index+1];
//                }
//                else {
//                    System.err.println("File name passed is null ");
//                }
//            }
//            }
//            list.add(arg.trim().toLowerCase());
//            index++;
//        }
//        ArrayList optionSet = new ArrayList<>();
//        /**
//         * If there are no arguments passed to main function
//         */
//        if (args.length == 0) {
//            printErrorMessage(Missing_args);
//            return;
//        }
//        /**
//         * If readme is one of the arguments then it prints readme and exit
//         * prints read me in system error
//         */
//        if(list.contains("-readme") && list.indexOf("-readme") <= 3){
//            printREADMEOption();
//            return;
//        }
//        /**
//         * If there are only one argument passed if the argument is type
//         * of optional arguments it returns error message
//         */
//
//        else if (args.length == 1) {
//            if (args[0].equalsIgnoreCase("-print")) { printErrorMessage(No_Print_args);  return;}
//            else if (args[0].equalsIgnoreCase(inputFileArg)) { printErrorMessage(text_File_error);  return;}
//            else {
//                printErrorMessage(Invalid_args);
//                return;
//            }
//        }
//        else if (args.length == 2) {
//            if (args[0].equalsIgnoreCase("-print")||args[1].equalsIgnoreCase("-print")) { printErrorMessage(No_Print_args);  return;}
//            else if (args[0].equalsIgnoreCase(inputFileArg)||args[1].equalsIgnoreCase(inputFileArg)) { printErrorMessage(text_File_error);  return;}
//            else {
//                printErrorMessage(Invalid_args);
//                return;
//            }
//        }
//
//
//        /**
//         * If more than 10 args are passed
//         */
//        else if (args.length > 11) {
//            printErrorMessage(More_Num_args);
//            return;
//            /**
//             * If more than 2 and less than 7 args are passed
//             */
//        } else if ((args.length < 7) && (args.length > 2)) {
//            printErrorMessage(Less_Num_args);
//            return;
//            /**
//             * If valid arguments are passed in expected order.
//             */
//        } else if (args.length == 7) {
//            if ((list.contains("-print") || list.contains(inputFileArg) || list.contains("-readme"))) {
//                printErrorMessage(Less_Num_args);
//                return;
//            }
//            else {
//                if (!validateEachArg(args)) {
//                    printErrorMessage(Invalid_args);
//                    return;
//                } else {
//                    PhoneCall callData = new PhoneCall(args,0);
//                    PhoneBill cust = new PhoneBill(args[0], callData);
//                    System.out.println(callData.toString());
//                }
//            }
//        } else if (args.length == 8) {
//            if (!validateEachArg(args)) {
//                printErrorMessage(Invalid_args);
//                return;
//            } else {
//                if (args[0].equalsIgnoreCase("-print")) {
//                    PhoneCall callData = new PhoneCall(args, 1);
//                    PhoneBill cust = new PhoneBill(args[1], callData);
//                    System.out.println(callData.toString());
//                }
//                else if (list.contains("-textfile")) {
////                    parseAndDump(args, inputFileArg, 1);
////                    printErrorMessage(text_File);
//                    printErrorMessage(text_File_error);
//                }
//                if (!(list.contains("-print") || list.contains("-textfile") || list.contains("-readme"))) {
//                    printErrorMessage(Invalid_options);
//                }
//
//            }
//        }
//        else if (args.length == 9) {
//            if (!validateEachArg(args)) {
//                printErrorMessage(Invalid_args);
//                return;
//            }
//            else {
//                if (list.contains("-print") && list.indexOf("-print") <= 2) {
//                    PhoneCall callData = new PhoneCall(args, 2);
//                    PhoneBill cust = new PhoneBill(args[2], callData);
//                    System.out.println(callData.toString());
//                }
//                if (list.contains("-textfile") && inputFileArg.length() > 1) {
//                    int pos = list.indexOf(inputFileArg);
//                    parseAndDump(args, inputFileArg,2);
//                }
//                if (!(list.contains("-print") || list.contains("-textfile") || list.contains("-readme"))) {
//                    printErrorMessage(Invalid_options);
//                }
//            }
//        }
//        else if (args.length == 10) {
//            if (!validateEachArg(args)) {
//                printErrorMessage(Invalid_args);
//                return;
//            } else {
//                if (!(list.contains("-print") || list.contains("-textfile") || list.contains("-readme"))) {
//                    printErrorMessage(Invalid_options);
//                }
//                if (list.contains("-print") && list.indexOf("-print") <= 3) {
//                    PhoneCall callData = new PhoneCall(args, 3);
//                    PhoneBill cust = new PhoneBill(args[3], callData);
//                    System.out.println(callData.toString());
//                }
//                if (list.contains("-textfile") && inputFileArg.length() > 1) {
//                    int pos = list.indexOf(inputFileArg);
//                    parseAndDump(args, inputFileArg,3);
//                }
//            }
//        }
//        else if (args.length == 11) {
//            if (!validateEachArg(args)) {
//                printErrorMessage(Invalid_args);
//                return;
//            } else {
//                if (!(list.contains("-print") || list.contains("-textfile") || list.contains("-readme"))) {
//                    printErrorMessage(Invalid_options);
//                }
//                if (list.contains("-print") && list.indexOf("-print") <= 4) {
//                    PhoneCall callData = new PhoneCall(args, 4);
//                    PhoneBill cust = new PhoneBill(args[4], callData);
//                    System.out.println(callData.toString());
//                }
//                if (list.contains("-textfile") &&  inputFileArg.length() > 1) {
//                    int pos = list.indexOf(inputFileArg);
//                    parseAndDump(args, inputFileArg,4);
//                }
//            }
//        }
//        return;
//    }
//
//
//
//    /**
//     * This method validates each argument individually and returns true if arguments are valid
//     * it checks for valid phone numbers date and time in input args return type is boolean
//     * @param args
//     *
//     */
//    @VisibleForTesting
//    public static boolean validateEachArg(String[] args) {
//        if (args.length == 7){
//            if ((checkForvalidString(args[0])) && (isValidPhoneNumber(args[1])) && (isValidPhoneNumber(args[2])) && (checkForValidDate(args[3])) && (checkForValidTime(args[4]))
//                    && (checkForValidDate(args[5])) && (checkForValidTime(args[6]))) {
//                return true;
//            }
//        }
//        else  if (args.length == 8) {
//            if ((checkForvalidString(args[1])) && (isValidPhoneNumber(args[2])) && (isValidPhoneNumber(args[3])) && (checkForValidDate(args[4])) && (checkForValidTime(args[5]))
//                    && (checkForValidDate(args[6])) && (checkForValidTime(args[7]))) {
//                return true;
//            }
//        }
//        else  if (args.length == 9) {
//            if ((checkForvalidString(args[2])) && (isValidPhoneNumber(args[3])) && (isValidPhoneNumber(args[4])) && (checkForValidDate(args[5])) && (checkForValidTime(args[6]))
//                    && (checkForValidDate(args[7])) && (checkForValidTime(args[8]))) {
//                return true;
//            }
//        }
//        else  if (args.length == 10) {
//            if ((checkForvalidString(args[3])) && (isValidPhoneNumber(args[4])) && (isValidPhoneNumber(args[5])) && (checkForValidDate(args[6])) && (checkForValidTime(args[7]))
//                    && (checkForValidDate(args[8])) && (checkForValidTime(args[9]))) {
//                return true;
//            }
//        }
//            else  if (args.length == 11) {
//                if ((checkForvalidString(args[4])) && (isValidPhoneNumber(args[5])) && (isValidPhoneNumber(args[6])) && (checkForValidDate(args[7])) && (checkForValidTime(args[8]))
//                        && (checkForValidDate(args[9])) && (checkForValidTime(args[10]))) {
//                    return true;
//                }
//        }
//        return false;
//    }
//
//
//    /**
//     * This method validates each customer name argument, it checks for valid string as input
//     * return type is boolean
//     * @param name
//     *
//     */
//    @VisibleForTesting
//    public static boolean checkForvalidString(String name) {
//        if (name.trim().isEmpty() || name.length() == 1 || (name.replaceAll("[^a-zA-Z]", "").length() == 0)) {
//            printErrorMessage("Invalid customer name");
//            return false;
//        }
//        return true;
//    }
//
//
//    /**
//     * This method validates phone number value of the arguments
//     * return type is boolean
//     * @param number
//     *
//     */
//    @VisibleForTesting
//    static boolean isValidPhoneNumber(String number) {
//        String phoneNumber = number.replaceAll("-","");
//        if (phoneNumber.length() < 10) {
//            printErrorMessage("Invalid phone number, number of digits less than 10");
//            return false;
//        } else if (phoneNumber.startsWith("0")) {
//            printErrorMessage(" Invalid phone number, a phone number cannot start with zero");
//            return false;
//        } else if (!phoneNumber.matches("[0-9]+")) {
//            printErrorMessage("Invalid input for phone number, it cannot contain letters");
//            return false;
//        }
//        return true;
//    }
//
//
//    /**
//     * This method validates DAte value in args checks fi date format is as expected
//     * return type is boolean
//     * @param date
//     *
//     */
//    @VisibleForTesting
//    public static boolean checkForValidDate(String date) {
//        try {
//            SimpleDateFormat validFormat = new SimpleDateFormat("MM/dd/yyyy");
//            Date formattedDate = validFormat.parse(date);
//            if (date.equals(validFormat.format(formattedDate))) {
//                return true;
//            } else {
//                printErrorMessage("Invalid input for date");
//                return false;
//            }
//        } catch (ParseException PE) {
//            printErrorMessage("Invalid input for date");
//            return false;
//        }
//    }
//
//    /**
//     * This method validates Time value in args checks if it is valid
//     * return type is  boolean
//     * @param time
//     *
//     */
//    @VisibleForTesting
//    public static boolean checkForValidTime(String time) {
//        try {
//            String[] hourMin = time.split(":");
//            if ((Integer.parseInt(hourMin[0]) < 24) && (Integer.parseInt(hourMin[1]) < 60)) {
//                return true;
//            }
//            printErrorMessage("Invalid input for time");
//            return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//}
