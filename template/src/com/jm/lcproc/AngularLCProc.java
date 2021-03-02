package com.jm.lcproc;

public class AngularLCProc extends AbsLCProc{
    public static String MARK="/*ANGULAR_%s%s*/";
    public static String HMARK="<!--ANGULARHTML-->";
    public static String EXT =".ts";
    
    @Override
    public String getMark() {
        String first= isBlock()? BLOCK:METHOD;
        String extra= getExtra();
        return String.format(MARK,first,extra);
    }

    @Override
    public String getExt() { return EXT; }
    
    @Override
    public String getMarkFC(boolean isBlock,String more) {
        String first= isBlock? BLOCK:METHOD;
        return String.format(MARK,first,more);
    }

    @Override
    public boolean isSPA() {return true;}

    @Override
    public boolean isReact() { return false; }

    @Override
    public String getHMark() {
          return HMARK;
    }

    @Override
    public String getHExt() {   return ".html";  }
}
