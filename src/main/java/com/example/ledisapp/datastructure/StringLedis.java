package com.example.ledisapp.datastructure;

import java.util.*;

public class StringLedis {
    private HashMap<String, String> listStringLedis;



    public String SET(String key, String value) {
        this.listStringLedis.put(key, value);
        return "OK";
    }
    public String GET(String key)
    {
        if(this.listStringLedis.containsKey(key)) {
            return this.listStringLedis.get(key);
        } else{
            return "ERROR: NOT FOUND KEY";
        }
    }

    public HashMap<String, String> getListStringLedis() {
        return listStringLedis;
    }
    public void setListStringLedis(HashMap<String, String> listStringLedis) {
        this.listStringLedis = listStringLedis;
    }
    public List<String> getListKeyLedis() {
        List<String> listKeyLedis = new ArrayList<String>();
        Set<String> setKey =  listStringLedis.keySet();
        setKey.forEach(c -> listKeyLedis.add(c));
        return  listKeyLedis;

    }

    public boolean deleteKey(String key) {
        if(listStringLedis.containsKey(key)) {
            listStringLedis.remove(key);
            return true;
        } else {
            return false;
        }

    }
    public StringLedis() {
        this.listStringLedis = new HashMap<String, String>();
    }

    public void setValueString(StringLedis string) {
        listStringLedis.clear();
        Set<String> key = string.getListStringLedis().keySet();
        for(String s : key) {
            listStringLedis.put(s,string.getListStringLedis().get(s));
        }
    }

}
