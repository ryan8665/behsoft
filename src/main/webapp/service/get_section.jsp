<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String name, sid, hospital;

    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {
        hospital = request.getParameter("h");
        rs = m1.result("SELECT * FROM `section` where `FK_hospital` = " + hospital + " ORDER BY `name`");
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
            temp = rs.getString("section_id");
        } catch (Exception ex) {
            out.print("[{'flag':'1'}]");
        }
        if (temp.length() == 0) {

        } else {

            rs.beforeFirst();
            String result = "[";

            while (rs.next()) {
                result += "{'flag':'2',";
                name = rs.getString("name");
                sid = rs.getString("section_id");

                result += "'name':'" + name
                        + "','sid':'" + sid + "'}";
                if (!rs.isLast()) {
                    result += ",";

                }
            }
            result += "]";
            out.print(result);

        }
    }

%>
