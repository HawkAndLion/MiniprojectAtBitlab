package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.net.Socket;

public class AddStudentToDBPage extends JPanel {
    private StudentApplication parent;
    private JButton addButton, backButton;
    private JLabel nameLabel, surnameLabel, ageLabel;
    private JTextField textField1, textField2, textField3;

    public AddStudentToDBPage(StudentApplication parent) {
        this.parent = parent;

        setSize(500, 500);
        setLayout(null);

        nameLabel = new JLabel("NAME:");
        nameLabel.setSize(70, 30);
        nameLabel.setLocation(100, 140);
        add(nameLabel);

        textField1 = new JTextField("Enter name");
        textField1.setSize(200, 30);
        textField1.setLocation(200, 140);
        add(textField1);

        surnameLabel = new JLabel("SURNAME:");
        surnameLabel.setSize(70, 30);
        surnameLabel.setLocation(100, 180);
        add(surnameLabel);

        textField2 = new JTextField("Enter surname");
        textField2.setSize(200, 30);
        textField2.setLocation(200, 180);
        add(textField2);

        ageLabel = new JLabel("AGE:");
        ageLabel.setSize(70, 30);
        ageLabel.setLocation(100, 220);
        add(ageLabel);

        textField3 = new JTextField("Enter age");
        textField3.setSize(200, 30);
        textField3.setLocation(200, 220);
        add(textField3);

        addButton = new JButton("ADD");
        addButton.setSize(150, 30);
        addButton.setLocation(100, 300);
        ArrayList<Students> students = new ArrayList<>();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Socket socket = new Socket("127.0.0.1", 4703);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    String name = textField1.getText();
                    String surname = textField2.getText();
                    int age = Integer.parseInt(textField3.getText());

                    Students student = new Students(null, name, surname, age);
                    PackageData pd = new PackageData("ADD_STUDENT", student);
                    outputStream.writeObject(pd);
                    outputStream.reset();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                parent.getMainMenuPage().setVisible(true);
                parent.getListStudentsFromDBPage().setVisible(false);
            }
        });
        add(addButton);

        backButton = new JButton("BACK");
        backButton.setSize(150, 30);
        backButton.setLocation(250, 300);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenuPage().setVisible(true);
                parent.getListStudentsFromDBPage().setVisible(false);
            }
        });
        add(backButton);
    }
}
