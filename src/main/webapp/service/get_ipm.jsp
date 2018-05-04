<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String w, q, qid;

    String prop = "", id = "",device = "",type="";
    boolean flag = false;
    ResultSet rs = null;
    Model m1 = new Model();
    try {

        id = request.getParameter("i");
        prop = request.getParameter("p");
        device = request.getParameter("d");

        rs = m1.result("SELECT * FROM `question` inner join `question_type` on `FK_question_type` = question_type_id inner join `device_name` on `FK_device_name` = device_name_id where `FK_device_name` = "+device);
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
            temp = rs.getString("question_id");
        } catch (Exception ex) {
            out.print("[{'flag':'1'}]");
        }
        if (temp.length() == 0) {

        } else {
            String result = "[";
            rs.beforeFirst();

            while (rs.next()) {
                result += "{'flag':'2'";
                w = rs.getString("ratio");
                q = rs.getString("text");
                qid = rs.getString("question_id");
                type = rs.getString("FK_question_type");

                result += ",'w':'" + w + "','q':'" + q + "','qid':'" + qid + "','type':'" + type + "'}";
                if (!rs.isLast()) {
                    result += ",";

                }
            }
            result += "]";
            m1.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'بارگذاری آی‌پی‌ام - اندروید " + prop + "', CURRENT_TIMESTAMP, '" + id + "');");
            out.print(result);

        }
    }

%>
