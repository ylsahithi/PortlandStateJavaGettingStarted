package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
@Test
void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
//    System.out.println("result.getTextWrittenToStandardOut()" );
    assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Missing_args));
}
@Test
  void testMainWithOnlyPrint(){
      MainMethodResult result = invokeMain(Project1.class, "-print");
      assertThat(result.getTextWrittenToStandardError(), containsString(Project1.No_Print_args));
  }

  @Test
  void testMainWithOnlyReadme(){
      MainMethodResult result = invokeMain(Project1.class, "-readme");
      assertThat(result.getTextWrittenToStandardError(), containsString("No arguments passed other than readme"));
  }

    @Test
    void testMainWithOnlyOptions(){
        MainMethodResult result = invokeMain(Project1.class, "-readme", "-print");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.No_Print_args));
    }

    @Test
    void testMainWithOnlyCustomername(){
        MainMethodResult result = invokeMain(Project1.class, "Sahithi");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Invalid_args));
    }

//    @Test
//    void testMainWithOnlyOptionsPassedinAnyorder(){
//        MainMethodResult result = invokeMain(Project1.class, "-readme", "-print");
//        MainMethodResult result1 = invokeMain(Project1.class, "-print", "-readme");
//
////        assertThat(assertThat(result.getTextWrittenToStandardError(), containsString("No arguments passed to print")))  (assertThat(result1.getTextWrittenToStandardError(), containsString("No arguments passed to print"))));
//    }
    @Test
    void testMainWithFewArgs(){
    MainMethodResult result = invokeMain(Project1.class, "-readme", "-print", "sahithi");
    assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Less_Num_args));
}

    @Test
    void testMainWithMoreArgs(){
        MainMethodResult result = invokeMain(Project1.class, "-print" , "-readme", "sahithi" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34", "additional");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.More_Num_args));
    }

    @Test
    void testMainWithLessArgsWithOptions(){
        MainMethodResult result = invokeMain(Project1.class, "-print" , "-readme", "sahithi" , "12/12/2020", "11:34", "12/12/2020", "9:34");
        assertThat(result.getTextWrittenToStandardError(), containsString(Project1.Less_Num_args));
    }

    @Test
    void testMainWithInvalidCustomer(){
        MainMethodResult result = invokeMain(Project1.class,  "" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid customer name"));
    }

    @Test
    void testMainWithInvalidCustomer1(){
        MainMethodResult result = invokeMain(Project1.class,  "123" ,"9719789630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid customer name"));
    }
    @Test
    void testMainWithInvalidPhonenumber(){
        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"971978@#9630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for phone number, it cannot contain letters"));
    }

    @Test
    void testMainWithInvalidPhonenumber1(){
        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"97199630", "3128103280", "12/12/2020", "11:34", "12/12/2020", "9:34" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid phone number, number of digits less than 10"));
    }

    @Test
    void testMainWithInvalidDate(){
        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "21/12/2020", "11:34", "12/12/2020", "9:34" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }
    @Test
    void testMainWithInvalidEndDate(){
        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "21/12/2020", "11:34", "1212/2020", "9:34" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }

    @Test
    void testMainWithInvalidTime(){
        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "34:34", "12/12/2020", "9:34" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
    }

    @Test
    void testMainWithInvalidEndTime(){
        MainMethodResult result = invokeMain(Project1.class,  "Sahithi" ,"9719789630", "3128103280", "12/12/2020", "34:34", "12/12/2020", "934" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for time"));
    }
}