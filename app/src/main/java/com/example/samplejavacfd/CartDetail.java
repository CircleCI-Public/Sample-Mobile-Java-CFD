package com.samplejavacfd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static CartDetail newInstance(String param1, String param2) {
        CartDetail fragment = new CartDetail();
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
        return inflater.inflate(R.layout.fragment_cart_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer cost = CartDetailArgs.fromBundle(getArguments()).getFoodCost();
        String name = CartDetailArgs.fromBundle(getArguments()).getFoodName();
        Integer quantity = CartDetailArgs.fromBundle(getArguments()).getFoodQuantity();

        int total = 0;
        String foodList = "";
        String costList = "";
        for(int i = 1; i <= quantity; i++)
        {
            foodList += (name + " \n");
            costList += ("$" + cost + "\n");
            total += cost;
        }

        String totalCost = ("$" + total);
        TextView foodView = view.getRootView().findViewById(R.id.cart_items);
        foodView.setText(foodList);

        TextView costView = view.getRootView().findViewById(R.id.cart_costs);
        costView.setText(costList);

        TextView quantityView = view.getRootView().findViewById(R.id.cart_total);
        quantityView.setText(totalCost);

        view.findViewById(R.id.empty_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CartDetail.this).navigate(CartDetailDirections.actionCartDetailToFoodMenu());
            }
        });
    }
}