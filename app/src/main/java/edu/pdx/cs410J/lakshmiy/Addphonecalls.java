package edu.pdx.cs410J.lakshmiy;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Addphonecalls#} factory method to
 * create an instance of this fragment.
 */
public class Addphonecalls extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Addphonecalls() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Addphonecalls.
//     */
    // TODO: Rename and change types and number of parameters
//    public static Addphonecalls newInstance(String param1, String param2) {
//        Addphonecalls fragment = new Addphonecalls();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_addphonecalls, container, false);
        EditText Customer = v.findViewById(R.id.customer_name_et);
        EditText Callee = v.findViewById(R.id.callee_et);
        EditText Caller = v.findViewById(R.id.caller_et);
        EditText Begin = v.findViewById(R.id.begin_et);
        EditText End = v.findViewById(R.id.end_et);
        CheckBox print_opt = v.findViewById(R.id.print);
        TextView print_res = v.findViewById(R.id.print_res);
        Button submit_bbtn = v.findViewById(R.id.submit_add_btn);
        submit_bbtn.setOnClickListener(new View.OnClickListener() {
            String customer = null;
            String callee = null;
            String caller = null;
            String begin_date = null;
            String end_date = null;
            @Override
            public void onClick(View view) {
                customer = Customer.getText().toString().trim();
                callee = Callee.getText().toString().trim();
                caller = Caller.getText().toString().trim();
                begin_date = Begin.getText().toString().trim();
                end_date = End.getText().toString().trim();
                ValidateArgs va = new ValidateArgs();
                String valid_flag = va.validateEachArg(customer,callee,caller,begin_date,end_date);
                if(valid_flag.length() > 1){
                    System.err.println("Invalid arguments passed as parameters");
                    Intent intent = new Intent(getActivity(), ErrorPopup.class);
                    valid_flag = valid_flag + "Invalid arguments passed as parameters";
                    intent.putExtra("Error", valid_flag);
                    startActivity(intent);
                }
                else {
                    PhoneCall pc = new PhoneCall(customer,callee,caller,begin_date,end_date);
                    if(print_opt.isChecked()){
                        String print_info = "customer " + customer + "has a call :" +pc.toString();
                        System.out.printf(print_info);
                        print_res.setText("New call details are \n" + print_info);
                        print_res.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                print_res.setVisibility(View.VISIBLE);} },2000);
                    }
                    PhoneBill pb = new PhoneBill(customer);
                    pb.addPhoneCall(pc);
                    String filename = customer+".txt";
                    System.out.println(filename);
                    File fle = getActivity().getApplicationContext().getDataDir();
                    File calllog = new File(fle, filename);
                    System.out.println(calllog.toString());
                    try {
                        PrintWriter pw = new PrintWriter(new FileWriter(calllog, true));
                        pw.println(customer.toString() + "," + callee.toString() + "," + caller.toString() + "," + begin_date.toString() + "," + end_date.toString());
                        pw.flush();
                        Toast.makeText(getActivity(), "Successfully added call details to phone log", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Intent intent = new Intent(getActivity(), ErrorPopup.class);
                        intent.putExtra("Error", "Error on writing text to phone log");
                        startActivity(intent);
                        e.printStackTrace();
                    }
                }
                Customer.setText("");
                Callee.setText("");
                Caller.setText("");
                Begin.setText("");
                End.setText("");
            }
        });
        return v;
    }
}