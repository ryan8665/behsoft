
package com.data;


import java.sql.*;

public class Model {

    public Connection c1;
    protected String url;

    public Model() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // url = "jdbc:mysql://localhost:3306/ryan?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
           // c1 = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public boolean insert(String sql) {
        try {
             Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/ryan?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
            c1 = DriverManager.getConnection(url);
            String q = sql;
            Statement st = c1.createStatement();
            st.executeUpdate(q);
           
            return false;
        } catch (Exception ex) {

            return true;
        } finally {

        }
    }

    public boolean Delete(String sql) {
        try {
             Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/ryan?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
            c1 = DriverManager.getConnection(url);
            String q = sql;
            Statement st = c1.createStatement();

            st.executeUpdate(q);
            st.close();
            c1.close();
            return false;
        } catch (Exception ex) {

            return true;
        } finally {

        }
    }

    public String select(String sql) {

        try {
             Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/ryan?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
            c1 = DriverManager.getConnection(url);
            Statement st = c1.createStatement();
            ResultSet rs2 = st.executeQuery(sql);
            String select = "";

            while (rs2.next()) {

                select = rs2.getString(1);

            }
           c1.close();
           rs2.close();
            return select;

        } catch (Exception e) {
            
            return e.toString();
        }

    }
    
    public Date Date(String sql) {

        try {
             Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/ryan?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
            c1 = DriverManager.getConnection(url);
            Statement st = c1.createStatement();
            ResultSet rs2 = st.executeQuery(sql);
            Date select = null ;

            while (rs2.next()) {

                select = rs2.getDate(1);

            }
           c1.close();
           rs2.close();
            return select;

        } catch (Exception e) {
            
            return null;
        }

    }

    public ResultSet result(String sql) {
        try {
             Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/ryan?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
            c1 = DriverManager.getConnection(url);
            Statement st = c1.createStatement();
            ResultSet rs2 = st.executeQuery(sql);
          
          
            return rs2;

        } catch (Exception e) {

            System.out.println(e);

            return null;
        }

    }
    

}
