package com.teenpatti.teenpattipremium.model;

/**
 * Created by anu patel on 16.1.18.
 */

public class User {

    String id,slot,active;
   CardSet cardset = new CardSet();
    PlayerInfo Playerinfo = new PlayerInfo();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public CardSet getCardset() {
        return cardset;
    }

    public void setCardset(CardSet cardset) {
        this.cardset = cardset;
    }

    public PlayerInfo getPlayerinfo() {
        return Playerinfo;
    }

    public void setPlayerinfo(PlayerInfo playerinfo) {
        Playerinfo = playerinfo;
    }
}
