package com.jm.gen;

import com.jm.mgr.*;
import com.jm.model.*;
import com.jm.util.Util;
import java.io.File;
import java.util.Map;

public abstract class AbstractGen {
    public static final String SRC           = "/src";
    public static final String JAVA          = ".java";
    public static final String JS            = ".js";
    public static final String APPJS         = "app.js";
    public static final String IMPORT        = "import";
    public static final String TEMPLATE      = ".templ";
    public static final String HTML          = ".html";
    public static final String HTMLTEMPLATE  = ".html.templ";
    public  static final String ANGHTML      =".component.html";
    public  static final String ANGHTMLTEMP  =".component.html.templ";
    public  static final String ANGCLASSTEMP =".component.ts.templ";
    public  static final String REACT        =".js.templ";
    public  static final String ANGCLASS     =".component.ts";
    public  static final String ANGCSS       =".component.css";
    public  static final String ANGMODULE    =".module.ts";
    public  static final String MODULE       ="module";
    public  static final String BEANTXT      ="bean";
    public  static final String METHODMARK   ="//###method###";
    public static final String CLASS         = "class";
    public static final String PACKAGE       = "package";
    public  static final String UNDER        ="/";
    public  static final String RTN          ="\r\n";
    public  static final String DOT          ="\\.";
    public  static final String NONE         ="none";
    public  static final String INPUT        ="input";
    public  static final String MAIN         ="main";
    public  static final String SOURCE       ="template";
    public  static final String ACTION       ="action";
    public  static final String MACRO        ="@@";
    public  static final String DOLLAR       ="$";
    public  static final String MARKER       ="#####";
    public  static final String FILE         ="file";
    public  static final String HTMLTYPE     ="html";
    public  static final String TYPE         ="type";
    public  static final String EXTRA        ="extra";
  
    protected String beanName;  //Bean class name such Java Class
    //Template file name such as JSP,XHTML,JSX
    protected String templName;
    protected FileMgr fileMgr=FileMgr.instance();
    protected FreeMarker freeMarker=FreeMarker.instance();
    protected ThreadMgr  threadMgr =ThreadMgr.instance();
    protected SwapMgr swapMgr=SwapMgr.instance();
    protected ModelMgr modelMgr=ModelMgr.instance();
    protected File workFolder;
    protected String pack = null;
    protected File classFolder = null;
    public static final String XML = ".xml";
    public static final String UI  = "ui";
    protected String className;
    protected boolean skipModel=false;
    protected String assignFolder="";
    public AbstractGen() {}
    public AbstractGen(String pack) {this.pack = pack;}

    public void setAssignFolder(String assignFolder) {
        this.assignFolder = assignFolder;
    }

    public String getAssignFolder() {
        return assignFolder;
    }

    public AbstractGen(String folder, String pack) {
        this(pack);
        init(folder, pack,null);
    }

    public AbstractGen(String folder, String pack, String src) {
        this(pack);
        init(folder,pack,src);
    }
    
    public void setPackage(String beanName,String altBeanName) {
        this.beanName=beanName;
        threadMgr.setBeanName(beanName);
        RenderModel rmModel=ThreadMgr.instance().getRenderModel();
        init(rmModel.getMainFolder(), rmModel.getPack(),rmModel.getFolder());
        className=Util.cap(beanName);
        altBeanName=isBlank(altBeanName)? NONE:altBeanName;
        if (altBeanName.equals(NONE)) skipModel=true;
    }
    
    private boolean isBlank(String text){
        return Util.isBlank(text);
    }

    private void init(String folder, String pack,String subFolder) {
        this.pack = pack;
        workFolder = getFolder(folder);
        subFolder=isBlank(subFolder)? "":subFolder;
        String source = subFolder == null ? folder  : folder + "/"+subFolder ;
        File file = getFolder(source);
        classFolder = getClassFolder(file, pack);
        
    }
    
    protected File setWorkFolder(String source){
        RenderModel rmModel=ThreadMgr.instance().getRenderModel();
        String folder=isBlank(assignFolder)? rmModel.getMainFolder():assignFolder;
        workFolder = new File(folder,source);
        threadMgr.setWorkFolder(workFolder);
        return workFolder;
    }
    
    public void  setWorkFolder(File source){
        workFolder=source;
    }
    
    
    private File getClassFolder(File source, String pack) {
        String[] words = Util.split(pack, ".");
        File file = source;
        for (int i = 0; i < words.length; i++) {
            file = new File(file, words[i]);
            if (!file.exists())
                file.mkdir();

        }
        return file;
    }

    private File getFolder(String name) {
        File folder = new File(name);
        String message = "\nFolder:" + name + " is not exist";
        if (!folder.exists()) {
            Util.makeFolder(name);
        }
        if (!folder.exists() || folder.isFile())
            throw new RuntimeException(message);
        return folder;
    }
    
    protected void writeFile(File file, String text){
        log("Write File:",file+"");
         Util.writeFile(file, text);
    }
   
    protected void writeJS(String name,String text) {
        File file = new File(workFolder, name + JS);
        log("Write JS:",file+"");
        writeFile(file, text);
    }
    
    protected void writeJava(String name, String text) {
        File file = new File(classFolder, name + JAVA);
        log("Write Java:",file+"");
        writeFile(file, text);
    }
    protected String readJava(String name) {
        File file = new File(classFolder, name + JAVA);
        return Util.readFile(file,true);
    }
    protected String readSource(String name) {
        File file = new File(classFolder, name);
        return Util.readFile(file,true);
    }
    
    protected void writeSource(String name, String text) {
        File file = new File(classFolder, name);
        log("Write File:",file+"");
        writeFile(file, text);
    }
    
    protected void writeANG(File folder,String name, String text,String html) {
        folder=getAngFolder(folder,name);
        File file = new File(folder, name + ANGCLASS);
        writeFile(file, text);
        file = new File(folder, name + ANGHTML);
        String subName=name+"-next";
        String template="<h1>name:{{name}}</h1>"+html;
        String data=Util.printf(template, new String[] {subName,subName});
        writeFile(file,data);
        file = new File(folder, name + ANGCSS);
        writeFile(file,"");
    }
    
    protected void writeModule(String name,String text){
        File file = new File(workFolder, name + ANGMODULE);
        writeFile(file,text);
    }
    
    protected File getAngFolder(File folder,String name){
        String[] pair=name.split("-");
        File newFolder=new File(folder,pair[0]);
        if (!newFolder.exists()) newFolder.mkdir();
        return newFolder;
    }
    
    protected String readUIFile(String name){
        return fileMgr.readFile(UI,name+XML);
    }
    
    protected String readFile(String name){
        return Util.readFile(new File(workFolder,name),true);
    }
    
    protected File getFile(String name){
        return new File(workFolder,name);
    }
    
    protected void writeFile(String name,String text){
        RenderModel rmModel=ThreadMgr.instance().getRenderModel();
        String targetFile =name+"."+rmModel.getTemplateExt();
        File file=new File(workFolder,targetFile);
        log("RenderModel",ThreadMgr.instance().getRenderModel()+"");
        log("Write File",file+"");
        writeFile(file, text);
    }
    
    protected void writeAndReplace(String text){
        RenderModel rmModel=ThreadMgr.instance().getRenderModel();
        String tempFile=templName+"."+rmModel.getTemplateExt()+TEMPLATE;
        String data    =readFile(tempFile);
        data =data!=null? freeMarker.tranWithText(data,text):text;
        writeFile(templName,data);
    }
    
    protected String getBeanClass(ClassModel model,String type,boolean isEntity) {
        log("GET BEAN","Read file from:app/template/"+type+".txt");
        FreeMarkModel freeMarkModel = FreeMarkModel.ofClass(
                model.cname()+ (isEntity? AbstractField.ENTITY:""), 
                pack,
                model.map(isEntity));
        String text = freeMarkModel.getResult(model.list(), type);
        log("GET BEAN","OK");
        return text;
    }
    
    public String getNewClass(ClassModel model,String key,String lanType) {
        String template=swapMgr.getCodeTemplate(key, lanType);
        Map map=model.map(false);
        map.put(CLASS,model.cname());
        map.put(PACKAGE,pack);
        String text = freeMarker.tranWithText(template, map);
        log("GET getNewClass",text);
        return text;
    }
    protected String getAngClass(ClassModel model,String type,String param) {
        FreeMarkModel fm = FreeMarkModel.ofClass(model.cname(), pack,model.map(false));
        boolean haveParam=param!=null;
        fm.put("haveparam",haveParam);
        fm.put("param",param==null? " ":param);
        String text = fm.getANG(model.list(), type);
        return text;
    }
    protected String strToFolder(String name){
     return (name.replaceAll(DOT,UNDER));
    }
    
    public void swapPackage(String source,String target) {
        String text=classFolder+"";
        String newFolder=text.replace(source, target);
        this.pack=pack.replace(source,target);
        classFolder=new File(newFolder);
    }
    protected void log(String command,String message){
        System.out.println(command+":::"+message);
    }
    
    protected String loadInput(String textFile){
        File folder=new File(INPUT);
        File curFile=new File(folder,textFile);
        return Util.readFile(curFile,true);
    }
    
    protected File  getSource(String mainFolder,String secondFolder){
        File folder=new File(SOURCE,mainFolder+"/"+secondFolder);
        if (!folder.exists() || !folder.isDirectory()) throw 
        new RuntimeException(folder+" do not exist");
        return folder;
    }
    
    protected String readAbsFile(File file){
        return Util.readFile(file,true);
    }
    
    protected String replaceMacro(String text){
        String result=text;
        while(true){
            int n=result.indexOf(MACRO);
            if (n<0) break;
            result= text.replace(MACRO, DOLLAR);
        }
        return result;
    }
    
    protected String replace(String main,String pattern,String replace){
        int n=main.indexOf(pattern);
        if (n<0) return main;
        String result=main.substring(0, n);
        result+=replace;
        result+=main.substring(n+pattern.length());
        return result;
    }
    
    
}
