<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String prop = "";
    String hospital;
    String id = "";
    String serial_number, device_id, subdate, FK_device_type, FK_section;

    boolean res = true;
    Model m1 = new Model();
    hospital = request.getParameter("h");
    prop = request.getParameter("pp");
    id = request.getParameter("i");
    serial_number = request.getParameter("si");
    FK_device_type = request.getParameter("t");
    FK_section = request.getParameter("s");
    device_id = request.getParameter("sh");

    String r = m1.select("SELECT `property_number` FROM `piece` inner join section on section_id = `FK_section` where FK_hospital = " + hospital + " and `property_number` = '" + prop + "'");
    if (r == null || r.equals(prop)) {
        out.print("[{'flag':'0'}]");
    } else {
        res = m1.insert("INSERT INTO `piece` (`piece_id`, `property_number`, `serial_number`, `device_id`, `subdate`, `FK_device_type`, `FK_section`, `FK_piece_status`) VALUES "
                + "(NULL, '" + prop + "', '" + serial_number + "', '" + device_id + "', CURRENT_TIMESTAMP, '" + FK_device_type + "', '" + FK_section + "', '1');");
        if (res) {
            out.print("[{'flag':'1'}]");
        } else {
            m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت شناسنامه جدید‌ - اندروید‌ " + prop + "‌', CURRENT_TIMESTAMP, '" + id + "');");
            out.print("[{'flag':'2'}]");
        }
    }


%>
