<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
      String name, did, model, pid, property = "";
    String hospital;
    String id = "";
       String prop = "";
    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {
       
        hospital = request.getParameter("h");
        id = request.getParameter("i");
        prop = request.getParameter("p");
        rs = m1.result("SELECT `property_number`,piece_id,device_type_id,n.name,model FROM `piece` inner join section as s on `FK_section` = section_id inner join `hospital` on FK_hospital = hospital_id inner join device_type on `FK_piece_status` = device_type_id inner join device_name as n on FK_device_name = device_name_id "
                + "where hospital_id = "+hospital+" and `property_number` like '"+prop+"%' ORDER BY `property_number`");
        
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
            temp = rs.getString("piece_id");
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
                pid = rs.getString("piece_id");
                property = rs.getString("property_number");
                did = rs.getString("device_type_id");

                result += "'name':'" + name
                        + "','model':'" + model
                        + "','pid':'" + pid
                        + "','property':'" + property
                        + "','did':'" + did +"'}";
                if (!rs.isLast()) {
                    result += ",";

                }
            }
            result += "]";
           m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' جستجوی دستگاه - اندروید', CURRENT_TIMESTAMP, '"+id+"');");
            out.print(result);

        }
    }

%>
