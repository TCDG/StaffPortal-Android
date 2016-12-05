package com.xelitexirish.staffportal_android.api;

/**
 * Created by XeliteXirish on 05/12/2016 (www.xelitexirish.com)
 */

public class PunishmentObject {

    private int ID;
    private String date;
    private String offender;
    private String admin;
    private String reason;
    private String info;
    private String proof;
    private String expire;

    public PunishmentObject(int id, String date, String offender, String admin, String reason, String info, String proof, String expire){
        this.ID = id;
        this.date = date;
        this.offender = offender;
        this.admin = admin;
        this.reason = reason;
        this.info = info;
        this.proof = proof;
        this.expire = expire;
    }

    public int getID(){
        return this.ID;
    }

    public String getDate(){
        return this.date;
    }

    public String getOffender(){
        return this.offender;
    }

    public String getAdmin(){
        return this.admin;
    }

    public String getReason(){
        return this.reason;
    }

    public String getInfo(){
        return this.info;
    }

    public String getProof(){
        return this.proof;
    }

    public String getExpire(){
        return this.expire;
    }
}
