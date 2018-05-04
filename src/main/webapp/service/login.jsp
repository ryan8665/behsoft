<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String user = "", pass;
    String id = "";
    boolean flag = false;
    ResultSet rs = null;
      Model m1 = new Model();
    try {
        user = request.getParameter("u");
        pass = request.getParameter("p");
      
        rs = m1.result("SELECT * FROM user WHERE username = '" + user + "' AND password = '" + pass + "' ;");
        flag = false;
    } catch (Exception e) {
        flag = true;
    }

    if (flag) {
        out.print("[{'flag':'0'}]");
    } else if (!flag) {
        String temp = "";
        try {
            rs.next();
            temp = rs.getString("user_id");
        } catch (Exception ex) {
             out.print("[{'flag':'1'}]");
        }
        if (temp.length() == 0) {

        } else {
              rs.beforeFirst();
            String result = "[{'flag':'2',";
           
            while (rs.next()) {
                 String tid, name, family, hid;
                id = tid = rs.getString("user_id");
                name = rs.getString("name");
                family = rs.getString("family");
                hid = rs.getString("FK_hospital");
                result += "'u_id':'" + tid + "','name':'" + name + "','family':'" + family + "','hospital':'" + hid + "'}";
                if (!rs.isLast()) {
                    result += ",";
                     
                }
            }
            result += "]";
            m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ورود به سیستم - اندروید', CURRENT_TIMESTAMP, '" + id + "');");
            out.print(result);

        }
    }

%>
