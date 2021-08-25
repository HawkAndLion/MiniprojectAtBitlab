package com.company;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class DBManager implements Serializable {
    private Connection connection;

    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/miniproject?useUnicode=true&serverTimezone=UTC","root", ""
            );
            System.out.println("CONNECTED TO DATABASE");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Students> getAllStudents(){
        ArrayList<Students> students = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");

                students.add(new Students(id,name,surname,age));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return students;
    }

    public void addNewStudent(Students student){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO students(id, name, surname, age) values(NULL,?,?,?)");

            st.setString(1,student.getName());
            st.setString(2,student.getSurname());
            st.setInt(3,student.getAge());

            st.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
