package com.jm.dto;

public class PatternDTO extends AbsModel{
    
    private boolean ok=true;
    public PatternDTO(String text){
        ok=process(text);
    }

    public boolean isOk() {
        return ok;
    }

    private boolean process(String text){
        int n=text.indexOf(MARK);
        if (n<0) return false;
        int len=MARK.length();
        int n1=text.substring(n+len).indexOf(MARK);
        if (n1<0) return false;
        this.pattern=text.substring(n).substring(0,n1+len*2);
        this.key=pattern.replaceAll(MARK, "").trim();
        this.render=pattern.replaceAll(MARK,"   ");
        return true;
    }
    private String key;
    private String render;
    private String pattern;


    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getRender() {
        return render;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }


}
