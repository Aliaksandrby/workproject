package com.example.demo.service;

public class StringService {
    public static String getTypeOfFile(String str) {
        String newStr = "";
        for (int i = str.length()-1; i >= 0 ; i--) {
            if(str.charAt(i) != '.') {
                newStr += str.charAt(i);
            } else {
                break;
            }
        }
        String reversStr = "";
        for (int i = newStr.length()-1; i >= 0; i--) reversStr += newStr.charAt(i);
        return reversStr;
    }
}
