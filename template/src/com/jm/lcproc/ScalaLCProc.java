package com.jm.lcproc;

public class ScalaLCProc extends AbsLCProc{
    public static String MARK="/*SCALA_%s%s*/";
    public static String EXT =".scala";
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
    public boolean isSPA() {return false;}

    @Override
    public boolean isReact() {
        // TODO Implement this method
        return false;
    }

    @Override
    public String getHMark() {
        // TODO Implement this method
        return null;
    }

    @Override
    public String getHExt() {
        // TODO Implement this method
        return null;
    }
}
