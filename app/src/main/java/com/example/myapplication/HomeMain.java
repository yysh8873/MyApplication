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

        Button btn_delivery = (Button) viewGroup.findViewById(R.id.btn_delivery);
        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Order.class);
                startActivity(intent);
            }
        });

        Button btn_pick = (Button) viewGroup.findViewById(R.id.btn_pick);
        btn_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Order.class);
                startActivity(intent);
            }
        });

        Button btn_hall = (Button) viewGroup.findViewById(R.id.btn_hall);
        btn_hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Order.class);
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

        Button btn_pay = (Button) viewGroup.findViewById(R.id.fndpay);
        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), App.class);
                startActivity(intent);
            }
        });

        return viewGroup;
    }

}

