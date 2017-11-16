package com.yoosal.zqmh.pojo;

public class SystemWithBLOBs extends System {
    private String companySummary;

    private String introduce;

    private String staticCode;

    private String footer;

    public String getCompanySummary() {
        return companySummary;
    }

    public void setCompanySummary(String companySummary) {
        this.companySummary = companySummary == null ? null : companySummary.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getStaticCode() {
        return staticCode;
    }

    public void setStaticCode(String staticcode) {
        this.staticCode = staticcode == null ? null : staticcode.trim();
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}