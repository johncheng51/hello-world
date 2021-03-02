package com.jm.lcproc;

import com.jm.dto.AbsModel;
import com.jm.model.XmlBody;
import com.jm.util.Util;
import com.jm.xml.ReadProp;
import com.jm.xml.XmlBlock;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

public class LineBlockParse extends AbsModel{
    public static final String NAME="name";
    Map<String,Map<String,XmlBody>> currentMap;
    private String type;
    private boolean processOK=true;
    private Map<String,String>  simpleTable;
    private Map<String,XmlBody>  blockTable;
    public LineBlockParse(String type) {
      this.type=type;
      process();
    }

    public Map<String, XmlBody> getBlockTable() {
        return blockTable;
    }

    public void setSimpleTable(Map<String, String> simpleTable) {
        this.simpleTable = simpleTable;
    }

    public Map<String, String> getSimpleTable() {
        return simpleTable;
    }

    public void setProcessOK(boolean processOK) {
        this.processOK = processOK;
    }

    public boolean isProcessOK() {
        return processOK;
    }

    private void process() {
        File file=new File(APPLINE,type+TXT);
        if (!file.exists()) { processOK=false;return;}
        String textFile=Util.readFile(file, true);
        currentMap=XmlBlock.getMap(textFile); 
        if (currentMap==null) log("====>Error read data:"+textFile);
        else log("Reading in file:"+file+" OK");
        processSingle();
        processBlocks();
    }
    
    private void processSingle(){
        Map<String,XmlBody> map=currentMap.get(MAIN);
        XmlBody xb=map.get(MAIN+"0");
        String text=xb.body();
        simpleTable=ReadProp.getT(text);
    }
    
    private void processBlocks(){
        Map<String,XmlBody> map=currentMap.get(BLOCK);
        blockTable=new Hashtable();
        if (map==null) return;
        map.values().forEach(x->{
            String name=x.get(NAME); 
            blockTable.put(name,x);                  
        });
       }
}
