package com.company;

import javax.swing.*;

public class StudentApplication extends JFrame {
    private MainMenu mainMenuPage;
    private AddStudentToDBPage addStudentToDBPage;
    private ListStudentsFromDBPage listStudentsFromDBPage;


    public StudentApplication() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("STUDENT APPLICATION");
        setSize(500, 500);
        setLayout(null);

        mainMenuPage = new MainMenu(this);
        mainMenuPage.setVisible(true);
        add(mainMenuPage);

        addStudentToDBPage = new AddStudentToDBPage(this);
        addStudentToDBPage.setVisible(false);
        add(addStudentToDBPage);

        listStudentsFromDBPage = new ListStudentsFromDBPage(this);
        listStudentsFromDBPage.setVisible(false);
        add(listStudentsFromDBPage);
    }

    public MainMenu getMainMenuPage() {
        return mainMenuPage;
    }

    public AddStudentToDBPage getAddStudentToDBPage() {
        return addStudentToDBPage;
    }

    public ListStudentsFromDBPage getListStudentsFromDBPage() {
        return listStudentsFromDBPage;
    }
}
