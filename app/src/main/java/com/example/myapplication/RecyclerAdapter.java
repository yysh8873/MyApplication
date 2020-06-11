package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list
    private ArrayList<Data> listData = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate
        // return 인자는 ViewHolder
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 보여주는(bind 되는) 함수
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수
        return listData.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가
        listData.add(data);
    }

    // RecyclerView의 ViewHolder
    // subView setting
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private Data data;
        private Button btn;
        LinearLayout linearItem;

        ItemViewHolder(View itemView) {
            super(itemView);
            linearItem = itemView.findViewById(R.id.linearItem);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            btn = itemView.findViewById(R.id.btn);
        }

        void onBind(Data data) {
            this.data = data;

            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            textView3.setText(data.getDate());
            textView4.setText(data.getContent2());
            btn.setText(data.getBtn());

            itemView.setOnClickListener(this);
            textView1.setOnClickListener(this);
            textView2.setOnClickListener(this);
            textView3.setOnClickListener(this);
            textView4.setOnClickListener(this);
            btn.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn:
                    Toast.makeText(context, "TITLE : " + data.getTitle() + "\nContent : " + data.getContent(), Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                    dlg.setTitle(data.getDlgTitle());
                    dlg.setMessage(data.getDlgMsg());
                    dlg.setIcon(R.drawable.chicken);
                    dlg.setPositiveButton("확인", data.getDlgPB());
                    dlg.setNegativeButton("취소", null);
                    dlg.show();
                    break;
               /* case R.id.textView1:
                    Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView2:
                    Toast.makeText(context, data.getContent(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView3:
                    Toast.makeText(context, data.getDate(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView4:
                    Toast.makeText(context, data.getContent2(), Toast.LENGTH_SHORT).show();
                    break;*/
            }
        }
    }
}