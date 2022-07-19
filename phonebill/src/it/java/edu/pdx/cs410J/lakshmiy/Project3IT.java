package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project3IT extends InvokeMainTestCase  {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project3.class, args);
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Missing_args));
    }

    /**
     * Tests that invoking the main method with only print option issues an error
     */
    @Test
    void testMainWithOnlyPrint() {
        MainMethodResult result = invokeMain(Project3.class, "-print");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.No_Print_args));
    }

    /**
     * Tests that invoking the main method with only readme argument
     */
    @Test
    void testMainWithOnlyReadme() {
        MainMethodResult result = invokeMain(Project3.class, "-readme");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Readme_txt));
    }

    /**
     * Tests that invoking the main method with only optional arguments prints readme file
     */
    @Test
    void testMainWithOnlyOptions() {
        MainMethodResult result = invokeMain(Project3.class, "-opt", "-print", "-textFile", "-pretty");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.No_Print_args));
    }

    @Test
    void testMainWithOnlyOptionsWithoutReadme1() {
        MainMethodResult result = invokeMain(Project3.class, "-pretty", "-opt", "-textFile");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.text_File_error));
    }

    @Test
    void testMainWithOnlyOptionsWithoutReadme2() {
        MainMethodResult result = invokeMain(Project3.class, "-pretty", "-opt", "-red");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Pretty_print_File_error));
    }
    @Test
    void testMainWithTwoOptions() {
        MainMethodResult result = invokeMain(Project3.class,  "-print", "-textFile", "Project3.txt");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.No_Print_args));
    }

    @Test
    void testMainWithAllOptions() {
        MainMethodResult result = invokeMain(Project3.class,  "-red", "-textFile" ,"Project3.txt", "-pretty", "prettyprint.txt","extra arg");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Less_Num_args));
    }
    /**
     * Tests that invoking the main method with only customer name
     */
    @Test
    void testMainWithOnlyCustomername() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Invalid_args));
    }

    /**
     * Tests that invoking the main method with only 4 arguments
     */
    @Test
    void testMainWithFewArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-test", "-print", "sahithi", "9797989898");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.No_Print_args));
    }

    /**
     * Tests that invoke the main method with more than required arguments issues an error
     */
    @Test
    void testMainWithMoreArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-print", "-textFile", "Project3.txt", "-pretty", "prettyprint.txt", "sahithi", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34", "additional");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.More_Num_args));
    }

    /**
     * Tests that invoking the main method with arguments as expected by application throws no error
     */
    @Test
    void testMainWithExactArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-print", "-textFile", " Project3.txt", "-pretty", "pretty_print.txt" ,"sahithi", "971-978-9630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from 971-978-9630 to 3128103280 from 12/12/2020 11:34 to 12/12/2020 9:34"));
    }

    /**
     * Tests that invoking the main method with no optional arguments throws no error
     */
    @Test
    void testMainWithExactArgsNooptions() {
        MainMethodResult result = invokeMain(Project3.class, "sahithi", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from 9719789630 to 3128103280 from 12/12/2020 11:34 to 12/12/2020 9:34"));
    }


    /**
     * Tests that invoking the main method with 7 arguments with optional arg throws error
     */
    @Test
    void testMainWithInvalidArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-print", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Less_Num_args));
    }

    /**
     * Tests that invoking the main method with 8 arguments with optional arg throws no error and invalid begin and end time
     */
    @Test
    void testMainWithEightArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-print", "Sahithi", "971-978-9630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from 971-978-9630 to 3128103280 from 12/12/2020 11:34 to 12/12/2020 9:34"));
    }

    @Test
    void testMainWithInvalidstartandendtime() {
        MainMethodResult result = invokeMain(Project3.class, "-textFile"," Project3.txt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Input end and begin time are equal or end time is before begin time"));
    }

    /**
     * Tests that invoking the main method with 9 arguments with valid optional arg throws no error
     */
    @Test
    void testMainWithNinewithOptArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-print","-textFile", "Sahithi", "971-978-9630", "3128103280", "12/12/2020", "9:34", "12/12/2020", "11:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("File names passed in argument are Null"));
    }

    /**
     * Tests that invoking the main method with 9 arguments with invalid optional arg throws error
     */
    @Test
    void testMainWithNinewithInvalidOptArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-opt1", "-opt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Invalid_options));
    }

    @Test
    void testMainWithNinewithTextfileOptArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-textFile","Project3.txt", "-opt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.text_File));
    }

    @Test
    void testMainWithTenwithOptArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-pretty", "pretty.txt", "-textFile", "Sahithi", "971-978-9630", "3128103280", "12/12/2020", "9:34", "12/12/2020", "11:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Pretty_success));
    }
    @Test
    void testMainWithTenwithOptArg1s() {
        MainMethodResult result = invokeMain(Project3.class, "-pretty", "-", "-textFile", "Sahithi", "971-978-9630", "3128103280", "12/12/2020", "9:34", "12/12/2020", "11:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Written pretty print to Console"));
    }

    /**
     * Tests that invoking the main method with 9 arguments with invalid optional arg throws error
     */
    @Test
    void testMainWithTenwithInvalidOptArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-textFile", "ten.txt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.text_File));
    }

    /**
     * Tests that invoking the main method with less arguments but with options issues an error
     */
    @Test
    void testMainWithElevenOptArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-textFile","Project3.txt", "-pretty", "pretty.txt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Pretty_success));
    }

    @Test
    void testMainWithElevenOptArgs2() {
        MainMethodResult result = invokeMain(Project3.class, "-textFile","Project3.txt", "-pretty", "-", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Written pretty print to Console"));
    }
@Test
    void testMainWithTwelveOptArgs() {
        MainMethodResult result = invokeMain(Project3.class, "-print", "-textFile", "Project3.txt", "-pretty", "pretty.txt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Pretty_success));
    }

    @Test
    void testMainWithTwelveOptArgs2() {
        MainMethodResult result = invokeMain(Project3.class, "-print", "-textFile","Project3.txt", "-pretty", "-", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Written pretty print to Console"));
    }

    @Test
    void testMainWithTwelveOptArgs3() {
        MainMethodResult result = invokeMain(Project3.class, "-opt", "-textFile","Project3.txt", "-pretty", "-", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Written pretty print to Console"));
    }

    @Test
    void testMainWithTwelveOptArgs4() {
        MainMethodResult result = invokeMain(Project3.class,  "-textFile", "-pretty",  "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("File names passed in argument are Null"));
    }


    @Test
    void testMainWithTwelveOptArgs6() {
        MainMethodResult result = invokeMain(Project3.class,  "-textFile","Project3.txt", "-pretty",  "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.text_File));
    }


    @Test
    void testMainWithTwelveOptArgs7() {
        MainMethodResult result = invokeMain(Project3.class,  "-textFile", "-pretty", "pretty1.txt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.Pretty_success));
    }

    @Test
    void testMainWithTwelveOptArgs9() {
        MainMethodResult result = invokeMain(Project3.class,  "-textFile", "-pretty", "-", "Sahithi", "9719789630", "3128103280", "12/12/2020", "1:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Written pretty print to Console"));
    }
    /**
     * Tests that invoking the main method with invalid empty customer name issues an error
     */
    @Test
    void testMainWithInvalidCustomer() {
        MainMethodResult result = invokeMain(Project3.class, "", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid customer name"));
    }

    /**
     * Tests that invoking the main method with invalid phone number issues an error
     */
    @Test
    void testMainWithInvalidPhonenumber() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "971978@#9630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for phone number, it cannot contain letters"));
    }

    @Test
    void testMainWithInvalidPhonenumber1() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "97199630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid phone number, number of digits less than 10"));
    }


    @Test
    void testMainWithInvalidPhonenumber2() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "0019789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(" Invalid phone number, a phone number cannot start with zero"));
    }

    /**
     * Tests that invoking the main method with invalid date as input issues an error
     */
    @Test
    void testMainWithInvalidDate() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "9719789630", "3128103280", "21/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }


    @Test
    void testMainWithInvalidDate1() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "9719789630", "3128103280", "2112/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }

    @Test
    void testMainWithInvalidEndDate() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "9719789630", "3128103280", "21/12/2020", "11:34", "1212/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }

    @Test
    void testMainWithInvalidEndDate1() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "9719789630", "3128103280", "21/12/2020", "11:34", "22/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }

    /**
     * Tests that invoking the main method with invalid time as input issues an error
     */
    @Test
    void testMainWithInvalidTime() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "9719789630", "3128103280", "12/12/2020", "34:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
    }


    @Test
    void testMainWithInvalidEndTime1() {
        MainMethodResult result = invokeMain(Project3.class, "Sahithi", "9719789630", "3128103280", "12/12/2020", "3:34", "12/12/2020", "25:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
    }

    /**
     * Tests with different user entries in file name option
     */
    @Test
    void TestMainwithEmptyFilename(){
        String dir = "/src/test/resources";
        MainMethodResult result = invokeMain(Project3.class, "-textFile ", dir, "Sahithi", "9719789630", "3128103280", "12/12/2020", "3:34", "12/12/2020", "5:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("File names passed in argument are Null"));
    }

    @Test
    void TestMainwithExistingFilename(){
        MainMethodResult result = invokeMain(Project3.class, "-textFile ","empty-file2.txt", "Lakshmi", "9719789630", "3128103280", "12/12/2020", "3:34", "12/12/2020", "5:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project3.text_File));
    }

    @Test
    void testMainNullTextFilergs() {
        MainMethodResult result = invokeMain(Project3.class, "-textFile " , "-opt", "Sahithi", "9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString("File names passed in argument are Null"));
    }
}



