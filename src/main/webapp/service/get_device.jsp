<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
      String name, em, model, pid, property = "", seri, shenase, section, status;
    String serial = "", hospital;
    String id = "";
    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {
        serial = request.getParameter("s");
        hospital = request.getParameter("h");
        id = request.getParameter("i");

        rs = m1.result("SELECT * FROM `piece` inner join section as s on `FK_section` = section_id inner join `hospital` on FK_hospital = hospital_id inner join device_type on `FK_device_type` = device_type_id inner join device_name as n on FK_device_name = device_name_id"
                + " where hospital_id = "+hospital+" and `serial_number` = '"+serial+"'");
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
            temp = rs.getString("FK_hospital");
        } catch (Exception ex) {
            out.print("[{'flag':'1'}]");
        }
        if (temp.length() == 0) {

        } else {

            rs.beforeFirst();
            String result = "[{'flag':'2',";
          
            while (rs.next()) {

                name = rs.getString("n.name");
                model = rs.getString("model");
                em = rs.getString("em_number");
                pid = rs.getString("piece_id");
                property = rs.getString("property_number");
                seri = rs.getString("serial_number");
                shenase = rs.getString("device_id");
                section = rs.getString("s.name");
                status = rs.getString("FK_piece_status");

                result += "'property':'" + property + "'}";
                if (!rs.isLast()) {
                    result += ",";

                }
            }
            result += "]";
            m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' اسکن بارکد"+property+"', CURRENT_TIMESTAMP, '"+id+"');");
            out.print(result);

        }
    }

%>
