package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class MainMenu extends JPanel {
    private StudentApplication parent;

    private JButton addStudentButton, listButton, exitButton;
    private JTable table;
    private DBManager db;

    public MainMenu(StudentApplication parent) {
        this.parent = parent;

        setSize(500, 500);
        setLayout(null);

        addStudentButton = new JButton("ADD STUDENT");
        addStudentButton.setSize(300, 30);
        addStudentButton.setLocation(100, 100);
        add(addStudentButton);
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenuPage().setVisible(false);
                parent.getAddStudentToDBPage().setVisible(true);
//                parent.getListStudentsFromDBPage().setVisible(false);
            }
        });

        listButton = new JButton("LIST STUDENTS");
        listButton.setSize(300, 30);
        listButton.setLocation(100, 150);
        add(listButton);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Students> students = new ArrayList<>();
                try {
                    Socket socket = new Socket("127.0.0.1", 4703);
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                    PackageData pd = new PackageData("LIST_STUDENTS");
                    outputStream.writeObject(pd);
                    outputStream.reset();
                    students = (ArrayList<Students>) inputStream.readObject();
                } catch (Exception j) {
                    j.printStackTrace();
                }
                parent.getListStudentsFromDBPage().generateTable(students);
                parent.getMainMenuPage().setVisible(false);
                parent.getAddStudentToDBPage().setVisible(false);
                parent.getListStudentsFromDBPage().setVisible(true);
            }
        });


        exitButton = new JButton("EXIT");
        exitButton.setSize(300, 30);
        exitButton.setLocation(100, 200);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
