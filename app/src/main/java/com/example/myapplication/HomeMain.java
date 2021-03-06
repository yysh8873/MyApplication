package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeMain extends Fragment{
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_main,container,false);

        Bundle bundle = getArguments();
        final String uid = bundle.getString("uid");

        Button btn_delivery = (Button) viewGroup.findViewById(R.id.btn_delivery);
        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Order.class);
                startActivity(intent);
            }
        });

        Button btn_charge = (Button) viewGroup.findViewById(R.id.btn_charge);
        btn_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Charge.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });

                Button btn_menu = (Button) viewGroup.findViewById(R.id.btn_menu);
                btn_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Menu.class);
                startActivity(intent);
            }
        });

        Button btn_info = (Button) viewGroup.findViewById(R.id.btn_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyInfo.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });

        Button btn_app = (Button) viewGroup.findViewById(R.id.btn_app);
        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), App.class);
                startActivity(intent);
            }
        });

        Button btn_pay = (Button) viewGroup.findViewById(R.id.btpay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PayBTFriend.class);
                startActivity(intent);
            }
        });

        return viewGroup;
    }

}
