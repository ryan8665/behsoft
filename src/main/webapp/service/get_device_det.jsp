<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
      String name, pid ,em, model, user_guide, property = "", seri, shenase, section, status,maintenance,brief_manual,key_reference,nid;
    String prop = "",hospital;
    String id = "";
    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {
        prop = request.getParameter("p");
        hospital = request.getParameter("h");
        id = request.getParameter("i");

        rs = m1.result("SELECT * FROM `piece` inner join section as s on `FK_section` = section_id inner join `hospital` on FK_hospital = hospital_id inner join device_type on `FK_device_type` = device_type_id inner join device_name as n on FK_device_name = device_name_id "
                + "where hospital_id = "+hospital+" and `property_number` = '"+prop+"'");
        
        
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
            String result = "[{'flag':'2',";
          
            while (rs.next()) {

                name = rs.getString("n.name");
                nid = rs.getString("n.device_name_id");
                model = rs.getString("model");
                em = rs.getString("em_number");
                user_guide = rs.getString("user_guide");
                property = rs.getString("property_number");
                seri = rs.getString("serial_number");
                shenase = rs.getString("device_id");
                section = rs.getString("s.name");
                maintenance = rs.getString("maintenance");
                brief_manual = rs.getString("brief_manual");
                key_reference = rs.getString("user_guide");
                status = rs.getString("FK_piece_status");
                pid = rs.getString("piece_id");

                result += "'name':'" + name
                        + "','model':'" + model
                          + "','nid':'" + nid
                        + "','em':'" + em
                        + "','pid':'" + user_guide
                        + "','property':'" + property
                        + "','seri':'" + seri
                        + "','shenase':'" + shenase
                        + "','maintenance':'" + maintenance
                        + "','brief_manual':'" + brief_manual
                        + "','user_guide':'" + key_reference
                        + "','section':'" + section
                          + "','pid':'" + pid
                        + "','status':'" + status + "'}";
                if (!rs.isLast()) {
                    result += ",";

                }
            }
            result += "]";
            m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' مشاهده شناسنامه تجهیز - اندروید"+property+"', CURRENT_TIMESTAMP, '"+id+"');");
            out.print(result);

        }
    }

%>
