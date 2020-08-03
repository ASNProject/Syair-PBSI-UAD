package com.example.syair.Holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syair.Adapter.RequestContoh;
import com.example.syair.R;

public class ContohViewHolder extends RecyclerView.ViewHolder {
    public TextView pcontoh;
    public Button bcontoh;
    public ContohViewHolder(@NonNull View itemView) {
        super(itemView);
        pcontoh = itemView.findViewById(R.id.contoh);
        bcontoh = itemView.findViewById(R.id.pcontoh);
    }
    public void bindToPerusahaan(RequestContoh requestContoh, View.OnClickListener onClickListener){
       pcontoh.setText(requestContoh.judul);
        bcontoh.setOnClickListener(onClickListener);
    }
}
