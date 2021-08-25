package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListStudentsFromDBPage extends JPanel {
    private StudentApplication parent;

    private JButton backButton;
    private String[] header = {"ID", "NAME", "SURNAME", "AGE"};
    private JTable table;
    private JScrollPane scrollPane;

    public ListStudentsFromDBPage(StudentApplication parent){
        this.parent = parent;

        setSize(500, 500);
        setLayout(null);

        backButton = new JButton("BACK");
        backButton.setSize(300, 30);
        backButton.setLocation(100, 400);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenuPage().setVisible(true);
                parent.getAddStudentToDBPage().setVisible(false);
                parent.getListStudentsFromDBPage().setVisible(false);
            }
        });
        add(backButton);

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(500, 400);
        scrollPane.setLocation(0, 0);
        add(scrollPane);
    }

    public void generateTable(ArrayList<Students> students) {
        Object[][] data = new Object[students.size()][4];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getId();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getSurname();
            data[i][3] = students.get(i).getAge();
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);
    }
}