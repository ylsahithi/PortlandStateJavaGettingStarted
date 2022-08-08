package edu.pdx.cs410J.lakshmiy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Readme#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Readme extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Readme() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Readme.
     */
    // TODO: Rename and change types and number of parameters
    public static Readme newInstance(String param1, String param2) {
        Readme fragment = new Readme();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        View v = inflater.inflate(R.layout.fragment_readme, container, false);
        TextView readme = v.findViewById(R.id.readme_text);
        Button close = v.findViewById(R.id.close);

        readme.setText("Readme of phone bill application \n");
        readme.append("This application performs 3 actions, to add phone call to phone log \n");
        readme.append("To search for phone call based on customer name \n");
        readme.append("To search for phone call based on customer name, begin and end date. It returns calls that are between begin time and end time \n");
        readme.append("The input parameters are customer name as string, phone number is number that can contain - \n");
        readme.append("The begin and end dates are expected to bbe in mm/dd/yyyy hh:mm am/pm format \n");
        readme.append("This application stores data in internal file system \n");
        readme.append("To view the data you have added check the print checkbox in add phone call section \n");
        readme.append("To search based on customer name just give input for customer field alone \n To search for a call at a particular time give input for customer, begin and end time fields \n");
        readme.append("In case of error a new window shows the error messages close it and try giving input again \n");
        readme.append("This application shows high level error as invalid arguments, to know detailed error log check Run log \n");
        readme.setMovementMethod(new ScrollingMovementMethod());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return v;
    }
}