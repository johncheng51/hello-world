package com.jm.test;


import com.jm.mgr.FileMgr;
import com.jm.util.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public abstract class AbstractMain {
   
    protected FileMgr fileMgr=FileMgr.instance();
    public static final String JAVA ="java";
    public static final String PYTHON ="python";
    public static final String DOCKER ="docker";
    public static final String SCALA  ="scala";
    public static final String JAVASCRIPT  ="javascript";
    public static final String ANGULAR  ="angular";
    public static final String REACT    ="react";
    public static final String UIGEN ="UIGen>";
    public static final String SEARCH="Search>";
    public static final String QUIT="quit";
    public static final String HELP="help.txt";
    public static final String RUNSCRIPT="assistant.script";
    public static final String SEARCHHELP="searchHelp.txt";
    
    public static final String SET="set";
    public static final String CREATECLASS="createClass";
    public static final String CREATEMODEL="createModel";
    
    public static final String MAKECLASS="makeClass";
    public static final String MAKEMETHOD="makeMethod";
    public static final String SETATTR ="setattr";
    public static final String CREATEEXTEND ="createExtend";
    public static final String LINE   ="line";
    public static final String METHOD ="method";
    public static final String RUN ="run";
    
    public static final String SPACE =" ";
    
    public static final String MARK="##########################################";
    public static Main main=new Main();
    protected static void log(String message) {
       System.out.println(message);
    }
   
    protected boolean isBlank(String text){
        return Util.isBlank(text);
    }
    
    public static String readFile(String fileName) {
       
        File file = new File(fileName);
        String re = Util.readFile(file,true);
        return re;
    }

    public static void execute(String line) {
        boolean isBlank=Util.isBlank(line);
        if(isBlank)  return;
        ScriptFunc scriptFunc = ScriptFunc.getSF();
        scriptFunc.addVar("main", main);
        ProcessInput pi = new ProcessInput(Main.class);
        String text     =pi.tran(line);
        try {scriptFunc.eval(text);}
        catch(Exception e)
        {
            log(e.getMessage());
        }
    }
    

    public static String readLine(String message) {
        System.out.print(message);
        BufferedInputStream bis = new BufferedInputStream(System.in);
        DataInputStream dis = new DataInputStream(bis);
        String re = "";
        try {
            re = dis.readLine();
        } catch (IOException e) {
        }
        return re.trim();
    }
}

class ProcessInput {
    private Class handler;
    public static final String QQ="'";
    public static final String COMM=",";
    public static final String LEFTP="(";
    public static final String RIGHTP=")";
    public static final String SPACE=" ";
    public ProcessInput(Class handler) {
        this.handler = handler;
    }


    private String getName(String key) {
        key = key.toLowerCase();
        Method[] ma = handler.getMethods();
        for (Method m : ma) {
            String mName = m.getName().toLowerCase();
            if (mName.equalsIgnoreCase(key)) return m.getName();
            
        }
        return null;
    }


    public String tran(String line) {
        String[] sa = Util.split(line.trim(), SPACE);
        String funcName = getName(sa[0]);
        if (funcName == null) {
            log("Invalid function:" + sa[0]);
            sa[0] = "help";
        } else
            sa[0] = funcName;
        boolean isCode = sa[0].startsWith("code");
        boolean isExec = sa[0].equals("exec");
        String re = "main." + sa[0] + LEFTP;
        if (sa.length == 1)  return re + RIGHTP;
        if (isCode) return makeCode(re,sa);
        re += isExec ? QQ : "";
        for (int i = 1; i < sa.length; i++) {
            if (!isExec)
                re += QQ + wrap(sa[i]) + QQ+COMM;
            else
                re += SPACE + sa[i] + SPACE;
        }
        re = Util.removeLast(re, 1);
        re += isExec ? QQ : "";
        return re + RIGHTP;
    }
    
    public String searchTran(String line) {
        String[] sa = Util.split(line.trim(), SPACE);
        String funcName = getName(sa[0]);
        if (funcName == null) {
            log("Invalid function:" + sa[0]);
            funcName=sa[0]= "help";
         } else sa[0] = funcName;
        String result="main." + sa[0] + LEFTP;
        boolean setMode=isIn(funcName);
        String leftSide=(sa.length>=2)? result+QQ + sa[1] + QQ:"";
        switch(sa.length){
        case 1:result=result+RIGHTP;break;
        case 2:result=leftSide+RIGHTP;break;    
        default:String re=merge(sa,setMode? 1:2);
            result=setMode? result+re+RIGHTP:
                            leftSide+COMM+QQ + re + QQ+RIGHTP;
                break;
        }
        return result;   
     }
    
    private boolean isIn(String name){
        String[] table=new String[] {
        AbstractMain.MAKECLASS,
        AbstractMain.MAKEMETHOD,
        AbstractMain.RUN,
        AbstractMain.METHOD,
        AbstractMain.LINE,
        AbstractMain.CREATECLASS,
        AbstractMain.CREATEMODEL,
        AbstractMain.CREATEEXTEND,
        AbstractMain.SETATTR,
        "runMain",
        "route",
        "addGroup",
        "setList",
        "set",
        "setp",
        "doImport",
        "setparam",
        "data"};
        return Stream.of(table).filter(x->x.equalsIgnoreCase(name))
            .findFirst().isPresent();
    }
    
    private String merge(String[] array,int n){
        String result="";            
        for (int i = n; i < array.length; i++) 
        result += SPACE + array[i] + SPACE;
        return QQ+result+QQ;
    }
    
    private String makeCode(String main,String[] args){
        String result=main;
        result+=QQ+args[1]+QQ+COMM;
        String text=Stream.of(args).skip(2).collect(Collectors.joining(" "));
        result+=QQ+text+QQ+")";
        return result;
    }

    private String wrap(String text) {
        String re = "";
        char[] ca = text.toCharArray();
        for (char c : ca) {
            re += (c == '\\') ? "\\\\" : c + "";
        }
        return re;
    }

    private static void log(String s) {
        System.out.println(s);
    }
    
   


}

