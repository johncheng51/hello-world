package com.jm.model;

import com.jm.util.MSUtil;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class AbsModel implements Comparable {
    private static final String RTN = ",\r\n";
    private static final String COLN = ":";
    private static final String NULL = "'null'";
    private static final String QQ = "'";
    private static final String ABSMODEL="absmodel";
    private String[] keys;
   

    public AbsModel() {}
    public AbsModel(String keyFields) {
        keys = MSUtil.split(keyFields, ",");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)  return false;
        if (keys == null) return super.equals(o);
        boolean yes = true;
        for (String key : keys) {
            Object value1 = getProp(o,key);
            Object value2 = getProp(key);
            yes &= value1.equals(value2);
        }

        return yes;
    }

    @Override
    public int hashCode() {
        if (keys == null) return super.hashCode();
        int result = 0;
        for (String key : keys)
            result += getProp(key).hashCode() * 29;
        return result;
    }
    
    
    private String printColl(Stack<AbsModel> stack,Object value) {
        StringBuilder sb=new StringBuilder();
        Collection coll = (Collection) value;
            for (Object object : coll) 
                sb.append(printModel(stack,object));
        return sb+"";        
        
    }
    
    private String printModel(Stack<AbsModel> stack,Object value) {
        StringBuilder sb=new StringBuilder();
        boolean isModel=value instanceof AbsModel;
        if (!isModel) { sb.append(value+RTN); return sb+"";}
        AbsModel model=find(stack, value.getClass());
        if (model!=null) {sb.append(value.getClass()+RTN);return sb+"";}
        model=(AbsModel)value;
        stack.push(model);
        String result=model.print(stack);
        stack.pop();
        return result;
    }
    
    private AbsModel find(Stack<AbsModel> stack,Class myClass){
        Optional<AbsModel> option=  stack.stream().
               filter((x)->x.getClass().equals(myClass)).findFirst();
        if (option.isPresent()) return option.get();
        else return null;
    }

    private String getDate(Object object){
        Date date=(Date) object;
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        return sdf.format(date);
    }
    
    private List<Field> getFields(){
        ArrayList list=new ArrayList();
        Field[] fields1=getClass().getDeclaredFields();
        Field[] fields2=null;
        Class myClass=getClass().getSuperclass();
        boolean isABS=myClass.equals(AbsModel.class);
        if (!isABS) fields2=myClass.getDeclaredFields(); 
        for (Field f:fields1) list.add(f);   
        if (fields2==null) return list;
        for (Field f:fields2) list.add(f);  
        return list;
    }
    
    public List<String> getAllNames(){
       return  getFields().stream().map(x->x.getName())
        .collect(Collectors.toList());
    }

    public String print(Stack<AbsModel> stack) {
        List<Field> fields = getFields();
        StringBuilder sb=new StringBuilder();
        sb.append(getClass().getName() + "{\n");
        for (Field field : fields) {
            String name  = field.getName();
            Object value = getProp(name);
            if (value == null)  { sb.append(name + COLN + NULL + RTN); continue; }
            String type=getType(field);
            switch (type) 
            {
            case ABSMODEL:sb.append(name+COLN+printModel(stack,value));break;
            case "list":sb.append(name+COLN+printColl(stack,value));break;
            case "set": sb.append(name+COLN+printColl(stack,value));break; 
            case "date":sb.append(name + COLN + QQ + getDate(value) + QQ + RTN);break;
            default:sb.append(name + COLN + QQ + value + QQ + RTN);break;
            }
        }
        sb.append("}\n");
        return sb+"";
    }
    
    public String getType(Field field) {
        Class myClass=field.getType();
        myClass=((myClass.getSuperclass()+"").toLowerCase().indexOf(ABSMODEL)>0)? 
                myClass.getSuperclass():myClass;
           String type = myClass + "";
            type = MSUtil.getLast(type, ".").toLowerCase();
        return type;
    }
       
   
  
     public String printModel() {
        Stack<AbsModel> stack=new Stack();
        stack.push(this);
        String result=print(stack);
        stack.pop();
        return result;
    }

    public void log(String message) {
        System.out.println(message);
    }

    protected Object getProp(String field) {
        return MSUtil.getProp(this,field);
    }

    protected Object getProp(Object object,String field) {
        return MSUtil.getProp(object,field);
    }
    
    protected void setProp(String field, Object value) {
        MSUtil.setProp(this, field, value);
    }

    protected void setProp(Object object,String field, Object value) {
        MSUtil.setProp(object, field, value);
    }
    
    public int compareTo(Object object) {
        if (object == null) return 0;
        for (String key : keys) {
            int result = 0;
            Object value1 = getProp(object,key);
            Object value2 = getProp(key);
            if (value1 instanceof String)
                result = ((String) value1).compareToIgnoreCase(((String) value2));
            if (result != 0)
                return result;
        }
        return 0;
    }
    
    

   
    
     
}
