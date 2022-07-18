package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.PhoneBillDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class PrettyPrint implements PhoneBillDumper<PhoneBill> {
    private final Writer writer;

    public PrettyPrint(Writer writer) {
        this.writer = writer;
    }

    public PrettyPrint(String filename) throws IOException,IllegalArgumentException {
        System.out.println(filename);
        if (filename.isEmpty() || filename == null) {
            throw new IllegalArgumentException("Expected a filename but got a null");
        }
        File fle = new File(filename);
        if(fle.length() == 0){
            System.out.println("Pretty print File is empty creating a file with given name");
        }
        Writer Writ = new FileWriter(fle);
        this.writer = Writ;
    }


    @Override
    public void dump(PhoneBill bill) throws IOException {
        try {
            Collection<PhoneCall> custlog = bill.getPhoneCalls();
            if(custlog.isEmpty()){
                this.writer.append(" Customer : " + bill.getCustomer() + "\n");
                this.writer.append(" Customer : "  + bill.toString() + "\n");
//                this.writer.append(bill.getCustomer());
            }
            else {
                this.writer.append(" Customer : " + bill.getCustomer() + "\n");
                this.writer.append(" Customer : "  + bill.toString() + "\n");
                int lineCount = 1;
                for (PhoneCall call : custlog) {
                    this.writer.append(" call  " + lineCount + ": \n");
                    this.writer.append(" Caller number  : " + call.getCaller() + "\n");
                    this.writer.append(" Callee number  : " + call.getCallee() + "\n");
                    this.writer.append(" Begin call time : " + call.getBeginTimeString() + "\n");
                    this.writer.append(" End call time : " + call.getEndTimeString() + "\n");
                    this.writer.append(" Call Duration : " + call.calculateDuration() + " min \n");
                    lineCount ++;
              }
            }
            this.writer.flush();
            this.writer.close();
        }
        catch (Exception e) {
            System.err.println(" No valid file for Pretty print exiting \n");
            throw new RuntimeException(e);

        }
    }
}
