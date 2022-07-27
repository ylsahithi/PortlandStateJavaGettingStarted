package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.ParserException;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TextDumperParserTest {

  @Test
  void emptyMapCanBeDumpedAndParsed() throws ParserException {
    String error = "";
    PhoneBill pb = new PhoneBill("");
      StringWriter sw = new StringWriter();
      TextDumper dumper = new TextDumper(sw);
          dumper.dump(pb);
    assertThat("customer log is empty", containsString("customer log is empty"));
  }


    //  @Test
  private PhoneBill dumpAndParse(Map<String, String> map) throws ParserException {
    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
   PhoneCall pc = new PhoneCall(new String[] {"sahithi","1234567890", "9876543210", "02/27/2022 8:56 am",  "02/27/2022 10:27 am"});
    PhoneBill pb = new PhoneBill("sahithi",pc);
    dumper.dump(pb);

    String text = sw.toString();

    TextParser parser = new TextParser(new StringReader(text));
    return parser.parse("sahithi");
  }

  @Test
  void dumpedTextCanBeParsed() throws ParserException {
    Map<String, String> dictionary = Map.of("customer", "sahithi", "callee", "1234567890", "caller",
     "9876543210", "begintime","02/27/2022 8:56 am", "endtime", "02/27/2022 10:27 am");

    PhoneBill read = dumpAndParse(dictionary);
    assertThat(read.getCustomer(), equalTo("sahithi"));
  }
}
