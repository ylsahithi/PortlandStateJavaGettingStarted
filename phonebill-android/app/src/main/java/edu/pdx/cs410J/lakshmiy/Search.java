package edu.pdx.cs410J.lakshmiy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Search() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Search.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Search newInstance(String param1, String param2) {
//        Search fragment = new Search();
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
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        EditText Customer = v.findViewById(R.id.customer_name_et);
        EditText Begin = v.findViewById(R.id.begin_date_et);
        EditText End = v.findViewById(R.id.end_date_et);
        Button search_btn = v.findViewById(R.id.Search_action);
        search_btn.setOnClickListener(new View.OnClickListener() {
            String customer = null;
            String begin_date = null;
            String end_date = null;

            @Override
            public void onClick(View view) {
                customer = Customer.getText().toString().trim();
                System.out.println(customer);
                begin_date = Begin.getText().toString().trim();
                end_date = End.getText().toString().trim();
                ValidateArgs va = new ValidateArgs();
                boolean cust_check = va.checkForvalidString(customer);
                if (cust_check) {
                    if (begin_date.isEmpty() && end_date.isEmpty()) {
                        Intent intent = new Intent(getActivity(), PrettyPrinter.class);
                        intent.putExtra("Customer", customer);
                        startActivity(intent);
                    } else {
                        String valid_flag = va.validateSelectedArg(customer, begin_date, end_date);
                        System.out.println(valid_flag + "valid_flag");
                        if (valid_flag.length() > 1) {
                            System.err.println("Invalid arguments passed as parameters");
                            valid_flag = valid_flag + "Invalid arguments passed as parameters";
                            Intent intent = new Intent(getActivity(), ErrorPopup.class);
                            intent.putExtra("Error", valid_flag);
                            startActivity(intent);
                            Customer.setText("");
                            Begin.setText("");
                            End.setText("");
                        } else {
                            Intent intent = new Intent(getActivity(), PrettyPrinter.class);
                            PhoneCall pc = new PhoneCall(customer, "", "", begin_date, end_date);
                            intent.putExtra("Customer", customer);
                            intent.putExtra("Begin", begin_date);
                            intent.putExtra("End", end_date);
                            startActivity(intent);
                        }
                    }
                }
                Customer.setText("");
                Begin.setText("");
                End.setText("");
            }
        });
        return v;
    }
}