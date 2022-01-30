package com.example.ledisapp.utils;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.datastructure.StringLedis;
import org.springframework.util.StopWatch;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Expire {

    private static HashMap<String,Double> exprire = new HashMap<>();

    public static String KEYS (SetLedis set, StringLedis string) {
        String result ="";
        List<String> listKeyString = string.getListKeyLedis();
        if(listKeyString !=null && listKeyString.size()!=0) {
            String keyString = "<h4> Key of string: ";
            for(String s : listKeyString) {
                keyString += s+", ";
            }
            keyString+="</h4>";
            result+=keyString;
        } else  {
            result+= "<h4> Not exist key in string </h4>";
        }
        List<String> listKeySet = set.getListKeyLedis();
        if(listKeySet !=null && listKeySet.size()!=0) {
            String keySet = "<h4> Key of set: ";
            for(String s : listKeySet) {
                keySet += s+", ";
            }
            keySet+="</h4>";
            result+=keySet;
        } else  {
            result+= "<h4> Not exist key in set </h4>";
        }
        return result;

    }

    public static boolean DEL(String key,SetLedis set, StringLedis string){
        return set.deleteKey(key)||string.deleteKey(key);
    }

    public static String TTL(String key,SetLedis set, StringLedis string) {
        if(set.getListSetLedis().containsKey(key)|| string.getListStringLedis().containsKey(key)) {
            if(exprire.containsKey(key)) {
                return exprire.get(key) +"";
            } else {
                return "KEY HAS NO EXPIRATION";
            }
        } else {
            return "ERROR: KEY NOT EXIST";
        }
    }
    public static String EXPIRE(String key,double time, SetLedis set, StringLedis string) {
        if(set.getListSetLedis().containsKey(key)|| string.getListStringLedis().containsKey(key)) {
            exprire.put(key,time);
            return time+"";
        } else {
            return "ERROR: KEY NOT EXIST";
        }

    }
}
