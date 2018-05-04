<%@page import="java.sql.ResultSet"%>
<%@page import="com.data.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String id;
    Model m1 = new Model();
    id = request.getParameter("id");
    try {
        m1.Delete("DELETE FROM `answer` WHERE `FK_piece` = "+ id);
    }catch(Exception e){
        
    }
%>
