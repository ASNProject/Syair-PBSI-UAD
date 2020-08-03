package com.example.syair.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syair.Adapter.RequestNilai;
import com.example.syair.R;

public class NilaiHolder extends RecyclerView.ViewHolder {

    public TextView datetime, values;

    public NilaiHolder(@NonNull View itemView) {
        super(itemView);
        datetime= itemView.findViewById(R.id.times);
        values = itemView.findViewById(R.id.value);
    }
    public void bindToPerusahaan(RequestNilai requestNilai, View.OnClickListener onClickListener){
        datetime.setText(requestNilai.tanggal);
        values.setText(requestNilai.nilai);
    }

}
