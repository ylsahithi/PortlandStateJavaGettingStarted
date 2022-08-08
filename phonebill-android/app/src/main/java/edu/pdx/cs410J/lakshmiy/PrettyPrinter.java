package edu.pdx.cs410J.lakshmiy;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrettyPrinter extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        String Customer = getIntent().getStringExtra("Customer");
        String Begin = getIntent().getStringExtra("Begin");
        String End = getIntent().getStringExtra("End");
        TextView results = findViewById(R.id.result_text);
        try {
        ValidateArgs va = new ValidateArgs();

            String res = "";
            String Printresult = "";
            String filename = Customer + ".txt";
            File fle = getApplicationContext().getApplicationContext().getDataDir();
            File calllog = new File(fle, filename);
            if (!calllog.exists()) {
                System.err.println("There is no call log to search for this customer");
                results.setText("There is no call log to search for this customer");
                return;
            } else {
                BufferedReader br = new BufferedReader(new FileReader(calllog));
                String line = br.readLine();
                int count = 1;
                if((Begin == null && End == null) ){
                    Printresult = Printresult + "Call log for customer \n";
                    while (line!=null){
                        String [] log = line.split(",");
                        PhoneCall pc = new PhoneCall(log[0],log[1],log[2],log[3], log[4]);
                        System.out.println(pc.toString());
                        res = "call :   "+ count + formatphoneBookEntry(pc);
                        count ++;
                        Printresult = Printresult + res + "\n";
                        line = br.readLine();
                    }
                    results.setText(Printresult);
                    results.setMovementMethod(new ScrollingMovementMethod());
                }
                else {
                    String valid_args = va.validateSelectedArg(Customer, Begin, End);
                    if (valid_args.length() > 1) {
                        System.err.println("Invalid arguments passed to search");
                        valid_args = valid_args + "Invalid arguments passed as parameters";
                        results.setText(valid_args);
                    }
                    Date begin_date = va.parseInputDate(Begin);
                    Date end_date = va.parseInputDate(End);
                    while (line!=null) {
                        String[] log = line.split(",");
                        PhoneCall pc = new PhoneCall(log[0], log[1], log[2], log[3], log[4]);
                        System.out.println(log);
                        Date begin_input = pc.getBeginTimeDate();
                        if(((begin_input.equals(begin_date)) || (begin_input.after(begin_date))) && ((begin_input.equals(end_date)) || (begin_input.before(end_date)))) {
                            res = "call :   "+ count + formatphoneBookEntry(pc);
                            count++;
                            Printresult = Printresult + res + "\n";
                        }
                        line = br.readLine();
                    }
                    if(Printresult.equals("")) {
                        results.setText("No results found for data entered");
                    }
                     else {
                         results.setText(Printresult);
                        results.setMovementMethod(new ScrollingMovementMethod());
                    }
                    }
            }
        }
        catch(FileNotFoundException e){
                e.printStackTrace();
                results.setText("File not found for the customer searched ");
            } catch (IOException e) {
                e.printStackTrace();
                results.setText("Error while loading file ");
            }
    }

    public static String formatphoneBookEntry(PhoneBill bill)
    {
        String res = "";
        res = res + "Call log of Customer : " + bill.getCustomer() + "\n";
        int count = 1;
        SimpleDateFormat prettydate = new SimpleDateFormat("dd-MMM-yy hh:mm a");
        for (PhoneCall call : bill.getPhoneCalls()) {
            res = res + "call :" + count + " Caller number  : " + call.getCaller() + "\n"
                    + " Callee number  : " + call.getCallee() + "\n" + " Begin call time : " + prettydate.format(call.getBeginTimeDate()) + "\n"
                    + " End call time : " + prettydate.format(call.getEndTimeDate()) + "\n";
            count += 1;
        }
        return res;
    }

    public static String formatphoneBookEntry(PhoneCall call)
    {
        String res = "";
        res = res + "Call log of Customer : " + call.getCustomer() + "\n";
        SimpleDateFormat prettydate = new SimpleDateFormat("dd-MMM-yy hh:mm a");
            res = res + " Caller number  : " + call.getCaller() + "\n"
                    + " Callee number  : " + call.getCallee() + "\n" + " Begin call time : " + prettydate.format(call.getBeginTimeDate()) + "\n"
                    + " End call time : " + prettydate.format(call.getEndTimeDate()) + "\n";
        return res;
    }


    public PrettyPrinter() {
    }

}
