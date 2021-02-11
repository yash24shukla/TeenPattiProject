package com.teenpatti.queendemo.model;

import java.io.Serializable;

/**
 * Created by anu patel on 16.1.18.
 */

public class Table implements Serializable {
    String id;
    String name,maxPlayers,slotUsed,boot,lastbet,lastblind,maxbet,potLimit,amount;
    String maximumblind,type,color_code,password;
    String tableType;

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaximumblind() {
        return maximumblind;
    }

    public void setMaximumblind(String maximumblind) {
        this.maximumblind = maximumblind;
    }

    public String getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(String maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getSlotUsed() {
        return slotUsed;
    }

    public void setSlotUsed(String slotUsed) {
        this.slotUsed = slotUsed;
    }

    public String getBoot() {
        return boot;
    }

    public void setBoot(String boot) {
        this.boot = boot;
    }

    public String getLastbet() {
        return lastbet;
    }

    public void setLastbet(String lastbet) {
        this.lastbet = lastbet;
    }

    public String getLastblind() {
        return lastblind;
    }

    public void setLastblind(String lastblind) {
        this.lastblind = lastblind;
    }

    public String getMaxbet() {
        return maxbet;
    }

    public void setMaxbet(String maxbet) {
        this.maxbet = maxbet;
    }

    public String getPotLimit() {
        return potLimit;
    }

    public void setPotLimit(String potLimit) {
        this.potLimit = potLimit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
