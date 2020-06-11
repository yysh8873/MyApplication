package com.example.myapplication;

import android.content.DialogInterface;

public class Data {

    private String title;       // 메뉴명
    private String content;     // 가격 및 주문자 이름
    private String content2;    // 가격
    private String date;        // 날짜
    private String btn;

    private String dlgTitle;
    private String dlgMsg;
    private DialogInterface.OnClickListener dlgPB;
    private String[] dlgItems;
    private int i;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public String getDlgTitle() {
        return dlgTitle;
    }

    public void setDlgTitle(String dlgTitle) {
        this.dlgTitle = dlgTitle;
    }

    public String getDlgMsg() {
        return dlgMsg;
    }

    public void setDlgMsg(String dlgMsg) {
        this.dlgMsg = dlgMsg;
    }


    public DialogInterface.OnClickListener getDlgPB() {
        return dlgPB;
    }

    public void setDlgPB(DialogInterface.OnClickListener dlgPB) {
        this.dlgPB = dlgPB;
    }



}