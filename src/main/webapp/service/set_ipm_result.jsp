<%@page import="com.data.Model"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        String qid,pid,uid,done,required,answer;
        boolean flag = false;
        ResultSet rs = null;
        Model m1 = new Model();
        try {
            
            qid = request.getParameter("q");
            pid = request.getParameter("p");
            uid = request.getParameter("u");
            done = request.getParameter("d");
            required = request.getParameter("r");
            answer = request.getParameter("a");


           flag =  m1.insert("INSERT INTO `answer` (`answer_id`, `answer`, `required`, `done`, `FK_user`, `FK_piece`, `FK_question`) VALUES "
                    + "(NULL, '"+answer+"', '"+required+"', '"+done+"', '"+uid+"', '"+pid+"', '"+qid+"');");
           
        } catch (Exception e) {
            
        }

    

%>
