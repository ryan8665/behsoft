<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String pid = "";
    String prop = "", hospital;
    String id = "";
    String disc = "";
    String alias = "";
    String calibration ="";
    boolean res = true;
    Model m1 = new Model();
    pid = request.getParameter("p");
    prop = request.getParameter("pp");
    id = request.getParameter("i");
    disc = request.getParameter("d");
    alias = request.getParameter("a");
    calibration = request.getParameter("c");

    res = m1.insert("INSERT INTO `ryan`.`calibration` (`calibration_id`, `execution_date`, `alias`, `description`, `FK_user`, `FK_piece`, `FK_calibration_result`) VALUES "
            + "(NULL, NOW(), '" + alias + "', '" + disc + "', '"+id+"', '"+pid+"', '"+calibration+"');");
    if (res) {
        out.print("[{'flag':'0'}]");
    } else {
        m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت کنترل کیفی‌ - اندروید‌ " + prop + "‌', CURRENT_TIMESTAMP, '" + id + "');");
        out.print("[{'flag':'1'}]");
    }


%>
