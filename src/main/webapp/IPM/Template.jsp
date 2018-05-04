<%-- 
    Document   : Template
    Created on : Feb 12, 2016, 8:11:33 PM
    Author     : Ryan
--%>

<%@page import="com.data.Model"%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<%    int counter = 0;
    Model m1 = new Model();
    ResultSet rs;
    String name = "";
    rs = m1.result("SELECT `name` FROM `device_name` WHERE 1 = 1");
    while (rs.next()) {
        name = rs.getString("name");
    }

    String nameID = request.getParameter("nameid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>IPM Form</title>

        <link type="text/css" rel="stylesheet" href="/javax.faces.resource/css/sentinel-layout.css.xhtml?ln=sentinel-layout" />
       
        
        <style type="text/css">

            body {
                margin-left: 10px;
                margin-top: 10px;
                margin-right: 10px;
                margin-bottom: 10px;
                font-size:12px;

            }
            table {

                border: #060 solid 1px;
                background-color: #096;
                margin-bottom: 5px;
                font-size: 14px;
                color:#FFF;
            }
            table td {
                padding:5px;
            }
            #sub{

                background-color:#FFF;
                margin-bottom:5px;
                border: 1px solid #dddddd;

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
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="white" >
            <tr>
                <td height="46" colspan="2" style="text-align:center;font-size:18px;background-color:#038c5d"><strong class="white Fs17">فرم بازرسی و نگهداری پیشگیرانه</strong></td>
            </tr>
            <tr class="white Fs11">

                <td width="50%" class="white" ><strong>نام تجهیز: <%= name%></strong></td>
                <td class="white"><strong>مارک و مدل: </strong></td>
            </tr>
            <tr class="white Fs11">
                <td width="50%" class="white"><strong>نام بخش :</strong></td>
                <td class="white"> <strong>نام شخص بازدید کننده:</strong></td>
            </tr>
            <tr class="white Fs11">
                <td class="white"><strong>شماره اموال:</strong></td>
                <td class="white"><strong>تاریخ انجام بازرسی:</strong></td>
            </tr>

        </table>

        <%                counter = 0;
            rs = m1.result("SELECT * FROM `question` WHERE `FK_question_type` = 1 AND `FK_device_name` = "+nameID);
            String[] temp = new String[100];
            while (rs.next()) {
                temp[counter] = rs.getString("text");
                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub" >
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>مشخصات ظاهری دستگاه</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                for (int i = 0; i < counter; i++) {
                    out.print("<tr>");
                    out.print("<td>" + temp[i] + "</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `question` WHERE `FK_question_type` = 2 AND `FK_device_name` = "+nameID);

            while (rs.next()) {
                temp[counter] = rs.getString("text");
                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>دستور العمل های بازرسی کمی</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                for (int i = 0; i < counter; i++) {
                    out.print("<tr>");
                    out.print("<td>" + temp[i] + "</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `question` WHERE `FK_question_type` = 3 AND `FK_device_name` = "+nameID);

            while (rs.next()) {
                temp[counter] = rs.getString("text");
                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>بازرسی در نگهدشت پیشگیرانه</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                for (int i = 0; i < counter; i++) {
                    out.print("<tr>");
                    out.print("<td>" + temp[i] + "</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `question` WHERE `FK_question_type` = 4 AND `FK_device_name` = "+nameID);

            while (rs.next()) {
                temp[counter] = rs.getString("text");
                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>بازرسی کلید ها ، آلارم ها و باتری</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                for (int i = 0; i < counter; i++) {
                    out.print("<tr>");
                    out.print("<td>" + temp[i] + "</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `question` WHERE `FK_question_type` = 5 AND `FK_device_name` = "+nameID);

            while (rs.next()) {
                temp[counter] = rs.getString("text");
                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>کاربری دستگاه</strong></th>
                <th width="42%">سوال</th>
                <th width="5%">بله</th>
                <th width="5%">خیر</th>
                <th width="17%">اقدامات اصلاحی مورد نیاز</th>
                <th width="17%">اقدامات اصلاحی انجام شده</th>
            </tr>
            <%
                for (int i = 0; i < counter; i++) {
                    out.print("<tr>");
                    out.print("<td>" + temp[i] + "</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("<td>&nbsp;</td>");
                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
        <%                counter = 0;
            rs = m1.result("SELECT * FROM `question` WHERE `FK_question_type` = 6 AND `FK_device_name` = "+nameID);

            while (rs.next()) {
                temp[counter] = rs.getString("text");
                counter++;
            }
            if (counter > 0) {
        %>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="sub">
            <tr>
                <th width="14%" rowspan="<% out.print(counter + 1); %>"><strong>اقدامات پیشگیرانه</strong></th>
                <th width="42%">اقدامات</th>
                <th width="44%">شرح</th>

            </tr>
            <%
                for (int i = 0; i < counter; i++) {
                    out.print("<tr>");
                    out.print("<td>" + temp[i] + "</td>");
                    out.print("<td>&nbsp;</td>");

                    out.print("</tr>");
                }
            %>

        </table>
        <%}%>
    </body>
</html>
