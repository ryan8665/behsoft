/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controler;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ryan
 */
@ManagedBean
public class loginControler {

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    private String isLogin;

    public loginControler() {
    }

    public String isLogin(String a) {
        if (a == null) {
            return "www.yahoo.com";
        }else{
             return "www.google.com";
        }
    }

}
