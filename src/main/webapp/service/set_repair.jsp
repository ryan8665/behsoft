<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String pid = "";
    String prop = "", hospital;
    String id = "";
    boolean res = true;
    Model m1 = new Model();
    pid = request.getParameter("p");
    prop = request.getParameter("pp");
    id = request.getParameter("i");

    res = m1.insert("INSERT INTO `ryan`.`maintenance` (`maintenace_id`, `FK_failure_annunciator`, `failure_date`, `FK_repair_annunciator`, `repair_date`, `description`, `FK_maintenace_status`, `FK_piece`) VALUES "
            + "(NULL, '" + id + "', CURRENT_TIMESTAMP, '" + id + "', NULL, NULL, '1', '" + pid + "');");
    if (res) {
        out.print("[{'flag':'0'}]");
    } else {
        m1.Delete("UPDATE `piece` SET `FK_piece_status`= 2 WHERE `piece_id` = " + pid);
        m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' ثبت گزارش خرابی‌ - اندروید " + prop + "‌', CURRENT_TIMESTAMP, '" + id + "');");
        out.print("[{'flag':'1'}]");
    }


%>
