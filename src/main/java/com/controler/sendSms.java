/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controler;

import com.data.Model;
import java.sql.ResultSet;

public class sendSms {

    public static void send(String phone, String name) {
        try {

            com.kavenegar.sdk.KavenegarApi api = new com.kavenegar.sdk.KavenegarApi("756E6C616757626C38644136574558586236666F39773D3D");
            api.Send("10006707323323", phone, "" + name + "، خوش آمدید\n"
                    + "ورود به سامانه بهسافت\n"
                    + "در " + com.utility.Utilities.todayDay() + "");
        } catch (com.kavenegar.sdk.excepctions.ApiException ex) {
            // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
            System.out.print("Message 11111111111111: " + ex.toString());
        } catch (com.kavenegar.sdk.excepctions.HttpException ex) {
            // در زمانی که مشکلی در برقرای ارتباط با وب سرویس وجود داشته باشد این خطا رخ می دهد
            System.out.print("Message 1111111111111111: " + ex.toString());
        } catch (Exception ex) {
            System.out.print("Message 1111111111111111: " + ex.toString());
        }
    }

    public static void sendnew(String phone, String name, String user, String pass) {
        try {

            com.kavenegar.sdk.KavenegarApi api = new com.kavenegar.sdk.KavenegarApi("756E6C616757626C38644136574558586236666F39773D3D");
            api.Send("10006707323323", phone, "" + name + "، به سامانه بهسافت خوش آمدید\n"
                    + "مشخصات ورود شما عبارت است از\n"
                    + "نام کاربری: " + user + "\n"
                    + "گذرواژه: " + pass + "");
        } catch (com.kavenegar.sdk.excepctions.ApiException ex) {
            // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
            System.out.print("Message 11111111111111: " + ex.toString());
        } catch (com.kavenegar.sdk.excepctions.HttpException ex) {
            // در زمانی که مشکلی در برقرای ارتباط با وب سرویس وجود داشته باشد این خطا رخ می دهد
            System.out.print("Message 1111111111111111: " + ex.toString());
        } catch (Exception ex) {
            System.out.print("Message 1111111111111111: " + ex.toString());
        }
    }

    public static void sendPass(String phone, String name, String user, String pass) {
        try {

            com.kavenegar.sdk.KavenegarApi api = new com.kavenegar.sdk.KavenegarApi("756E6C616757626C38644136574558586236666F39773D3D");
            api.Send("10006707323323", phone, "سامانه بهسافت\n"
                    + "مشخصات ورود شما عبارت است از\n"
                    + "نام کاربری: " + user + "\n"
                    + "گذرواژه: " + pass + "");
        } catch (com.kavenegar.sdk.excepctions.ApiException ex) {
            // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
            System.out.print("Message 11111111111111: " + ex.toString());
        } catch (com.kavenegar.sdk.excepctions.HttpException ex) {
            // در زمانی که مشکلی در برقرای ارتباط با وب سرویس وجود داشته باشد این خطا رخ می دهد
            System.out.print("Message 1111111111111111: " + ex.toString());
        } catch (Exception ex) {
            System.out.print("Message 1111111111111111: " + ex.toString());
        }
    }

    public static void sendMessage(String rid, String id) {
         String name = null, family, tel="", full_name = "";
        try {
            try {
                Model om = new Model();
               
                ResultSet rs = om.result("SELECT `name`,`family`,`tel` FROM `user` WHERE `user_id` = "+id+";");
                while (rs.next()) {
                    name = rs.getString("name");
                    family = rs.getString("family");
                    // tel = rs.getString("tel");
                    full_name = name + " " + family;
                }
                tel = om.select("SELECT `tel` FROM `user` WHERE `user_id` = " + rid);
            } catch (Exception ei) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ei.toString());
            }

            com.kavenegar.sdk.KavenegarApi api = new com.kavenegar.sdk.KavenegarApi("756E6C616757626C38644136574558586236666F39773D3D");
            api.Send("10006707323323", tel, "سامانه بهسافت\nشما یک پیام خواند نشده از طرف "+full_name+" دارید");
        } catch (com.kavenegar.sdk.excepctions.ApiException ex) {
            // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
            System.out.print("Message 11111111111111: " + ex.toString());
        } catch (com.kavenegar.sdk.excepctions.HttpException ex) {
            // در زمانی که مشکلی در برقرای ارتباط با وب سرویس وجود داشته باشد این خطا رخ می دهد
            System.out.print("Message 1111111111111111: " + ex.toString());
        } catch (Exception ex) {
            System.out.print("Message 1111111111111111: " + ex.toString());
        }
    }
}
