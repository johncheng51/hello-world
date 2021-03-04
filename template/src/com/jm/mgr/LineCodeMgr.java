package com.jm.mgr;

import com.jm.lcproc.LineBlockParse;
import com.jm.model.XmlBody;
import com.jm.util.Util;
import java.util.Hashtable;
import java.util.Map;

public class LineCodeMgr {
    private Map<String,LineBlockParse> curMap=new Hashtable();
    public static final String ERROR="XMLBODY %s not found in the block";
    public static final String ERROR1="Line pattern %s not found";
    private LineBlockParse lineBlockParse;
    private LineCodeMgr() {}
    
    public boolean  load(String name){
        lineBlockParse=curMap.get(name);
        if (lineBlockParse==null) {
            lineBlockParse=new LineBlockParse(name);
            if (!lineBlockParse.isProcessOK())  return false;
        }
        curMap.put(name, lineBlockParse);
        return true;
    }
    
    public void showLineMap(){
        Map<String,String> map=lineBlockParse.getSimpleTable();
        Util.printHash(map);     
    }
    
    public void showBlockMap(){
        Map<String,XmlBody> map=lineBlockParse.getBlockTable();
        Util.printBHash(map);
        }
    
    public XmlBody getXmlBody(String pattern){
        Map<String, XmlBody> map=lineBlockParse.getBlockTable();
        XmlBody  body=map.get(pattern);
        if (body==null) throw new RuntimeException(String.format(ERROR,pattern));
        return body;
    }
    
    public String getBlockCode(String pattern){
        return getXmlBody(pattern).body();
    }
    public String getBlockProp(String pattern,String key){
        return getXmlBody(pattern).get(key);
    }
    
    public String getLineCode(String pattern){
        String template= lineBlockParse.getSimpleTable().get(pattern);
        if (template==null) throw new RuntimeException(String.format(ERROR1,pattern));
        else return template;
    }
    
  
    private static LineCodeMgr mgr;
    private static Object lock = new Object();
    public synchronized static LineCodeMgr instance() {
        if (mgr != null)   return mgr;
        synchronized (lock) {
            mgr = new LineCodeMgr();
            mgr.init();
            return mgr;
        }
    }

    private void init() {
    }
}
