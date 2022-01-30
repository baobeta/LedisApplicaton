package com.example.ledisapp.datastructure;

import java.util.*;

public class SetLedis {

    private HashMap<String, HashSet<String>> listSetLedis;

   public String SADD(String key, String... values) {
       Integer count =0;
       HashSet<String> hashSet = new HashSet<String>();
       for(String val :values) {
           if(!hashSet.contains(val)) {
               hashSet.add(val);
               count++;
           }
       }
       listSetLedis.put(key,hashSet);
       return "(integer)"+count;
   }
   public String SADD(String key,List<String> values) {
       Integer count =0;
       HashSet<String> hashSet = new HashSet<String>();
       for(String val :values) {
           if(!hashSet.contains(val)) {
               hashSet.add(val);
               count++;
           }
       }
       listSetLedis.put(key,hashSet);
       return "(integer)"+count;
   }
   public String SREM(String key, String... values) {

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
           return "ERROR";
       }
   }

   public String SREM(String key, List<String> values) {
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
           return "ERROR";
       }
   }

   public HashSet<String> SMEMBERS(String key) {
       if(listSetLedis.containsKey(key)) {
           return listSetLedis.get(key);
       } else {
           return null;
       }
   }

   public List<HashSet<String>> SINTER(String... keys) {
       List<HashSet<String>> listResult = new ArrayList<HashSet<String>>();
       for(String key : keys) {
           if(listSetLedis.containsKey(key)) {
               listResult.add(listSetLedis.get(key));
           }
       }
       return listResult;
   }

    public List<HashSet<String>> SINTER(List<String> keys) {
        List<HashSet<String>> listResult = new ArrayList<HashSet<String>>();
        for(String key : keys) {
            if(listSetLedis.containsKey(key)) {
                listResult.add(listSetLedis.get(key));
            } else {
                HashSet<String> emptySet = new HashSet<String>();
                emptySet.add("ERROR: NOT EXIST KEY");
                listResult.add(emptySet);
            }
        }
        return listResult;
    }

    public List<String> getListKeyLedis() {
        List<String> listKeyLedis = new ArrayList<String>();
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

    public void setListSetLedis(HashMap<String, HashSet<String>> listSetLedis) {
        this.listSetLedis = listSetLedis;
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
