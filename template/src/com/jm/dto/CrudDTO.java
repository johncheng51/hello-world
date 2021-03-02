package com.jm.dto;


public class CrudDTO extends AbsModel{
   String lcomp;
   String ucomp;
   String comp;
   String extra="";


    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }


    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getComp() {
        return comp;
    }

    public void setLcomp(String lcomp) {
        this.lcomp = lcomp;
    }

    public String getLcomp() {
        return lcomp;
    }

    public void setUcomp(String ucomp) {
        this.ucomp = ucomp;
    }

    public String getUcomp() {
        return ucomp;
    }
}
