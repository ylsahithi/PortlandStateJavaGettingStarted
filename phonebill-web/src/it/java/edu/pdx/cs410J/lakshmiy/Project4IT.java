package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */
@TestMethodOrder(MethodName.class)
class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    void test0RemoveAllMappings() throws IOException {
      PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }

    @Test
    void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Missing_args));
    }

    @Test
    void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost", "-port", "8080"  );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Less_Num_args));
    }

    @Test
    void testreadmeServer() {
        MainMethodResult result = invokeMain( Project4.class, "-readme"  );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Readme_txt));
    }

    @Test
    void testEmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost"  );
        assertThat(result.getTextWrittenToStandardError(), containsString("Port \"-host\" must be an integer"));
    }

    @Test
    void testEmptyServer1() {
        MainMethodResult result = invokeMain( Project4.class, "-port", "8080"  );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Less_Num_args));
    }

    @Test
    void testWithOpts() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080" , "-print" , "-search" );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Less_Num_args));
    }

    @Test
    void testWithOpts1() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080" , "search" );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Less_Num_args));
    }

    @Test
    void testInvalidPort() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "print" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Port \"print\" must be an integer"));
    }
    @Test
    void testvalidArgsnoOpts() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "Dave", "503-245-2345","765-389-1273","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString("200 :Successful response"));
    }

    @Test
    void testvalidSearchArgsnoOpts() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-search", "Dave","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString("200 :Successful response"));
    }

    @Test
    void testvalidSearchArgsnoOpts1() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-search", "sahithi","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Searched for Phone call that doesnot exist"));
    }

    @Test
    void testvalidSearchArgsnoOpts2() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-search", "sahithi","02/27/2022", "8:56" ,"am","02/27/2022", "10:27"  );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Invalid_args));
    }

    @Test
    void testvalidArgswithOpts() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-print", "Dave", "503-245-2345","765-389-1273","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from 503-245-2345 to 765-389-1273 from 02/27/2022 8:56 am to 02/27/2022 10:27 am"));
    }

    @Test
    void testvalidArgswithOpts1() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-print", "-opt" , "Dave", "503-245-2345","765-389-1273","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.More_Num_args));
    }

    @Test
    void testvalidSearchArgsnoOpts4() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-search", "opt",  "sahithi","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Invalid_args));
    }

    @Test
    void testvalidSearchArgsnoOpts5() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-opt", "sahithi","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Invalid_options));
    }

    @Test
    void testvalidArgswithOpts6() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-print", "Dave","765-389-1273","02/27/2022", "8:56" ,"am","02/27/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.Less_Num_args));
    }

    @Test
    void testvalidSearchArgsnoOpts9() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-search", "sahithi","02/27/2022", "8:56" ,"am","02/2a/2022", "10:27" ,"am" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }

    @Test
    void testvalidSearchArgsnoOpts7() {
        MainMethodResult result = invokeMain( Project4.class, "-host", "localhost" , "-port", "8080", "-search", "sahithi","02/27/2022", "8:56" ,"am","02/2a/2022", "10:27" ,"sm" );
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid input for date"));
    }
    @Test
    void test3NoDefinitionsThrowsAppointmentBookRestException() {
        String word = "WORD";
        try {
            invokeMain(Project4.class, HOSTNAME, PORT, word);
//            fail("Expected a RestException to be thrown");

        } catch (UncaughtExceptionInMain ex) {
            RestException cause = (RestException) ex.getCause();
            assertThat(cause.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
        }
    }

    @Test
    void test4AddDefinition() {
        String customer = "Dave";
        String begindate = "02/27/2022";
        String beginformat = "am";
        String bbegintime = "8:56";
        String enddate = "02/27/2022";
        String endformat = "am";
        String endtime = "10:27";
        String callee = "503-245-2345";
        String caller = "765-389-1273";
        String begin = "02/27/2022 8:56 am";
        String end = "02/27/2022 10:27 am";


        MainMethodResult result = invokeMain(Project4.class, "-host", HOSTNAME, "-port", PORT, customer, callee, caller, begindate, bbegintime, beginformat, enddate, endtime, endformat);
        assertThat(result.getTextWrittenToStandardOut(), containsString(Messages.definedWordAs(customer, callee, caller, begin, end)));
    }
}