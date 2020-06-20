package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeManagerMain extends Fragment {
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.manager_main, container, false);

        Button btn_list = (Button) viewGroup.findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManagerList.class);
                startActivity(intent);
            }
        });

        Button btn_list_cancel = (Button) viewGroup.findViewById(R.id.btn_list_cancel);
        btn_list_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManagerListCancel.class);
                startActivity(intent);
            }
        });

        Button btn_list_sold = (Button) viewGroup.findViewById(R.id.btn_list_sold);
        btn_list_sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManagerListSold.class);
                startActivity(intent);
            }
        });

        return viewGroup;
    }
}
