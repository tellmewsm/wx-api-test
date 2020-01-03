package com.wx.entity;

public class TestCase {

    private String type;

    private String url;

    private String params;

    private String header;

    private String check;

    private String correlation;

    private boolean run;

    private String result;

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCorrelation() {
        return correlation;
    }

    public void setCorrelation(String correlation) {
        this.correlation = correlation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", params='" + params + '\'' +
                ", header='" + header + '\'' +
                ", check='" + check + '\'' +
                ", correlation='" + correlation + '\'' +
                ", run=" + run +
                ", result='" + result + '\'' +
                '}';
    }
}
