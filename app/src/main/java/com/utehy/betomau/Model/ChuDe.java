package com.utehy.betomau.Model;

import java.util.List;

public class ChuDe {
    private String tenchude;
    private int hinhanh;
    private List<Integer>listHinhAnh;

    public ChuDe( String tenchude, int hinhanh, List<Integer> listHinhAnh) {

        this.tenchude = tenchude;
        this.hinhanh = hinhanh;
        this.listHinhAnh = listHinhAnh;
    }

    public ChuDe() {

    }



    public String getTenchude() {
        return tenchude;
    }

    public void setTenchude(String tenchude) {
        this.tenchude = tenchude;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public List<Integer> getListHinhAnh() {
        return listHinhAnh;
    }

    public void setListHinhAnh(List<Integer> listHinhAnh) {
        this.listHinhAnh = listHinhAnh;
    }
}
