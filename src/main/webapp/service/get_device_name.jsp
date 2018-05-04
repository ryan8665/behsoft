<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
      String name, did;

    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {
        rs = m1.result("SELECT * FROM `device_name` ORDER BY name");
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
            temp = rs.getString("device_name_id");
        } catch (Exception ex) {
            out.print("[{'flag':'1'}]");
        }
        if (temp.length() == 0) {

        } else {

            rs.beforeFirst();
            String result="[";
          
            while (rs.next()) {
                result += "{'flag':'2',";
                name = rs.getString("name");
                did = rs.getString("device_name_id");


                result += "'name':'" + name
                        + "','did':'" + did +"'}";
                if (!rs.isLast()) {
                    result += ",";

                }
            }
            result += "]";
            out.print(result);

        }
    }

%>
