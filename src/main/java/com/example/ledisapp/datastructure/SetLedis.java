package com.example.ledisapp.datastructure;

import com.example.ledisapp.exception.KeyNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SetLedis {

    private HashMap<String, HashSet<String>> listSetLedis;

   public String SADD(String key,List<String> values) {
       Integer count =0;
       HashSet<String> hashSet = new HashSet<>();
       for(String val :values) {
           if(!hashSet.contains(val)) {
               hashSet.add(val);
               count++;
           }
       }
       listSetLedis.put(key,hashSet);
       return "(integer)"+count;
   }


   public String SREM(String key, List<String> values) throws KeyNotFoundException {
       Integer count =0;
       if(listSetLedis.containsKey(key)){
           for(String val :values) {
               if(this.getListSetLedis().get(key).contains(val)){
                   this.getListSetLedis().get(key).remove(val);
                   count++;
               }
           }

           return "(interger)"+count;
       } else {
           throw new KeyNotFoundException("ERROR: Key not exist");
       }
   }

   public HashSet<String> SMEMBERS(String key) throws KeyNotFoundException {
       if(listSetLedis.containsKey(key)) {
           return listSetLedis.get(key);
       } else {
           throw new KeyNotFoundException("ERROR: Key not exist");
       }
   }


    public List<HashSet<String>> SINTER(List<String> keys) {
        List<HashSet<String>> listResult = new ArrayList<>();
        for(String key : keys) {
            if(listSetLedis.containsKey(key)) {
                listResult.add(listSetLedis.get(key));
            } else {
                HashSet<String> emptySet = new HashSet<>();
                emptySet.add("ERROR: NOT EXIST KEY");
                listResult.add(emptySet);
            }
        }
        return listResult;
    }

    public List<String> getListKeyLedis() {
        List<String> listKeyLedis = new ArrayList<>();
        Set<String> setKey = listSetLedis.keySet();
        setKey.forEach(c -> listKeyLedis.add(c));
        return  listKeyLedis;

    }

    public boolean deleteKey(String key) {
        if(listSetLedis.containsKey(key)) {
            listSetLedis.remove(key);
            return true;
        } else {
            return false;
        }

    }

    public HashMap<String, HashSet<String>> getListSetLedis() {
        return listSetLedis;
    }

    public SetLedis() {
        this.listSetLedis = new HashMap<>();
    }
    public void setValueSet(SetLedis set) {
       listSetLedis.clear();
       Set<String> key = set.getListSetLedis().keySet();
       for (String s : key) {
           listSetLedis.put(s,set.getListSetLedis().get(s));
       }
    }
}
