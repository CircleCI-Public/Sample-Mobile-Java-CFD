package com.samplejavacfd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HamburgerDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HamburgerDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HamburgerDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HamburgerDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static HamburgerDetail newInstance(String param1, String param2) {
        HamburgerDetail fragment = new HamburgerDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hamburger_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView countView = view.getRootView().findViewById(R.id.burger_quantity);
        String countString = countView.getText().toString();
        final Integer[] count = {Integer.parseInt(countString)};

        view.findViewById(R.id.burger_plus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0]++;
                TextView quantityView = view.getRootView().findViewById(R.id.burger_quantity);
                quantityView.setText(count[0].toString());
            }
        });

        view.findViewById(R.id.burger_minus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count[0] > 1) {
                    count[0]--;
                }
                TextView quantityView = view.getRootView().findViewById(R.id.burger_quantity);
                quantityView.setText(count[0].toString());
            }
        });

        view.findViewById(R.id.add_to_cart_burger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               HamburgerDetailDirections.ActionHamburgerDetailToCartDetail HamburgerAction = HamburgerDetailDirections.actionHamburgerDetailToCartDetail(count[0], "Hamburger", 12);
               NavHostFragment.findNavController(HamburgerDetail.this).navigate(HamburgerAction);
            }
        });
    }
}