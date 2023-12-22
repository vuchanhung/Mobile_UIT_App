package com.example.bigproject.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Diemdanh {
    private ArrayList<Map<String, Object>> hocsinh;

    public Diemdanh() {
        // Constructor mặc định cần thiết cho Firebase
        this.hocsinh = new ArrayList<>();
    }

    public Diemdanh(ArrayList<Map<String, Object>> hocsinh) {
        this.hocsinh = hocsinh;
    }

    public ArrayList<Map<String, Object>> getHocsinh() {
        return hocsinh;
    }

    public void setHocsinh(ArrayList<Map<String, Object>> hocsinh) {
        this.hocsinh = hocsinh;
    }

    // Thêm một học sinh mới vào danh sách
    public void addHocSinh(String mssv, String solanDiemDanh, Date thoigianDiemDanh) {
        Map<String, Object> hocSinhMap = new HashMap<>();
        hocSinhMap.put("mssv", mssv);
        hocSinhMap.put("solandiemdanh", solanDiemDanh);
        hocSinhMap.put("thoigian", thoigianDiemDanh);

        // Thêm thông tin học sinh vào danh sách
        this.hocsinh.add(hocSinhMap);
    }
}
