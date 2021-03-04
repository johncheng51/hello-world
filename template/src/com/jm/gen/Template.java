package com.jm.gen;

import com.jm.dto.AbsModel;
import com.jm.dto.CrudDTO;
import com.jm.dto.spring.BasicDTO;
import com.jm.dto.spring.EntityDTO;
import com.jm.model.XmlBody;
import com.jm.util.Util;
import com.jm.xml.XmlBlock;
import java.io.File;
import java.util.Map;
import java.util.stream.Stream;

public class Template extends AbstractGen{
   private String inputFile;
   public Template() {}
    public Template(String inputFile) {
        this.inputFile=inputFile+XML;
        process();}
    
    public void process(){
       boolean needSwap=false;
       String xmlData=loadInput(inputFile);
       log("xmlData:"+xmlData,"");
       Map<String,Map<String,XmlBody>> map=XmlBlock.getMap(xmlData);
       XmlBody xmlBody=map.get(MAIN).get(MAIN+0);
       String action  =xmlBody.getMap().get(ACTION);
       String className=xmlBody.getMap().get("class").toLowerCase();
       AbsModel absModel=null;
       switch(className){
       case "basic":  absModel=new BasicDTO();break;
       case "entity": absModel=new EntityDTO();needSwap=true;break; 
       case "crud":  absModel=new CrudDTO();needSwap=true;break;
       }
       absModel.setAction(action);
       absModel.setClassName(className);
       absModel.load(xmlBody.body());
       processModule(absModel,map);
       processFiles(absModel,needSwap);
    }
    
    private void processFiles(AbsModel dto,boolean needSwap){
        File sourceFolder=getSource(dto.getAction(),dto.getClassName());
        File[] filesList = Util.getAllFiles(sourceFolder);
        Stream.of(filesList).forEach(file->processFile(file,dto,needSwap));
    }
    
    private void processFile(File file,AbsModel dto,boolean needSwap) {
        Map<String,String> map=dto.getMap();
        String content=readAbsFile(file);
        content=freeMarker.tranWithText(content, dto.getMap());
        content=replaceMacro(content);
        String name   =dto.diff(file.getAbsolutePath());
        name=needSwap? freeMarker.tranWithText(name, map):name;
        File targetFile=new File(dto.getPrjFolder(),name);
        Util.writeFile(targetFile, content);
    }
    
    private void processModule(AbsModel dto,Map<String,Map<String,XmlBody>> map){
        CrudDTO crudDTO=(CrudDTO) dto;
        Map<String,XmlBody> mdMap=map.get(MODULE);
        for ( XmlBody xmlBody:mdMap.values()) {
            File file=new File(dto.getFullFolder(),xmlBody.get(FILE));
            String content=this.readAbsFile(file);
            crudDTO.setExtra(xmlBody.get(EXTRA));
            boolean isExist=isExistPattern(crudDTO,xmlBody,content);
            if (isExist){
                log("Skip Writing:::",file+"");
                continue;
            }
            String  pattern=createPattern(crudDTO,xmlBody);
            String body=freeMarker.tranWithText(xmlBody.body(), dto.getMap());
            body=replaceMacro(body);
            //log("body:",body);
            String swapText=body+"\r\n"+pattern;
            content=replace(content,pattern, swapText);
            //log("content:",content);
            Util.writeFile(file, content);
        }
    }
    
    
    private String createPattern(CrudDTO crudDTO,XmlBody xb){
        String type=xb.get(TYPE);
        return String.format(getPattern(crudDTO,type),MARKER+crudDTO.getExtra());
    }
    
    
    private boolean  isExistPattern(CrudDTO crudDTO,XmlBody xb,String text){
        String type=xb.get(TYPE);
        String pattern=String.format(getPattern(crudDTO,type),
                                     crudDTO.getUcomp()+crudDTO.getExtra());
        boolean flag= text.indexOf(pattern)>=0;
        return flag;
    }
    
    private String getPattern(AbsModel dto,String type){
        String action=dto.getAction();
        String format=null;
        switch(action){
        case  "angular":boolean isHTML=type.equals(HTMLTYPE);
                        format=isHTML? "<!--%s-->":"/*%s*/";
                        break;
        case "react": boolean isImport=type.equals(IMPORT);
                       format=isImport? "/*%s*/":"{/*%s*/}";
                       break;
            
        }
        return format;
    }
}
