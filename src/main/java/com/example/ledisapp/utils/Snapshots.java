package com.example.ledisapp.utils;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.datastructure.StringLedis;

public class Snapshots {
    private static  SetLedis storeSet = new SetLedis() ;
    private static StringLedis storeString = new StringLedis();

    public static String SAVE(SetLedis set,StringLedis string) {
        storeSet.setValueSet(set);
        storeString.setValueString(string);
        return "SAVE SUCCESS";
    }

    public static String RESTORE(SetLedis set,StringLedis string) {
        if(storeSet.getListKeyLedis()==null && storeString.getListStringLedis()==null ) {
            return "NOT EXIST SNAPSHOT";
        } else {
            set.setValueSet(storeSet);
            string.setValueString(storeString);
            return "RESTORE SUCCESS";
        }
    }

    public SetLedis getStoreSet() {
        return storeSet;
    }

    public void setStoreSet(SetLedis storeSet) {
        this.storeSet = storeSet;
    }

    public StringLedis getStoreString() {
        return storeString;
    }

    public void setStoreString(StringLedis storeString) {
        this.storeString = storeString;
    }
}
