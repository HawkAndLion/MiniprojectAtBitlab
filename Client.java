package com.company;

import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket4703 = new Socket("127.0.0.1", 4703);
            StudentApplication frame = new StudentApplication();
            frame.setVisible(true);
            frame.setResizable(false);
            while (true){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Работающая версия для GUI (вводятся данные и выводятся данные в виде таблицы)
//public class Client {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//
//        StudentApplication frame = new StudentApplication();
//        frame.setVisible(true);
//        frame.setResizable(false);
//    }
//}
