package com.jm.lcproc;

import com.jm.dto.LineCodeDTO;
import com.jm.dto.PatternDTO;
import com.jm.mgr.FreeMarker;
import com.jm.mgr.LineCodeMgr;
import com.jm.process.ProcessEntity;
import com.jm.util.Util;
import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class AbsLCProc {
    protected LineCodeMgr lineCodeMgr=LineCodeMgr.instance();
    protected FreeMarker  freeMarker =FreeMarker.instance();
    public static final String MARK="###";
    public static final String SMARK="#####";
    public static final String RMARK="##R##";
    public static final String FILE="file";
    public static final String PAIR="pair";
    
    public static final String BLANK="";
    public static final String NEXT=":NEXT";
    public static final String NAME ="n";
    public static final String NAME1="n1";
    public static final String MARK1="mark";
    public static final String    RT="\r\n";
    public static final String SPACE5="     ";
    public static final int  INDENT=6;
    public static final String JAVA ="java";
    public static final String MODEL="model";
    public static final String BLOCK="BLOCK";
    public static final String METHOD="METHOD";
    public static final String PARAM="p";
    public static final String PACKAGE="package";
    public static final String USECALSS="useClass";
    public static final String HTML="html";
    
    public static final String INTERFACE="interface";
    protected File   currentFolder;     
    protected File   currentFile;
    protected String lineCodeType;
    protected LineCodeDTO lineCodeDTO=new LineCodeDTO();
    protected boolean block;
    protected String extra="";
    
    
    public String getAttr(String name,String key){
        return lineCodeMgr.getBlockProp(name,key);
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setLineCodeDTO(LineCodeDTO lineCodeDTO) {
        this.lineCodeDTO = lineCodeDTO;
    }

    public LineCodeDTO getLineCodeDTO() {
        return lineCodeDTO;
    }

    public void setLineCodeType(String lineCodeType) {
        this.lineCodeType = lineCodeType;
    }

    public String getLineCodeType() {
        return lineCodeType;
    }

    public void setLineCodeMgr(LineCodeMgr lineCodeMgr) {
        this.lineCodeMgr = lineCodeMgr;
    }

    public LineCodeMgr getLineCodeMgr() {
        return lineCodeMgr;
    }

    public void setFreeMarker(FreeMarker freeMarker) {
        this.freeMarker = freeMarker;
    }

    public FreeMarker getFreeMarker() {
        return freeMarker;
    }


    public void setFolder(File folder) {
        this.currentFolder = folder;
    }

    public File getFolder() {
        return currentFolder;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public boolean load(String name){
        boolean ok=lineCodeMgr.load(name);
        this.lineCodeType=name;
        return ok;
    }
    
    public void printLine(String pattern){
        block=false;
        print(pattern);
    }
    
    public String printBlock(String pattern){
        block=true;
        return printb(pattern);
    }
    
    public String printSPAModel(String pattern){
       String pack=getAttr(pattern, PACKAGE);
       int size=lineCodeDTO.getList().size();
       boolean isInterface=pattern.startsWith(INTERFACE);
       if (size!=0) 
       {
           if (isInterface){
               String line=lineCodeMgr.getLineCode(INTERFACE+"0");
               String result=makeFromList(line);
               Util.setProperty(lineCodeDTO, PARAM+"0", result);
           }
           else 
           for(int i=0;i<3;i++){
           String key=pack+i;
           String line=lineCodeMgr.getLineCode(key);
           String assign=PARAM+i;
           String result=line.equals(PARAM)? lineCodeDTO.getP():makeFromList(line);
           Util.setProperty(lineCodeDTO, assign, result);
       }}
       writeComment();
       return writeSPA(pattern,pack);
     }
    
    private String writeSPA(String pattern,String pack){
        String text=lineCodeMgr.getBlockCode(pattern);
        String result=freeMarker.tranWithText(text, lineCodeDTO.getMap());
        String existPack=lineCodeDTO.getPack();
        String subFolder= isBlank(pack)? existPack:pack;
        boolean isConst=pattern.equals("constant");
        String lName=lineCodeDTO.getLc();
        boolean isHTML=!isBlank(getAttr(pattern, HTML));
        if (isConst) lName=lName.toLowerCase();
        String workFile=(isReact()? lineCodeDTO.getCcc():lName)+
                        (isHTML? getHExt():getExt());
        File currentFile=new File(currentFolder,subFolder+"\\"+workFile);
        log("data:"+result);
        writeFile(currentFile,result);
        return subFolder+"\\"+workFile;
    }
    
    private String makeFromList(String line){
        List<String> list=lineCodeDTO.getList();
        String result="";
        int size=list.size();
        for (int i=0;i<size;i++)
        {
            lineCodeDTO.setN1(list.get(i));
            result+=freeMarker.tranWithText(line, lineCodeDTO.getMap());
            if (i!=(size-1)) result+=RT+SPACE5;
        }    
        return result;
    }
    
    
    
    public void  createModel(String folder,String pattern){
        String[] words=Util.split(pattern," ");
        int n=folder.indexOf(JAVA);
        String newFolder=folder.substring(0,n+JAVA.length());
        lineCodeDTO.setF(newFolder);
        lineCodeDTO.setP(words[0]);
        lineCodeDTO.setP0(lineCodeDTO.getLc()+":"+words[1]);
        String text=lineCodeMgr.getBlockCode(MODEL);
        String result=freeMarker.tranWithText(text, lineCodeDTO.getMap());
        new ProcessEntity(result,true);
    }
    
    private String printb(String name) {
        String text=lineCodeMgr.getBlockCode(name);
        if (text==null) {
            log(String.format("Can not find line with [%s]",name));
            return  null;
        }
        lineCodeDTO.setN(cap(name));
        boolean isPair=!isBlank(lineCodeMgr.getBlockProp(name,PAIR));
        writeComment();
        String result=freeMarker.tranWithText(text, lineCodeDTO.getMap());
        PatternDTO patternDTO=new PatternDTO(result);
        if (patternDTO.isOk())
        { String newData=isPair? 
                        makeListPair(patternDTO.getKey(),patternDTO.getRender()):
                        makeList(patternDTO.getKey(),patternDTO.getRender());
          result=result.replace(patternDTO.getPattern(), newData);
          patternDTO=new PatternDTO(result);
          if (patternDTO.isOk()){
          newData=makeListA(patternDTO.getKey(),patternDTO.getRender());
          result=result.replace(patternDTO.getPattern(), newData);}
        }
        
        boolean isFile  =!isBlank(lineCodeMgr.getBlockProp(name,FILE));
        String pack     =lineCodeMgr.getBlockProp(name,PACKAGE);
        String useClass =lineCodeMgr.getBlockProp(name,USECALSS);
        boolean havePack=!isBlank(pack);
        boolean usec=!isBlank(useClass);
        String newName=usec? cap(lineCodeDTO.getC()):cap(name);
        
        if (isFile) {
            String workFile=havePack?  newName+cap(pack)+getExt():
                            lineCodeDTO.getC()+cap(name)+getExt();
            if (!getExt().equals("."+JAVA)) workFile=lcap(workFile);
            String subFolder= havePack? pack:lcap(name);
            File currentFile=new File(currentFolder,subFolder+"\\"+workFile);
            log("data:"+result);
            writeFile(currentFile,result);
            return subFolder+"\\"+workFile;
        }
        else {
            updateFile(result);
            return null;
        }
    }
    
    
    private void writeComment(){
        lineCodeDTO.setMc(this.getMarkFC(false,BLANK));
        lineCodeDTO.setMc1(this.getMarkFC(false,NEXT));
        lineCodeDTO.setBc(this.getMarkFC(true,BLANK));
        lineCodeDTO.setHc(this.getHMark());
    }
    
    
    private String makeList(String key,String render){
        String result="";
        String pattern=lineCodeMgr.getLineCode(key);
        Map map=new Hashtable();
        for (String word:lineCodeDTO.getList()){
            map.put(NAME,word);
            String line=freeMarker.tranWithText(pattern, map);
            line=render.replace(key, line);
            result+=line+RT;
        }
        return result;
    }
    
    private String makeListA(String key,String render){
        String result="";
        String pattern=lineCodeMgr.getLineCode(key);
        Map map=new Hashtable();
        for (String word:lineCodeDTO.getListAssign()){
            map.put(NAME,word);
            String line=freeMarker.tranWithText(pattern, map);
            line=render.replace(key, line);
            result+=line+RT;
        }
        return result;
    }
    
    private String makeListPair(String key,String render){
        String result="";
        String pattern=lineCodeMgr.getLineCode(key);
        Map map=new Hashtable();
        List<String> list=lineCodeDTO.getListPair();
        for (int i=0;i<list.size();i+=2){
            setExtra((i/2)+"");
            setBlock(false);
            String newMark=RT+insert(list.get(i))+RT+getMark();
            setBlock(true);
            setExtra("");
            map.put(NAME,list.get(i));
            map.put(NAME1,list.get(i+1));
            map.put(MARK1,newMark);
            String line=freeMarker.tranWithText(pattern, map);
            line=render.replace(key, line);
            result+=line+RT;
        }
        return result;
    }
    
    private String insert(String var){
        Map map=new Hashtable();
        String pattern=lineCodeMgr.getLineCode("print");
        map.put(NAME,var);
        String line=freeMarker.tranWithText(pattern, map);
        return sp(INDENT)+line;
    }
    
    private void updateFile(String text){
        String content=readFile(currentFile);
        String marker=getMark();
        log("marker:"+marker);
        content=content.replace(marker, text+"\n"+getMark());
        log("data::"+content);
        writeFile(currentFile,content);
        log(text);
    }
    
    private void updateFile(File file,String text,boolean isHtml){
        setBlock(true);
        String content=readFile(file);
        String marker=isHtml?getHMark():getMark();
        log("marker:"+marker);
        content=content.replace(marker, text+"\n"+marker);
        log("data::"+content);
        writeFile(file,content);
        log(text);
    }
    
    private void print(String pattern){
        String text=lineCodeMgr.getLineCode(pattern);
        if (text==null) {
            log(String.format("Can not find line with [%s]",pattern));
            return;
        }
        String result=freeMarker.tranWithText(text, lineCodeDTO.getMap());
        result=swap(result);
        result=addLineFeed(result);
        updateFile(result);
    }
    
    private String addLineFeed(String text){
        String result=text;
        while(true){
            int n=result.indexOf(MARK);
            if (n<0) return result;
            result=result.replace(MARK, RT);
        }
    }
    
    private String swap(String text){
        int n=text.indexOf(SMARK);
        if (n<0) return text;
        text=text.replace(SMARK, sp(INDENT));
        text=text.replace(RMARK,RT+sp(INDENT+1));
        return text;
    }
    
    private String sp(int n){
        String result="";
        for (int i=0;i<n;i++) result+=" ";
        return result;
    }
    
    
    public boolean isBlock() {return block;}
    
    public abstract String getMark();
    public abstract String getMarkFC(boolean isBlock,String ext);
    public abstract String getExt();
    public abstract boolean isSPA();
    public abstract boolean isReact();
    public abstract String  getHMark();
    public abstract String  getHExt();
    
    public  String readFile(File file){
        return Util.readFile(file, false);
    }
    
    public  void writeFile(File file,String content){
         Util.writeFile(file, content);
    }
    
    protected  void log(String message) {System.out.println(message);}
    public String getExtra() {return extra; }
    private boolean isBlank(String text) {return Util.isBlank(text);}
    private String cap(String text){return Util.cap(text);}
    private String lcap(String text){return Util.lcap(text); }


    public void saveGroup() {
        String text=currentFile+"";
        boolean isHtml=text.endsWith(".html");
        if (isHtml) 
        {
           updateFile(currentFile,lineCodeDTO.getGroup(),true);
           text=text.replace("html","ts");
           updateFile(new File(text),lineCodeDTO.getCgroup(),false);
        }
        else {
            updateFile(currentFile,lineCodeDTO.getCgroup(),false);
            text=text.replace("ts","html");
            updateFile(new File(text),lineCodeDTO.getGroup(),true);
        }
    }
}
