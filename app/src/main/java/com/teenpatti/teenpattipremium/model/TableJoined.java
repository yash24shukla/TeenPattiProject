package com.teenpatti.teenpattipremium.model;

import java.util.ArrayList;

/**
 * Created by anu patel on 16.1.18.
 */

public class TableJoined {
    String id;
    String tableId;
    String slot,active;

    String C_userId,C_tableId,C_userName,C_displayName,C_chips;

    ArrayList<User> arrotheruser = new ArrayList<>();

    public ArrayList<User> getArrotheruser() {
        return arrotheruser;
    }

    public void setArrotheruser(ArrayList<User> arrotheruser) {
        this.arrotheruser = arrotheruser;
    }

    public String getC_userId() {
        return C_userId;
    }

    public void setC_userId(String c_userId) {
        C_userId = c_userId;
    }

    public String getC_tableId() {
        return C_tableId;
    }

    public void setC_tableId(String c_tableId) {
        C_tableId = c_tableId;
    }

    public String getC_userName() {
        return C_userName;
    }

    public void setC_userName(String c_userName) {
        C_userName = c_userName;
    }

    public String getC_displayName() {
        return C_displayName;
    }

    public void setC_displayName(String c_displayName) {
        C_displayName = c_displayName;
    }

    public String getC_chips() {
        return C_chips;
    }

    public void setC_chips(String c_chips) {
        C_chips = c_chips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
