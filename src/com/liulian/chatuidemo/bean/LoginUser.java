package com.liulian.chatuidemo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class LoginUser extends Location  {
    String falg;
    String msg;
    User user;
    String is_new;

    public LoginUser(boolean result, String msg) {
        this.setResult(result);
        this.setMsg(msg);
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "falg='" + falg + '\'' +
                ", msg='" + msg + '\'' +
                ", user=" + user +
                ", is_new='" + is_new + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginUser)) return false;

        LoginUser loginUser = (LoginUser) o;

        if (getFalg() != null ? !getFalg().equals(loginUser.getFalg()) : loginUser.getFalg() != null)
            return false;
        if (getMsg() != null ? !getMsg().equals(loginUser.getMsg()) : loginUser.getMsg() != null)
            return false;
        if (getUser() != null ? !getUser().equals(loginUser.getUser()) : loginUser.getUser() != null)
            return false;
        return getIs_new() != null ? getIs_new().equals(loginUser.getIs_new()) : loginUser.getIs_new() == null;

    }

    @Override
    public int hashCode() {
        int result = getFalg() != null ? getFalg().hashCode() : 0;
        result = 31 * result + (getMsg() != null ? getMsg().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getIs_new() != null ? getIs_new().hashCode() : 0);
        return result;
    }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }
}
