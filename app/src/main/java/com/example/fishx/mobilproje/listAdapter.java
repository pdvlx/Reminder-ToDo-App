package com.example.fishx.mobilproje;

public class listAdapter {
    String toDo;
    String yil,ay,gun,saat,dk,alarmsaat,alarmdk ,notif;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public listAdapter() {
    }

    public listAdapter(String toDo, String yil, String ay, String gun, String saat, String dk, String notif) {
        this.toDo = toDo;
        this.yil = yil;
        this.ay = ay;
        this.gun = gun;
        this.saat = saat;
        this.dk = dk;

        this.notif = notif;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getYil() {
        return yil;
    }

    public void setYil(String yil) {
        this.yil = yil;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getDk() {
        return dk;
    }

    public void setDk(String dk) {
        this.dk = dk;
    }

    public String getAlarmsaat() {
        return alarmsaat;
    }

    public void setAlarmsaat(String alarmsaat) {
        this.alarmsaat = alarmsaat;
    }

    public String getAlarmdk() {
        return alarmdk;
    }

    public void setAlarmdk(String alarmdk) {
        this.alarmdk = alarmdk;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }
}
