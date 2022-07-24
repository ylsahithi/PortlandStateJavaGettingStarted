package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration test that tests the REST calls made by {@link PhoneBillRestClient}
 */
@TestMethodOrder(MethodName.class)
class PhoneBillRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private PhoneBillRestClient newPhoneBillRestClient() {
    int port = Integer.parseInt(PORT);
    return new PhoneBillRestClient(HOSTNAME, port);
  }

  @Test
  void test0RemoveAllDictionaryEntries() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.removeAllDictionaryEntries();
  }

//  @Test
//  void test1EmptyServerContainsNoDictionaryEntries() throws IOException, ParserException {
//    PhoneBillRestClient client = newPhoneBillRestClient();
//    String dictionary = client.getAllDictionaryEntries("");
//    assertThat(dictionary.length(), equalTo(0));
//  }

//  @Test
//  void test2DefineOneWord() throws IOException, ParserException {
//    PhoneBillRestClient client = newPhoneBillRestClient();
//    String testWord = "TEST WORD";
//    String testDefinition = "TEST DEFINITION";
//    client.addPhoneCallEntry(testWord, testDefinition);
//
//    String definition = client.getAllDictionaryEntries(testWord);
//    assertThat(definition, equalTo(testDefinition));
//  }

//  @Test
//  void test4EmptyWordThrowsException() {
//    PhoneBillRestClient client = newPhoneBillRestClient();
////    String [] emptyString = [""];
//    PhoneCall pc = new PhoneCall(new String[]("Sahithi"));
//
//    RestException ex =
//      assertThrows(RestException.class, () -> client.addPhoneCallEntry(pc));
//    assertThat(ex.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
//    assertThat(ex.getMessage(), equalTo(Messages.missingRequiredParameter("word")));
//  }

}
