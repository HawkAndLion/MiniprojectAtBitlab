package com.company;
import java.io.*;
import java.net.*;


public class ClientHandler extends Thread{
    private Socket socket4703;
    private DBManager dbManager;
    public ClientHandler(Socket socket, DBManager dbManager) {
        this.socket4703 = socket;
        this.dbManager = dbManager;
    }

   public void run() {
           try {
               System.out.println("ClientHandler is connected");
               ObjectOutputStream outputStream = new ObjectOutputStream(socket4703.getOutputStream());
               ObjectInputStream inputStream = new ObjectInputStream(socket4703.getInputStream());

               while (true) {
                   PackageData data = (PackageData) inputStream.readObject();
                   if (data.getOperationType().equals("ADD_STUDENT")) {
                       dbManager.addNewStudent(data.getStudent());
                   }else{
                       outputStream.writeObject(dbManager.getAllStudents());
                       outputStream.reset();
                   }
//                   inputStream.close();
               }

           } catch(Exception e){
               e.printStackTrace();
           }
    }
}
