<%-- 
    Document   : form
    Created on : Aug 21, 2015, 8:35:54 PM
    Author     : Ryan
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Error/noIPM.xhtml" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.data.*"%>
<%@page import="com.utility.*"%>

<%    
    int counter = 0;
    Model m1 = new Model();
    ResultSet rs;
    float t1, t2, c = 0;
    float sum = 0;
    float total = 0;
    float totalscor = 0;
    int y = 0;
    boolean faild = false;
    boolean yellow = false;
    Date time = new Date();
   
    String p = request.getParameter("p");
    if(p.isEmpty()||p.equals("0")){
         response.sendRedirect("/Error/noIPM.xhtml");
    }
    int ch = 0;
    String todo = " ", will = " ", det = " ", serial = "", prop = "", section = "", user = "", name = " ", mark = " ";
    rs = m1.result("SELECT * FROM `answer` INNER JOIN user as u on `FK_user` = u.user_id INNER JOIN piece as p on `FK_piece` = p.piece_id INNER JOIN `question` as q on `FK_question` = q.question_id inner join device_type as t on device_type_id = FK_device_type inner join `device_name` as n on  t.FK_device_name = n.device_name_id WHERE `piece_id` = "+p+" group by p.property_number");
    while (rs.next()) {
        name = rs.getString("n.name") + " " + rs.getString("model");
        serial = rs.getString("serial_number");
        prop = rs.getString("property_number");
        time = rs.getDate("doDate");
        section = "section";
        user = rs.getString("name") + " " + rs.getString("family");
        ch++;

    }
    if(ch == 0){
         response.sendRedirect("/Error/noIPM.xhtml");
    }
    

    rs = m1.result("SELECT * FROM `answer` left join `question` on `FK_question` = question_id WHERE `FK_piece` = "+p+" AND FK_question_type <> 5 AND FK_question_type <> 6");
    while (rs.next()) {
        t1 = rs.getInt("ratio");
        t2 = rs.getInt("answer");
        if (t1 == 4 && t2 == 0) {
            faild = true;
        }
        if (t1 == 5 && t2 == 0) {
            yellow = true;
            y++;
        }
        if (t1 == 5 && y > 1) {
            faild = true;
        }
        if (t1 == 4) {
            t1 = 3;
        }
        if (t1 == 5) {
            t1 = 3;
        }

        totalscor += (t1 * t2) * 10;
        total += t1;
        c++;

    }
    sum = (totalscor / total);

    //user = (String)s1.getAttribute("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>IPM Form</title>
        <link type="text/css" rel="stylesheet" href="/javax.faces.resource/css/sentinel-layout.css.xhtml?ln=sentinel-layout" />
        <style type="text/css">
            @import url("../Font/stylesheet.css");
            body {
                margin-left: 10px;
                margin-top: 10px;
                margin-right: 10px;
                margin-bottom: 10px;
                font-size:12px;
                font-family: yekan;
            }
            table {

                margin-bottom: 5px;
                font-size: 14px;
                color:#FFF;
                <%                    if (faild) {
                        out.print("background-color:#b30303;");
                        out.print("border: #910202 solid 1px;");
                    } else if (yellow) {
                        out.print("background-color:#fcdf00;");
                        out.print("border: #fccf0b solid 1px;");
                    } else {

                        if (sum < 4) {
                            out.print("background-color:#b30303;");
                            out.print("border: #910202 solid 1px;");
                        }
                        if (sum >= 4 && sum <= 7) {
                            out.print("background-color:#fcdf00;");
                            out.print("border: #fccf0b solid 1px;");
                        }
                        if (sum > 7) {
                            out.print("background-color:#096;");
                            out.print("border: #060 solid 1px;");
                        }
                    }

                %>
            }
            table td {
                padding:5px;

            }


            #sub{

                background-color:#FFF;
                margin-bottom:5px;
                border: 1px solid #dddddd;
                font-family: yekan;
                font-size:8px;
            }
            #sub td{
                padding:5px;

                font-size:8px;
            }
            #sub td,th{
                border-left:1px solid #dddddd;
                border-bottom:1px solid #dddddd;
            }

            #sub th{
                color:#333;

                background-color:#f5f5f5;


                font-size:12px;
            }
            #sub td:first-child{
                padding:5px;
                font-size:11px;
                text-align: right;
                border-right: 1px solid #dddddd;
            }

            #sub th:first-child{
                text-align:center;
                background-color:#F90;
                color:#FFF;
                border:0px;

            }
            #sub th:nth-child(2){
                border-right: 1px solid #dddddd; 
            }


            #sub tr:nth-child(even){
                background-color:#fafafa;
                color:#6c7673;
                text-align:center;
            }
            #sub tr:nth-child(odd){
                background-color:#ffffff;
                color:#6c7673;
                text-align:center;
            }
            #sub  tr{
                height:30px;
                font-size:13px;
            }


        </style>
    </head>

    <body class="fontIran">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" >
            <tr><%                if (faild) {
                    out.print("<td height='46' colspan='2' style='text-align:center;font-size:18px;background-color:#910202'><strong>فرم بازرسی و نگهداری پیشگیرانه</strong></td>");
                } else if (yellow) {
                    out.print("<td height='46' colspan='2' style='text-align:center;font-size:18px;background-color:#fccf0b'><strong>فرم بازرسی و نگهداری پیشگیرانه</strong></td>");
                } else {

                    if (sum < 4) {
                        out.print("<td height='46' colspan='2' style='text-align:center;font-size:18px;background-color:#910202'><strong>فرم بازرسی و نگهداری پیشگیرانه</strong></td>");
                    }
                    if (sum >= 4 && sum <= 7) {
                        out.print("<td height='46' colspan='2' style='text-align:center;font-size:18px;background-color:#fccf0b'><strong>فرم بازرسی و نگهداری پیشگیرانه</strong></td>");
                    }
                    if (sum > 7) {
                        out.print("<td height='46' colspan='2' style='text-align:center;font-size:18px;background-color:#038c5d'><strong>فرم بازرسی و نگهداری پیشگیرانه</strong></td>");
                    }
                }
                %>

            </tr>
            <tr>

                <td width="50%"><strong>نام تجهیز: <%= name%></strong></td>
                <td><strong> سریال: <%=serial%></strong></td>
            </tr>
            <tr>
                <td width="50%"><strong>نام بخش : <%= section%></strong></td>
                <td> <strong>نام شخص بازدید کننده: <%= user%></strong></td>
            </tr>
            <tr>
                <td><strong>شماره اموال: <%= prop%></strong></td>
                <td><strong>تاریخ انجام بازرسی: <%= Utilities.getCurrentShamsidate4(time)%></strong></td>
            </tr>

        </table>


        <%                counter = 0;
            rs = m1.result("SELECT * FROM `answer` inner join `question` on `FK_question` = question_id where `FK_piece` = "+p+" AND FK_question_type = 1 order by answer_id");
         
            while (rs.next()) {

                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub" class="fontIran">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>مشخصات ظاهری دستگاه</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                rs.beforeFirst();
                while (rs.next()) {
                    out.print("<tr>");
                    out.print("<td>" + rs.getString("text") + "</td>");

                    if (rs.getInt("answer") > 0) {
                        out.print("<td>X</td>");
                        out.print("<td>&nbsp;</td>");
                    } else {
                        out.print("<td>&nbsp;</td>");
                        out.print("<td>X</td>");
                    }

                    out.print("<td>" + rs.getString("done") + "</td>");
                    out.print("<td>" + rs.getString("required") + "</td>");

                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `answer` inner join `question` on `FK_question` = question_id where `FK_piece` = "+p+" AND FK_question_type = 2 order by answer_id");
           
            while (rs.next()) {

                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub" class="fontIran">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>دستور العمل های بازرسی کمی</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                rs.beforeFirst();
                while (rs.next()) {
                    out.print("<tr>");
                    out.print("<td>" + rs.getString("text") + "</td>");

                    if (rs.getInt("answer") > 0) {
                        out.print("<td>X</td>");
                        out.print("<td>&nbsp;</td>");
                    } else {
                        out.print("<td>&nbsp;</td>");
                        out.print("<td>X</td>");
                    }

                    out.print("<td>" + rs.getString("done") + "</td>");
                    out.print("<td>" + rs.getString("required") + "</td>");

                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `answer` inner join `question` on `FK_question` = question_id where `FK_piece` = "+p+" AND FK_question_type = 3 order by answer_id");
            
            while (rs.next()) {

                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub" class="fontIran">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>بازرسی در نگهداشت پیشگیرانه</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                rs.beforeFirst();
                while (rs.next()) {
                    out.print("<tr>");
                    out.print("<td>" + rs.getString("text") + "</td>");

                    if (rs.getInt("answer") > 0) {
                        out.print("<td>X</td>");
                        out.print("<td>&nbsp;</td>");
                    } else {
                        out.print("<td>&nbsp;</td>");
                        out.print("<td>X</td>");
                    }

                    out.print("<td>" + rs.getString("done") + "</td>");
                    out.print("<td>" + rs.getString("required") + "</td>");

                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `answer` inner join `question` on `FK_question` = question_id where `FK_piece` = "+p+" AND FK_question_type = 4 order by answer_id");
           
            while (rs.next()) {

                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub" class="fontIran">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>بازرسی کلید ها ، آلارم ها و باتری</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                rs.beforeFirst();
                while (rs.next()) {
                    out.print("<tr>");
                    out.print("<td>" + rs.getString("text") + "</td>");

                    if (rs.getInt("answer") > 0) {
                        out.print("<td>X</td>");
                        out.print("<td>&nbsp;</td>");
                    } else {
                        out.print("<td>&nbsp;</td>");
                        out.print("<td>X</td>");
                    }

                    out.print("<td>" + rs.getString("done") + "</td>");
                    out.print("<td>" + rs.getString("required") + "</td>");

                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        


    </body>
</html>
