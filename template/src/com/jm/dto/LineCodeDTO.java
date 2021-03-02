package com.jm.dto;

import com.jm.lcproc.AbsLCProc;
import com.jm.util.Util;

import java.io.File;

import java.util.*;


public class LineCodeDTO extends AbsModel {
    private String a = "";
    private String au = "";
    private String f = "";
    private String n = "Variable";
    private String ln = "variable";
    private String n1 = "variable1";
    private String n2 = "variable1";
    private String n3 = "variable1";
    private String n4 = "variable1";
    private String ln1 = "variable1";
    private String ln2 = "variable1";
    private String ln3 = "variable1";
    private String ln4 = "variable1";
    private String m = "method";
    private String m1 = "method1";
    private String m2 = "method2";
    private String c = "Guest";
    private String lc = "guest";
    private String c1 = "Guest";
    private String lc1 = "guest";
    private String cc = "";
    private String ccc = "";
    private String p = "";
    private String p0 = "param0";
    private String p1 = "param1";
    private String p2 = "param2";
    private String p3 = "param3";
    private String p4 = "";
    private String p5 = "param3";
    private String p6 = "";
    private String t = "Integer";
    private String t1 = "Person";
    private String s = "";
    private String ls = "";
    private String u = "";
    private String v = "";
    private String x = "${";
    private String mc = "//NOT ENTER"; //method comment
    private String mc1 = "//NOT ENTER"; //method comment
    private String bc = "//NOT ENTER"; //block comment
    private String hc ="//Not ENTER";
    private String imports = "";
    private String data = "";
    private String vallist1 = "";
    private String vallist2 = "";
    private String vallist3 = "";
    private String vallist4 = "";
    private String pack = "";
    private String group= "";
    private String cgroup= "";
    private String router="";
    private String label="";

    public void setCgoup(String cgroup) {
        this.cgroup = cgroup;
    }


    public String getCgroup() {
        return cgroup;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label.replace("_"," ");
    }

    public void setHc(String hc) {this.hc = hc;}
    public String getHc() {return hc;}

    public void setRouter(String router) {
        this.router = router;
    }

    public String getRouter() {
        return router;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setGroup(String group) {this.group = group;}

    public String getGroup() {return group;}
    public void setAu(String au) {this.au = au;}

    public String getAu() {return au;}

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getPack() {
        return pack;
    }

    public void setVallist1(String vallist1) {
        this.vallist1 = vallist1;
    }

    public String getVallist1() {
        return vallist1;
    }

    public void setVallist2(String vallist2) {
        this.vallist2 = vallist2;
    }

    public String getVallist2() {
        return vallist2;
    }

    public void setVallist3(String vallist3) {
        this.vallist3 = vallist3;
    }

    public String getVallist3() {
        return vallist3;
    }

    public void setVallist4(String vallist4) {
        this.vallist4 = vallist4;
    }

    public String getVallist4() {
        return vallist4;
    }


    private List<String> list = new ArrayList();
    private List<String> listAssign = new ArrayList();
    private List<String> listPair = new ArrayList();

    public void setU(String u) {
        this.u = u;
    }

    public String getU() {
        return u;
    }

    public String getLn1() {
        return lcap(n1);
    }

    public String getLn2() {
        return lcap(n2);
    }

    public String getLn3() {
        return lcap(n3);
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN2() {
        return cap(n2);
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getN3() {
        return cap(n3);
    }

    public void setN4(String n4) {
        this.n4 = n4;
    }

    public String getN4() {
        return cap(n4);
    }


    public void setImports(String imports) {
        this.imports = imports;
    }

    public String getImports() {
        return imports;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCc() {
        String[] words = Util.split(c, ".");
        String result = "";
        for (String word : words)
            result += cap(word);
        return result;
    }

    public void setMc1(String mc1) { this.mc1 = mc1;    }
    public String getMc1() { return mc1;   }
    public String getC1() {  return cap(c1);    }
    public void setC1(String c1) {    this.c1 = c1;   }
    public String getLc1() {   return lcap(c1);   }
    public void setLc1() {  this.c1 = c1;    }
    public void setMc(String mc) {  this.mc = mc;    }
    public String getMc() {     return mc;   }
    public void setBc(String bc) {      this.bc = bc;    }
    public String getBc() {        return bc;    }
    public void setV(String v) {        this.v = v;    }
    public String getV() {        return v;    }
    public void setLn(String ln) {   this.ln = ln;    }
    public String getLn() { return lcap(n);    }
    public void setF(String f) { this.f = f; }
    public String getF() {   return f; }
    public void setP(String p) {  this.p = p;}
    public String getP() { return p; }
    public String getX() {   return x;}
    public String getUn() {  return cap(n); }
    public String getUp0() {  return cap(p0);}
    public String getUp1() { return cap(p1); }
    public String getUp2() {  return cap(p2); }
    public String getUp3() { return cap(p3); }
    public void setLc(String lc) { this.lc = lcap(lc); }
    public String getLc() {  return addDot(c);  }
    public String getLs() {  return lcap(s);    }
    public String getS() {  return cap(s); }
    public void setP0(String p0) { this.p0 = p0;}
    public String getP0() {  return p0; }
    public void setP1(String p1) {   this.p1 = p1; }
    public String getP1() {  return p1; }
    public void setP2(String p2) {  this.p2 = p2; }
    public String getP2() {  return p2; }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP4() {
        return p4;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public String getP5() {
        return p5;
    }

    public void setP6(String p6) {
        this.p6 = p6;
    }

    public String getP6() {
        return p6;
    }

    public void setP3(String p3) { this.p3 = p3; }
    public String getP3() {  return p3;}
    public void setListAssign(List<String> listAssign) {  this.listAssign = listAssign; }
    public List<String> getListAssign() {  return listAssign; }
    public void setListPair(List<String> listPair) {  this.listPair = listPair; }
    public List<String> getListPair() { return listPair; }
    public void setList(List<String> list) { this.list = list; }
    public List<String> getList() {return list;}
    public void setS(String s) {
        this.s = cap(s);
        this.ls = lcap(s);
    }

    public String getC() {return capAll(c);}
    public void setT(String t) {this.t = t;}
    public String getT() {   return t; }
    public void setT1(String t1) { this.t1 = t1;  }
    public String getT1() {return t1; }
    public void setN(String n) { this.n = n; }
    public String getN() {return cap(n); }
    public void setN1(String n1) {this.n1 = n1;}
    public String getN1() {return n1;}
    public void setM(String m) { this.m = m;}
    public String getM() {   return m;}
    public void setM1(String m1) {this.m1 = m1;}
    public String getM1() {return m1;}
    public void setM2(String m2) {this.m2 = m2;}
    public String getM2() {return m2;}
    public void setCcc(String ccc) {this.ccc = ccc;}
    public String getCcc() {return c.toUpperCase();}

    /*
     * Set type
     * c Test c1 Test1
     */
    public void setVar(String text) {
        String[] words = split(text);
        if (words.length % 2 == 1) {
            log("Please enter even words");
            return;
        }
        for (int i = 0; i < words.length; i += 2) {
            String value = words[i + 1];
            value = value.equals("blank") ? "" : value;
            setProp(words[i], value);
        }
    }

    /*
     *
     */
    public void setp(String text) {
        String[] words = split(text);
        for (int i = 0; i < words.length; i++) {
            setProp(PARAM + (i), words[i]);
        }
    }

    public String loadClass(String text, boolean isSPA) {
        boolean isBlank = isBlank(text);
        if (isBlank) {
            list = new ArrayList();
            setP("");
            return getC();
        }
        String[] words = split(text);
        String result = "";
        list = new ArrayList();
        for (int i = 0; i < words.length; i++) {
            String conv = convert(words[i], isSPA);
            result += conv + ",";
            list.add(conv);
            if (!isSPA)
                setProp(PARAM + (i), conv);
        }
        result = removeLast(result);
        setP(result);
        return getC();
    }

    public void extendClass(String text) {
        String[] words = split(text);
        if (words.length < 2)
            sendError("It need at least two arguments");
        setC(words[0]);
        setLc(lcap(words[0]));
        setS(words[1]);
        String result = "";
        list = new ArrayList();
        for (int i = 2; i < words.length; i++) {
            result += words[i] + ",";
            list.add(words[i]);
            setProp(PARAM + (i - 2), words[i]);
        }
        result = removeLast(result);
        setP(result);
    }

    public String loadLine(String text) {
        String[] words = split(text);
        if (words.length < 2)
            sendError("Please set least One word");
        String pattern = words[0];
        setProp("n", words[1]);
        for (int i = 2; i < words.length; i++) {
            list.add(words[i]);
            setProp("n" + (i - 1), words[i]);
        }
        return pattern;
    }

    public void loadAttr(String text) {
        String[] words = split(text);
        if (words.length % 2 == 1) {
            log("Please enter even words");
            return;
        }
        listAssign = new ArrayList();
        listPair = new ArrayList();
        for (int i = 0; i < words.length; i += 2) {
            String var = words[i];
            String value = words[i + 1];
            listAssign.add(var + "=" + check(value));
            listPair.add(var);
            listPair.add(value);
        }

    }

    private String check(String value) {
        try {
            Double.parseDouble(value);
            return value;
        } catch (Exception e) {
            return QQ + value + QQ;
        }
    }


    private void sendError(String message) {
        throw new RuntimeException(message);
    }

    public void setC(String c) {
        this.c = c;
    }

    private String lcap(String text) {
        return Util.lcap(text);
    }

    private String cap(String text) {
        return Util.cap(text);
    }

    private boolean isBlank(String text) {
        return Util.isBlank(text);
    }

    /**
     * doImport pattern args
     * doImport import product_repository static_datasource
     * @param text
     * @param proc
     */
    public void doImport(String text, AbsLCProc proc) {
        imports = "";
        String pattern = Util.getFirst(text, SPACE);
        String rest = Util.getRight(text, SPACE);
        String template = proc.getLineCodeMgr().getLineCode(pattern);
        String[] words = split(rest);
        for (String word : words) {
            Map map = getMap(word);
            imports += proc.getFreeMarker().tranWithText(template, map) + RT;
        }
        imports=Util.removeLast(imports, 3);
    }
    
    public void addGroup(String text, AbsLCProc proc) {
        String pattern = Util.getFirst(text, SPACE);
        String template = proc.getLineCodeMgr().getBlockCode(pattern);
        FindVar var=FindVar.create(template);
        template=var.getResult();
        String rest = Util.getRight(text, SPACE);
        setp(rest);
        String result=proc.getFreeMarker().tranWithText(template, getMap());
        group+=result;
        for (String word:var.getList()){
            template = proc.getLineCodeMgr().getBlockCode(word);
            cgroup+=proc.getFreeMarker().tranWithText(template, getMap());
        }
      }
    
    public void route(String text, AbsLCProc proc) {
        String pattern = Util.getFirst(text, SPACE);
        String rest = Util.getRight(text, SPACE);
        setp(rest);
        String template = proc.getLineCodeMgr().getBlockCode(pattern);
        String result=proc.getFreeMarker().tranWithText(template, getMap());
        this.setRouter(result);
     }
    /*
     * setParam pattern args
     * setParam vallistp vallist1 id:n name category description price:n
     */
    public void setParam(String text, AbsLCProc proc) {
        String pattern = Util.getFirst(text, SPACE);
        String rest = Util.getRight(text, SPACE);
        String variable = Util.getFirst(rest, SPACE);
        rest = Util.getRight(rest, SPACE);
        String template = proc.getLineCodeMgr().getLineCode(pattern);
        String[] words = split(rest);
        String result = "";
        for (String word : words) {
            Map map = getMap(word);
            result += proc.getFreeMarker().tranWithText(template, map) + RT;
        }
        result=Util.removeLast(result, 3);
        setProp(variable, result);
    }

    /**
     *
     * @param text
     * @param absLCProc
     */
    public void setList(String text, AbsLCProc proc) {
        String pattern = Util.getFirst(text, SPACE);
        String rest = Util.getRight(text, SPACE);
        String variable = Util.getFirst(rest, SPACE);
        rest = Util.getRight(rest, SPACE);
        String template = proc.getLineCodeMgr().getLineCode(pattern);
        String[] words = split(rest);
        String result = "";
        for (String word : words) {
            String value = convert(word, true);
            Map map = new Hashtable();
            map.put("value", value);
            result += proc.getFreeMarker().tranWithText(template, map) + RT;
        }
        result=Util.removeLast(result,3);
        setProp(variable, result);
    }

    private Map getMap(String text) {
        String[] result = formURL(text);
        String value = result[0];
        String rest = result[1];
        String last = result[2];
        Map map = new Hashtable();
        map.put("last", last);
        map.put("value", capAll(value));
        map.put("lvalue", addDot(value));
        map.put("uvalue", value.toUpperCase());
        map.put("svalue", rest);
        map.put("c", this.getC());
        return map;
    }

    private String[] formURL(String value) {
        String[] words = Util.split(value, ".");
        value = words[0];
        String result = ".";
        String last="0";
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            if (i==2) last=word;
            if (nDot(word) != null)
                result += nDot(word);
            else
                result += "/" + word;
        }
        result += "/" + addDot(value);
        return new String[] { value, result,last };
    }


    private String capAll(String text) {
        String[] words = Util.split(text, "_");
        String result = "";
        for (String word : words)
            result += cap(word);
        return result;
    }

    private String addDot(String text) {
        return lcap(text).replace("_", ".");
    }

    public void data(String text, AbsLCProc proc) {
        data = proc.getLineCodeMgr().getBlockCode(text);
    }

    public String getData() {
        return data;
    }

    private String convert(String text, boolean isSPA) {
        if (!isSPA)   return text;
        String[] words = Util.split(text, ":");
        if (words.length == 1)
            return text + ":string";
        String result = "";
        int count = 0;
        for (String word : words) {
            boolean single = word.length() == 1;
            if (count == 0)
                result += word + ":";
            else if (!single)
                result += word;
            else
                result += getType(word);
            count++;
        }
        return result;
    }

    public void clear() {
        this.list.clear();
        this.imports = "";
        this.listAssign.clear();
        this.listPair.clear();
        this.group="";
        this.cgroup="";
    }


    private String getType(String word) {
        String result = "";
        word = word.toLowerCase();
        switch (word) {
        case "n":
            result = "number";
            break;
        case "s":
            result = "string";
            break;
        case "b":
            result = "boolean";
            break;
        case "o":
            result = "{}";
            break;
        case "a":
            result = "[]";
            break;
        }
        return result;
    }

    private String nDot(String text) {
        if (text.length() != 1)
            return null;
        int n = 0;
        try {
            n = Integer.parseInt(text);
        } catch (Exception e) {
        }
        String result = "";
        for (int i = 0; i < n; i++)
            result += ".";
        return result;
    }


    public void saveGroup(File currentFile) {
    }
}
