package com.jm.test;


import com.jm.lcproc.*;
import com.jm.util.ScriptFunc;
import com.jm.util.Util;
import java.io.File;

public class Search extends AbstractMain {
    public static final String SCRIPT =".script";
    private File currentFolder = new File("assistant");
    private File workFolder = currentFolder;
    private File currentFile = new File("assistant\\result.txt");
    private static Search search = new Search();
    private String curClassName;
    public AbsLCProc absLCProc;

    public void folder(String file) {
        currentFolder = new File(file);
        if (!currentFolder.exists() || !currentFolder.isDirectory()) {
            log(String.format("currentFolder [%s] is not exist", file));
            currentFolder = null;
        } else {
            setFileProp();
            log(String.format("set currentFolder [%s]", file));
        }
    }
    
    public void workFolder(String file) {
        workFolder=new File(file);
        if (!workFolder.exists() || !workFolder.isDirectory()) {
            log(String.format("workFolder [%s] is not exist", file));
            workFolder = null;
        } else {
           
            log(String.format("set workFolder [%s]", file));
        }
    }
    /**
     * set mark extension
     * @param text
     */
    public void section(String text) {
        text = text.equalsIgnoreCase("n") ? "" : text;
        absLCProc.setExtra(text);
        log(String.format("set mark extension [%s]", text));
    }

    public void file(String file) {
        if (currentFolder == null) {
            log("please use [folder ?]");
            return;
        }
        boolean haveDot=file.indexOf(".")>0;
        if (!haveDot) file+=absLCProc.getExt();
        currentFile = new File(currentFolder, file);
        if (!currentFile.exists() || currentFile.isDirectory()) {
            log(String.format("file [%s] is not exist", currentFile));
            currentFile = null;
        } else {
            setFileProp();
            log(String.format("set file [%s]", currentFile));
        }
    }

    public void fullPath(String file) {
        currentFile = new File(file);
        if (!currentFile.exists() || currentFile.isDirectory()) {
            log(String.format("file [%s] is not exist", currentFile));
            currentFile = null;
        } else
            log(String.format("set file [%s]", currentFile));
        currentFolder = new File(Util.getLeft(file, "\\"));
        log(String.format("set folder [%s]", currentFolder));
    }

    public void use(String name) {
        AbsLCProc lcProc = null;
        switch (name) {
        case JAVA:  lcProc = new JavaLCProc();break;
        case DOCKER:lcProc = new DockerLCProc();break;
        case PYTHON:lcProc = new PythonLCProc();break;
        case SCALA:lcProc  = new ScalaLCProc();break;
        case JAVASCRIPT: lcProc=new JavascriptLCProc();break;
        case ANGULAR: lcProc=new AngularLCProc();break;
        case REACT: lcProc=new ReactLCProc();break;
        }
        boolean ok = lcProc.load(name);
        if (!ok) {
            log(String.format("Can not read lineCode [%s]", name));
            return;
        } else
            absLCProc = lcProc;
        setFileProp();
    }


    private void setFileProp() {
        if (absLCProc != null) {
            absLCProc.setCurrentFile(currentFile);
            absLCProc.setFolder(currentFolder);
        }
    }

    public void showParam() {
        Util.printHash(absLCProc.getLineCodeDTO().getMap());
    }

    public void showLines() {
        absLCProc.getLineCodeMgr().showLineMap();
    }

    public void showBlocks() {
        absLCProc.getLineCodeMgr().showBlockMap();
    }

    public void setp(String text) {
        absLCProc.getLineCodeDTO().setp(text);
     }
    
    public void clear(){
        absLCProc.getLineCodeDTO().clear();
    }
    
    public void method(String text) {
        absLCProc.getLineCodeDTO().loadAttr(text);
        printblock("method");
    }

    /**
     * print Line code
     * @param pattern
     */
    public void line(String pattern) {
        absLCProc.printLine(pattern);
    }

    /**
     * print block
     * @param pattern
     */
    public void printblock(String pattern) {
        String file = absLCProc.printBlock(pattern);
        if (file != null)   file(file);

    }

    public void help() {
        log("current folder:" + currentFolder);
        log("current   file:" + currentFile);
        log(readFile(SEARCHHELP));
    }

    /**
     * set argument with n 233 n1 1222
     * @param text
     */
    public void set(String text) {
        absLCProc.getLineCodeDTO().setVar(text);
        showParam();
    }
    
    public void doImport(String text){
        absLCProc.getLineCodeDTO().doImport(text,absLCProc);
        showParam();
    }
    
    public void addGroup(String text){
        absLCProc.getLineCodeDTO().addGroup(text,absLCProc);
        }
    
    public void route(String text){
        absLCProc.getLineCodeDTO().route(text,absLCProc);
        }
    
    public void saveGroup(){
        absLCProc.saveGroup();
    }
    
    public void setParam(String text){
        absLCProc.getLineCodeDTO().setParam(text,absLCProc);
        showParam();
    }
    
    public void setList(String text){
        absLCProc.getLineCodeDTO().setList(text,absLCProc);
        showParam();
    }
    
    public void data(String text){
        absLCProc.getLineCodeDTO().data(text,absLCProc);
    }
    
    public void setattr(String text) {
        absLCProc.getLineCodeDTO().loadAttr(text);
        showParam();
    }

    public void createClass(String text) {
        absLCProc.getLineCodeDTO().loadClass(text,false);
        showParam();
        printblock("class");
    }
    
    public void createModel(String text) {
          absLCProc.createModel(currentFolder+"",text);
    }
    
    
    public void makeClass(String text) {
        String type=Util.getFirst(text, SPACE);
        String rest =Util.getRight(text, SPACE);
        boolean isSPA=absLCProc.isSPA();
        curClassName=absLCProc.getLineCodeDTO().loadClass(rest,isSPA);
        showParam();
        if (!isSPA) printblock(type);
        else {
              String result=absLCProc.printSPAModel(type);
              file(result);
        }
    }
    
    public void makeMethod(String text) {
        String method=Util.getFirst(text, SPACE);
        String rest =Util.getRight(text, SPACE);
        absLCProc.getLineCodeDTO().loadClass(rest,false);
        absLCProc.getLineCodeDTO().setN(method);
        showParam();
        printblock(method);
    }
    
    
    public void run(String input) {
        String[] words=Util.split(input);
        String type=words[0];
        String name=words[1];
        execute("use "+type);
        File folder=new File(workFolder,type);
        File file=new File(folder,name+SCRIPT);
        if (!file.exists() || file.isDirectory()) {
            log(String.format("file [%s] is not exist", file));
            return;}
        String text=Util.readFile(file, false);
        log("read script file:"+file);
        log("data==>\n"+text);
        String[] lines=Util.getLines(text);
        for (String line:lines) {
            line=line.trim();
            if (line.startsWith("//") || isBlank(line)) continue;
            log(String.format("execute [%s] command", line));
            execute(line);
        }
     }
    
    public void createExtend(String argument){
        absLCProc.getLineCodeDTO().extendClass(argument);
        showParam();
        printblock("extend");
    }
    
    public void runMain(String command){
        RunMain.exec(command);
    }
    
    public static void main(String[] args) {
        execute("use java");
        while (true) {
            String line = readLine(SEARCH);
            if (line.startsWith(QUIT))
                System.exit(1);
            execute(line);
        }
    }

    public static void execute(String line) {
        boolean isBlank = Util.isBlank(line);
        if (isBlank)    return;
        ScriptFunc scriptFunc = ScriptFunc.getSF();
        scriptFunc.addVar("main", search);
        ProcessInput processInput = new ProcessInput(Search.class);
        String text = processInput.searchTran(line);
        
        try {
            scriptFunc.eval(text);
        } catch (Exception e) {
            log(e.getMessage());
            System.exit(0);
        }
    }
}
