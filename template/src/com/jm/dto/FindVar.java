package com.jm.dto;

import java.util.ArrayList;
import java.util.List;

public class FindVar extends AbsModel{
    private String text;
    private List<String> list=new ArrayList();
    private String result;
    public FindVar(String text) {
       this.text=text;
       process();
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    private void process() {
        result=text;
        while(true){
            int n=result.indexOf(MARK);
            if (n<0) break;
            int len=MARK.length();
            int n1=result.substring(n+len).indexOf(MARK);
            if (n1<0) break;
            n1+=n+len;
            String key=result.substring(n+len,n1);
            list.add(key);
            result=result.substring(0,n)+key+result.substring(n1+len);
        }
    }
    
    
    public static FindVar create(String text){
        return new FindVar(text);
    }
    
    public static void main(String[] args){
        FindVar var=new FindVar("1111111###abc###111111###cde###22333");
        System.out.println(var.getList());
    }
}
