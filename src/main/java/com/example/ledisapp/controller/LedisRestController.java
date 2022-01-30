package com.example.ledisapp.controller;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.datastructure.StringLedis;
import com.example.ledisapp.utils.Expire;
import com.example.ledisapp.utils.Snapshots;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LedisRestController {

    private  SetLedis set = new SetLedis();
    private  StringLedis string = new StringLedis();

    @PostMapping("/sendkey")
    public String returnValue(@Param("key") String key) {
        HashMap<String, Integer> commandLine = buildValueCommand();
        List<String> splitKey = Arrays.asList(key.split(" "));
        if(!commandLine.containsKey(splitKey.get(0))) {
            return "ERROR: Not found command ";
        } else {
            if(splitKey.get(0).equals("SET")) {
                if(splitKey.size()!=3) {
                    return "ERROR: The argument not valid";
                } else {
                    return string.SET(splitKey.get(1),splitKey.get(2));

                }
            } else if(splitKey.get(0).equals("GET")) {
                if(splitKey.size()!=2) {
                    return "ERROR: The argument not valid";
                } else {
                    return string.GET(splitKey.get(1));
                }
            } else if(splitKey.get(0).equals("SADD")){
                if(splitKey.size()<3) {
                    return "ERROR: The argument not valid";
                } else {
                    List<String> values = splitKey.subList(2,splitKey.size());
                    return set.SADD(splitKey.get(1),values);
                }
            } else if(splitKey.get(0).equals("SREM")) {
                if(splitKey.size()<3) {
                    return "ERROR: The argument not valid";
                } else {
                    List<String> values = splitKey.subList(2,splitKey.size());
                    return set.SREM(splitKey.get(1),values);
                }
            }  else if(splitKey.get(0).equals("SMEMBERS")) {
                if(splitKey.size()<2) {
                    return "ERROR: The argument not valid";
                } else {
                    String result = "";
                    HashSet<String> setValues = set.SMEMBERS(splitKey.get(1));
                    if(setValues != null) {
                        for(String value : setValues) {
                            result+=" "+value;
                        }
                        return result;
                    } else {
                        return "ERROR: NOT EXIST KEY";
                    }
                }
            } else if(splitKey.get(0).equals("SINTER")){
                if(splitKey.size()<2) {
                    return "ERROR: The argument not valid";
                } else {
                    List<HashSet<String>> listSetValues = set.SINTER(splitKey.subList(1,splitKey.size()));
                    List<String> listValuesReturn = new ArrayList<String>();
                    String value ="";
                    for(HashSet<String> set : listSetValues) {
                        String result="<h4>";
                        for(String s : set) {
                            result+=" "+s;
                        }
                        result+="</h4>";
                        listValuesReturn.add(result);
                    }
                    for(String s :listValuesReturn) {
                        value+=s;
                    }
                    return value;

                }
            }
            else if(splitKey.get(0).equals("KEYS")) {
                if(splitKey.size()==1) {
                    return Expire.KEYS(set,string);
                } else {
                    return "ERROR: COMMAND NOT VALID";
                }
            }
            else if(splitKey.get(0).equals("DEL")) {
                if(splitKey.size()==2) {
                    if(Expire.DEL(splitKey.get(1),set, string)) {
                        return "DELETE KEY  SUCCESS";
                    } else {
                        return "DELETE KEY ERROR";
                    }
                } else {
                    return "ERROR: COMMAND NOT VALID";
                }
            } else if(splitKey.get(0).equals("EXPIRE")) {
                if(splitKey.size()==3) {
                    try {
                        return Expire.EXPIRE(splitKey.get(1),Double.parseDouble(splitKey.get(2)),set,string);
                    } catch(NumberFormatException e) {
                        return "ERROR: COMMAND NOT VALID";
                    }

                } else {
                    return "ERROR: COMMAND NOT VALID";
                }
            }  else if(splitKey.get(0).equals("TTL")) {
                if (splitKey.size() == 2) {
                    return Expire.TTL(splitKey.get(1), set, string);
                } else {
                    return "ERROR: COMMAND NOT VALID";
                }
            }
            else if(splitKey.get(0).equals("SAVE")) {
                if(splitKey.size()==1) {
                    return Snapshots.SAVE(set,string);
                } else {
                    return "ERROR: COMMAND NOT VALID";
                }
            }
            else if(splitKey.get(0).equals("RESTORE")) {
                if(splitKey.size()==1) {
                    return Snapshots.RESTORE(set,string);
                } else {
                    return "ERROR: COMMAND NOT VALID";
                }
            }

            else {
                return "ERROR";
            }

        }
    }

    public HashMap<String,Integer> buildValueCommand() {
        HashMap<String, Integer> value = new HashMap<String,Integer>();
        value.put("GET",1);
        value.put("SET",2);
        value.put("SADD",2);
        value.put("SREM",2);
        value.put("SMEMBERS",2);
        value.put("SINTER",2);
        value.put("KEYS",3);
        value.put("DEL",3);
        value.put("EXPIRE",3);
        value.put("TTL",3);
        value.put("SAVE",4);
        value.put("RESTORE",4);
        return value;

    }
}
