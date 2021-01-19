package com.jinseong.soft;

import java.sql.SQLOutput;

public class Application {

    public static void main(String[] args) {
        String str = "Kim";
        String str1 = new String("Kim");

        System.out.println(System.identityHashCode(str));
        System.out.println(System.identityHashCode(str1));
    }
}
