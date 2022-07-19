package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.PhoneBillDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class PrettyPrint implements PhoneBillDumper<PhoneBill> {
    private final Writer writer;

    /**
     * Default constructor
     */
    public PrettyPrint() {
        this.writer = null;
    }

    /**
     * Creates writer object with file name imput
     * @param filename
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public PrettyPrint(String filename) throws IOException,IllegalArgumentException {
        if (filename.isEmpty() || filename == null) {
            throw new IllegalArgumentException("Expected a filename but got a null");
        }
        File fle = new File(filename);
        Writer Writ = new FileWriter(fle);
        this.writer = Writ;
    }

    /**
     * Prints data to console
     * @param bill
     */
    public void PrettyPrintToConsole(PhoneBill bill) {
        Collection<PhoneCall> custlog = bill.getPhoneCalls();
        if (custlog.isEmpty()) {
            System.out.println(" Customer : " + bill.getCustomer());
            System.out.println(" Customer : " + bill.toString());
        } else {
            System.out.println(" Customer : " + bill.getCustomer());
            System.out.println(" Customer : " + bill.toString());
            int lineCount = 1;
            for (PhoneCall call : custlog) {
                System.out.println(" call " + lineCount + ": ");
                System.out.println(" Caller number  : " + call.getCaller() );
                System.out.println(" Callee number  : " + call.getCallee() );
                SimpleDateFormat prettydate = new SimpleDateFormat("dd-MMM-yy hh:mm a");
                System.out.println(" Begin call time : " + prettydate.format(call.getBeginTimeDate()));
                System.out.println(" End call time : " + prettydate.format(call.getEndTimeDate()));
                System.out.println(" Call Duration : " + call.calculateDuration() + " min");
                lineCount ++;
            }
        }
    }

    /**
     * Dumps data into pretty print file
     * @param bill
     * @throws IOException
     */
    @Override
    public void dump(PhoneBill bill) throws IOException {
        try {
            Collection<PhoneCall> custlog = bill.getPhoneCalls();
//            SimpleDateFormat inputdate = new SimpleDateFormat("MM/dd/yyyy hh:mm");

            if(custlog.isEmpty()){
                this.writer.append(" Customer : " + bill.getCustomer() + "\n");
                this.writer.append(" Customer : "  + bill.toString() + "\n");
            }
            else {
                this.writer.append(" Customer : " + bill.getCustomer() + "\n");
                this.writer.append(" Customer : "  + bill.toString() + "\n");
                int lineCount = 1;
                for (PhoneCall call : custlog) {
                    this.writer.append(" call  " + lineCount + ": \n");
                    this.writer.append(" Caller number  : " + call.getCaller() + "\n");
                    this.writer.append(" Callee number  : " + call.getCallee() + "\n");
                    SimpleDateFormat prettydate = new SimpleDateFormat("dd-MMM-yy hh:mm a");
                    this.writer.append(" Begin call time : " + prettydate.format(call.getBeginTimeDate()) + "\n");
                    this.writer.append(" End call time : " + prettydate.format(call.getEndTimeDate()) + "\n");
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
