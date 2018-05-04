<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String model, did;
    String d = request.getParameter("d");
    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {
        rs = m1.result("SELECT device_type_id,model FROM `device_type` WHERE `FK_device_name` = " + d + " ORDER BY model");
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
            temp = rs.getString("device_type_id");
        } catch (Exception ex) {
            out.print("[{'flag':'1'}]");
        }
        if (temp.length() == 0) {

        } else {

            rs.beforeFirst();
            String result = "[";

            while (rs.next()) {
                result += "{'flag':'2',";
                model = rs.getString("model");
                did = rs.getString("device_type_id");

                result += "'name':'" + model
                        + "','did':'" + did + "'}";
                if (!rs.isLast()) {
                    result += ",";

                }
            }
            result += "]";
            out.print(result);

        }
    }

%>
