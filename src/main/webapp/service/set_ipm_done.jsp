<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        String w, q, qid;

        String prop = "", id = "", device = "", type = "";
        boolean flag = false;
        ResultSet rs = null;
        Model m1 = new Model();
        try {

            id = request.getParameter("i");
            prop = request.getParameter("p");

            m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت آی‌پی‌ام - اندروید " + prop + "', CURRENT_TIMESTAMP, '" + id + "');");
            flag = false;
        } catch (Exception e) {
            flag = true;
        }

    

%>
