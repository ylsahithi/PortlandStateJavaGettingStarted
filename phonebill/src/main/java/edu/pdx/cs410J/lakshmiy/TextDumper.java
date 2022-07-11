package edu.pdx.cs410J.lakshmiy;

import edu.pdx.cs410J.AppointmentBookDumper;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.util.Collection;

public class TextDumper implements PhoneBillDumper<PhoneBill> {
    private final Writer writer;

    /**
     * constructor class for Textdumper
     * @param writer
     */
    public TextDumper(Writer writer) {
        this.writer = writer;
    }

    /**
     * This constructor checks if filename is empty and creates new file
     * @param filename
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public TextDumper(String filename) throws IOException,IllegalArgumentException {
        System.out.println(filename);
        if (filename.isEmpty() || filename == null) {
            throw new IllegalArgumentException("Expected filename but got a null");
        }
        File fle = new File(filename);
        if(fle.length() == 0){
            System.out.println("File is empty creating a file with given name");
        }
        Writer Writ = new FileWriter(fle);
        this.writer = Writ;
    }

    /**
     * this function writes phonecall data into text file
     * @param bill
     */

    @Override
    public void dump(PhoneBill bill) {
        try {
            Collection<PhoneCall> custlog = bill.getPhoneCalls();
            if(custlog.isEmpty()){
                this.writer.append(bill.getCustomer());
            }
            else {
                for (PhoneCall call : custlog) {
                    this.writer.append(bill.getCustomer() + "," + call.getCaller() + "," + call.getCallee() + "," + call.getBeginTimeString().split(" ")[0] + "," +
                            call.getBeginTimeString().split(" ")[1] + "," + call.getEndTimeString().split(" ")[0] + "," + call.getEndTimeString().split(" ")[1] + "\n");
                }
            }
            this.writer.flush();
            this.writer.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
