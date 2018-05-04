<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
      String name, did, model, pid, property = "";
    String hospital;
    String id = "";
 
    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {
       
        hospital = request.getParameter("h");
        id = request.getParameter("i");
        

        rs = m1.result("SELECT `property_number`,piece_id,device_type_id,n.name,model FROM `piece` inner join section as s on `FK_section` = section_id inner join `hospital` on FK_hospital = hospital_id inner join device_type on `FK_device_type` = device_type_id inner join device_name as n on FK_device_name = device_name_id "
                + "where hospital_id = "+hospital+" ORDER BY piece_id DESC");
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
            String result="[";
          
            while (rs.next()) {
                result += "{'flag':'2',";
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
             m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'مشاهده لیست تجهیزات - اندروید', CURRENT_TIMESTAMP, '"+id+"');");
            
            out.print(result);

        }
    }

%>
