//package edu.pdx.cs410J.lakshmiy;
//
//import edu.pdx.cs410J.ParserException;
//import edu.pdx.cs410J.web.HttpRequestHelper;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.Map;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class PhoneBillRestClientTest {
//
//  @Test
//  void getAllDictionaryEntriesPerformsHttpGetWithNoParameters() throws ParserException, IOException {
//    Map<String, String> dictionary = Map.of("customer", "sahithi", "callee", "1234567890", "caller",
//     "9876543210", "begintime","02/27/2022 8:56 am", "endtime", "02/27/2022 10:27 am");
//
//    HttpRequestHelper http = mock(HttpRequestHelper.class);
//    when(http.get(eq(Map.of()))).thenReturn(dictionaryAsText(dictionary));
//
//    PhoneBillRestClient client = new PhoneBillRestClient(http);
//    ArrayList ar = new ArrayList();
//    ar.add("sahithi");
//    assertThat(client.addPhoneCallEntry(ar);, equalTo("Searched for Phone call that doesnot exist"));
//  }
//
//  private HttpRequestHelper.Response dictionaryAsText(Map<String, String> dictionary) {
//    StringWriter writer = new StringWriter();
//      String[] values = new String[] {dictionary.get("customer"),dictionary.get("callee"),dictionary.get("caller"),dictionary.get("begintime"),dictionary.get("endtime")};
//      PhoneCall pc = new PhoneCall(values);
//      PhoneBill pb = new PhoneBill(dictionary.get("customer"),pc);
//
//      new TextDumper(writer).dump(pb);
//
//    return new HttpRequestHelper.Response(writer.toString());
//  }
//}
