package com.company;


import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        db.connect();

        try {
            ServerSocket server4703 = new ServerSocket(4703);
            while (true) {
                Socket socket4703 = server4703.accept();
                System.out.println("Client connected");

                ClientHandler clientHandler = new ClientHandler(socket4703, db);
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



///&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& более или менее работающая версия
/*

public class Server {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        db.connect();

        ArrayList<Students> students = db.getAllStudents();
//        String operationType = "LIST_STUDENTS";
//        PackageData pd = new PackageData(operationType,students);

        for (Students st : students) {
            System.out.println("List of Students BEFORE 'server.accept()' at Server\n" + st + "\n");
        }
        System.out.println("******************\n");

        try {
            ServerSocket server = new ServerSocket(4702);
            try {
//            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected");

                ClientHandler clientHandler = new ClientHandler(socket, db);
                clientHandler.start();

                try {
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    PackageData data = (PackageData) inputStream.readObject();
                    System.out.println("Test: printing PackageData on Server \n" + data);
                    System.out.println(data.getStudent().getName());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//            if ("ADD_STUDENT".equals(data.getOperationType())) {
//                        Students student = new Students(null, data.getStudent().getName(), data.getStudent().getSurname(), data.getStudent().getAge());
//                        db.addNewStudent(student);
//                    } else {
//                        ArrayList<Students> students = db.getAllStudents();
//                        for (Students st : students) {
//                            System.out.println(st);
//                        }
//                    }
////            PackageData pd = null;
////            while((pd = (PackageData) inputStream.readObject()) != null){
////                db.addNewStudent(pd.getStudent());
////            }



//        students = db.getAllStudents();
//        for (Students st : students) {
//            System.out.println("List of Students AFTER 'server.accept()' at Server\n" + st);
//        }
        System.out.println("******************\n");

    }
}
*/
