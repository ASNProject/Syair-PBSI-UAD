package com.example.syair.Adapter;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class RequestContoh {
    public String judul;

    public RequestContoh() {
    }

    public RequestContoh(String judul) {
        this.judul = judul;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("judul", judul);
        return result;
    }
}
