//package edu.pdx.cs410J.lakshmiy;
//
//import edu.pdx.cs410J.InvokeMainTestCase;
//import org.junit.jupiter.api.Test;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
// * Tests the functionality in the {@link Project1} main class.
// */
//class Project1IT extends InvokeMainTestCase {
//
//    /**
//     * Invokes the main method of {@link Project1} with the given arguments.
//     */
//    private MainMethodResult invokeMain(String... args) {
//        return invokeMain( Project1.class, args );
//    }
//
//    /**
//     * Tests that invoking the main method with no arguments issues an error
//     */
//    @Test
//    void testNoCommandLineArguments() {
//        MainMethodResult result = invokeMain();
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Missing_args));
//    }
//
//    /**
//     * Tests that invoking the main method with only print option issues an error
//     */
//    @Test
//    void testMainWithOnlyPrint(){
//        MainMethodResult result = invokeMain(Project1.class, "-print");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.No_Print_args));
//    }
//    /**
//     * Tests that invoking the main method with only readme argument
//     */
//    @Test
//    void testMainWithOnlyReadme(){
//        MainMethodResult result = invokeMain(Project1.class, "-readme");
//        assertThat(result.getTextWrittenToStandardError(), containsString("No arguments passed other than readme"));
//    }
//
//    /**
//     * Tests that invoking the main method with only optional arguments prints readme file
//     */
//    @Test
//    void testMainWithOnlyOptions(){
//        MainMethodResult result = invokeMain(Project1.class, "-readme", "-print");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Readme_txt));
//    }
//    /**
//     * Tests that invoking the main method with only customer name
//     */
//    @Test
//    void testMainWithOnlyCustomername(){
//        MainMethodResult result = invokeMain(Project1.class, "Sahithi");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Invalid_args));
//    }
//
//    /**
//     * Tests that invoking the main method with only 4 arguments
//     */
//    @Test
//    void testMainWithFewArgs(){
//        MainMethodResult result = invokeMain(Project1.class, "-test", "-print", "sahithi", "9797989898");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Less_Num_args));
//    }
//    /**
//     * Tests that invoke the main method with more than required arguments issues an error
//     */
//    @Test
//    void testMainWithMoreArgs(){
//        MainMethodResult result = invokeMain(Project1.class, "-print" , "-more", "sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34", "additional");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.More_Num_args));
//    }
//
//    /**
//     * Tests that invoking the main method with arguments as expected by application throws no error
//     */
//    @Test
//    void testMainWithExactArgs(){
//        MainMethodResult result = invokeMain(Project1.class,  "-print", "-opt1", "sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString("Phone call from 9719789630 to 3128103280 from 12/12/2020 11:34 to 12/12/2020 9:34"));
//    }
//
//    /**
//     * Tests that invoking the main method with no optional arguments throws no error
//     */
//    @Test
//    void testMainWithExactArgsNooptions(){
//        MainMethodResult result = invokeMain(Project1.class,  "sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString("Phone call from 9719789630 to 3128103280 from 12/12/2020 11:34 to 12/12/2020 9:34"));
//    }
//
//
//    /**
//     * Tests that invoking the main method with 7 arguments with optional arg throws error
//     */
//    @Test
//    void testMainWithInvalidArgs(){
//        MainMethodResult result = invokeMain(Project1.class,  "-print" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Less_Num_args));
//    }
//
//    /**
//     * Tests that invoking the main method with 8 arguments with optional arg throws no error
//     */
//    @Test
//    void testMainWithEightArgs(){
//        MainMethodResult result = invokeMain(Project1.class,  "-print", "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString("Phone call from 9719789630 to 3128103280 from 12/12/2020 11:34 to 12/12/2020 9:34"));
//    }
//
//
//    /**
//     * Tests that invoking the main method with 8 arguments with invalid optional arg throws error
//     */
//    @Test
//    void testMainWithEightInvalidOptArgs(){
//        MainMethodResult result = invokeMain(Project1.class,  "-opt", "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Invalid_options));
//    }
//
//
//    /**
//     * Tests that invoking the main method with 9 arguments with valid optional arg throws no error
//     */
//    @Test
//    void testMainWithNinewithOptArgs(){
//        MainMethodResult result = invokeMain(Project1.class,  "-print", "-readme", "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Readme_txt));
//    }
//
//    /**
//     * Tests that invoking the main method with 9 arguments with invalid optional arg throws error
//     */
//    @Test
//    void testMainWithNinewithInvalidOptArgs(){
//        MainMethodResult result = invokeMain(Project1.class,  "-opt1", "-opt", "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Invalid_options));
//    }
//
//    /**
//     * Tests that invoking the main method with less arguments but with options issues an error
//     */
//    @Test
//    void testMainWithLessArgsWithOptions(){
//        MainMethodResult result = invokeMain(Project1.class, "-print" , "-less", "sahithi" , "12/12/2020", "11:34", "12/12/2020", "9:34");
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Less_Num_args));
//    }
//
//    /**
//     * Tests that invoking the main method with 3 arguments
//     */
//    @Test
//    void testMainWiththreeArgs(){
//        MainMethodResult result = invokeMain(Project1.class, "-print" , "-less", "sahithi" );
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Less_Num_args));
//    }
//
//    /**
//     * Tests that invoking the main method with invalid empty customer name issues an error
//     */
//    @Test
//    void testMainWithInvalidCustomer(){
//        MainMethodResult result = invokeMain(Project1.class,  "" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid customer name"));
//    }
//
//    @Test
//    void testMainWithInvalidCustomer1(){
//        MainMethodResult result = invokeMain(Project1.class,  "123" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid customer name"));
//    }
//
//    /**
//     * Tests that invoking the main method with invalid phone number issues an error
//     */
//    @Test
//    void testMainWithInvalidPhonenumber(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"971978@#9630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for phone number, it cannot contain letters"));
//    }
//
//    @Test
//    void testMainWithInvalidPhonenumber1(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"97199630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid phone number, number of digits less than 10"));
//    }
//
//
//    @Test
//    void testMainWithInvalidPhonenumber2(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"0019789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString(" Invalid phone number, a phone number cannot start with zero"));
//    }
//
//    /**
//     * Tests that invoking the main method with invalid date as input issues an error
//     */
//    @Test
//    void testMainWithInvalidDate(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "21/12/2020", "11:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
//    }
//
//
//    @Test
//    void testMainWithInvalidDate1(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "2112/2020", "11:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
//    }
//    @Test
//    void testMainWithInvalidEndDate(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "21/12/2020", "11:34", "1212/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
//    }
//
//    @Test
//    void testMainWithInvalidEndDate1(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "21/12/2020", "11:34", "22/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
//    }
//    /**
//     * Tests that invoking the main method with invalid time as input issues an error
//     */
//    @Test
//    void testMainWithInvalidTime(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "34:34", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
//    }
//
//    @Test
//    void testMainWithInvalidTime1(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "334", "12/12/2020", "9:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
//    }
//
//    @Test
//    void testMainWithInvalidEndTime(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "3:34", "12/12/2020", "934" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
//    }
//
//    @Test
//    void testMainWithInvalidEndTime1(){
//        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "3:34", "12/12/2020", "25:34" );
//        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
//    }
//
//
//
//}