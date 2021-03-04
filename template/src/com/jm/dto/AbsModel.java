package com.jm.dto;

import com.jm.util.Util;
import com.jm.xml.ReadProp;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class AbsModel {
    public  static String QQ="\"";
    public  static String SPACE=" ";
    private String prjName     ="XXXXXXX";
    private String prjFolder   ="XXXXXXX";
    private String action;
    private String className;
    public  static String APPLINE="app/linecode";
    public  static String TXT    =".txt";
    public  static String MAIN   ="main";
    public  static String BLOCK  ="block";
    public static final String RT="\r\n";
    public static final String PARAM="p";
    public static final String MARK="###";
    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    protected void loadProp(String text) {
        Map<String,String> map=ReadProp.getT(text);
        List<Field> fields=getAllFields();
        for (Field f:fields){
            String name=f.getName();
            String value=map.get(name);
            setProp(name,value);
        }
    }
    
    private List<Field> getAllFields(){
        Field[] fielda=getClass().getDeclaredFields();
        Field[] fieldb=getClass().getSuperclass().getDeclaredFields();
        ArrayList list=new ArrayList();
        for (Field f:fielda) list.add(f);
        for (Field f:fieldb) list.add(f);
        return list;
    }

    public void setProp(String name, String value) {
        Util.setProperty(this, name, value);
    }
    
    public String getProp(String name) {
        return  Util.getProperty(this, name)+"";
    }
    
    public Map<String,String> getMap(){
        Map map=new Hashtable();
        Field[] fa=getClass().getDeclaredFields();
        for (Field f:fa){
            String name=f.getName();
             map.put(name,getProp(name));
        }
        return map;
    }
    
    public String diff(String text) {
       int n= text.indexOf(className);
       String result=text.substring(n+className.length()+1);
              result=prjName+"/"+result;
       return result;
    }
        
    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getPrjName() {
        return prjName;
    }
    
    public void setPrjFolder(String prjFolder) {
        this.prjFolder = prjFolder;
    }

    public String getPrjFolder() {
        return prjFolder;
    }
    
    public String getFullFolder(){
        return prjFolder+"/"+prjName;
    }
    
    public  void load(String text){
        loadProp(text);
    }
    
    public void log(String message){
        System.out.println(message);
    }
    
    public String[] split(String text){
        return Util.split(text);
    }
    
    public String  removeLast(String text){
        return Util.removeLast(text);
    }
}
