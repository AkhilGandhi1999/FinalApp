package com.example.finalapp;

import java.util.Iterator;
import java.util.LinkedList;

public class Medicine{
    public String MedName;
    public LinkedList<Integer> hh;
    public LinkedList<Integer> mm;
    public int startdd,startmm,startyy,enddd,endmm,endyy;
    public Medicine(String MedName,LinkedList<Integer> hours,LinkedList<Integer> minutes,int startdd,int startmm,int startyy,
                    int enddd,int endmm,int endyy
    ){
        this.hh = new LinkedList<Integer>();
        this.mm = new LinkedList<Integer>();
        this.MedName = MedName;
        this.startdd = startdd;
        this.startmm = startmm;
        this.startyy = startyy;
        this.enddd = enddd;
        this.endmm = endmm;
        this.endyy = endyy;
        Iterator itr = hours.iterator();
        Iterator ptr = minutes.iterator();
        while(itr.hasNext() && ptr.hasNext()){
            this.hh.add((Integer) itr.next());
            this.mm.add((Integer) ptr.next());
        }
    }
    public Medicine(){
        hh = new LinkedList<Integer>();
        mm = new LinkedList<Integer>();
    }
}
