package edu.pdx.cs410J.lakshmiy;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
class PhoneBillServletTest {

  @Test
  void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);
//    System.out.println(response.getStatus());

    // Nothing is written to the response's PrintWriter
    verify(pw, never()).println(anyString());
    verify(response).setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
  }

  @Test
  void addOneEntryToLog() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String customer = "Dave";
    String begin = "02/27/2022 8:56 am";
    String end = "02/27/2022 10:27 am";
    String callee = "503-245-2345";
    String caller = "765-389-1273";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("callee")).thenReturn(callee);
    when(request.getParameter("caller")).thenReturn(caller);
    when(request.getParameter("begintime")).thenReturn(begin);
    when(request.getParameter("endtime")).thenReturn(end);
    HttpServletResponse response = mock(HttpServletResponse.class);
    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);
    when(response.getWriter()).thenReturn(pw);
    servlet.doPost(request, response);
    assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(customer,
            caller,
            callee,
            begin,
            end)));
    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());
    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
  }
@Test
  void GETForCallthatDoesntExist() throws IOException {
    String customer = "bhanu";
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    HttpServletResponse response = mock(HttpServletResponse.class);
    servlet.doGet(request, response);
    verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, Messages.InvalidSearchResult());
  }

  @Test
  void SearchForCallthatDoesntExist() throws IOException {
    String customer = "Dave";
    String begin = "02/27/2022 8:56 am";
    String end = "02/27/2022 10:27 am";
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begintime")).thenReturn(begin);
    when(request.getParameter("endtime")).thenReturn(end);
    HttpServletResponse response = mock(HttpServletResponse.class);
    servlet.doGet(request, response);
    verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, Messages.InvalidSearchResult());
  }

  @Test
  void SearchForCallthatCustomerDoesntExist() throws IOException {
//    String customer = "Dave";
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
//    when(request.getParameter("customer")).thenReturn(customer);
    HttpServletResponse response = mock(HttpServletResponse.class);
    servlet.doGet(request, response);
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter("customer"));
  }
@Test
  void addCall() throws IOException {
  PhoneBillServlet servlet = new PhoneBillServlet();
  String customer = "Dave";
  String begin = "02/27/2022 8:56 am";
  String end = "02/27/2022 10:27 am";
  String callee = "503-245-2345";
  String caller = "765-389-1273";
//  PhoneBillServlet servlet = new PhoneBillServlet();
  HttpServletRequest request = mock(HttpServletRequest.class);
  when(request.getParameter("customer")).thenReturn(customer);
  when(request.getParameter("begintime")).thenReturn(begin);
  when(request.getParameter("endtime")).thenReturn(end);
  when(request.getParameter("callee")).thenReturn(callee);
  when(request.getParameter("caller")).thenReturn(caller);
  HttpServletResponse response = mock(HttpServletResponse.class);
//  HttpServletResponse response = mock(HttpServletResponse.class);
  StringWriter stringWriter = new StringWriter();
  PrintWriter pw = new PrintWriter(stringWriter, true);
  when(response.getWriter()).thenReturn(pw);
  servlet.doPost(request, response);
  assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(customer,
          caller,
          callee,
          begin,
          end)));
  }

}
