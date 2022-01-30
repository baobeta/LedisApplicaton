package com.example.ledisapp.service;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.exception.ArgumentNotValidException;
import com.example.ledisapp.exception.KeyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class SetService {

    public String SADD(List<String> splitKey,SetLedis set) throws ArgumentNotValidException {
        if(splitKey.size()<3) {
            throw new ArgumentNotValidException("ERROR: The argument not valid") ;
        } else {
            List<String> values = splitKey.subList(2,splitKey.size());
            return set.SADD(splitKey.get(1),values);
        }
    }

    public String SREM(List<String> splitKey,SetLedis set) throws ArgumentNotValidException, KeyNotFoundException {
        if(splitKey.size()<3) {
            throw new ArgumentNotValidException("ERROR: The argument not valid") ;
        } else {
            List<String> values = splitKey.subList(2,splitKey.size());
            return set.SREM(splitKey.get(1),values);
        }
    }

    public String SMEMBERS(List<String> splitKey,SetLedis set) throws ArgumentNotValidException, KeyNotFoundException {
        if(splitKey.size()<2) {
            throw new ArgumentNotValidException("ERROR: The argument not valid") ;
        } else {
            String result = "";
            HashSet<String> setValues = set.SMEMBERS(splitKey.get(1));
            if(setValues != null) {
                for(String value : setValues) {
                    result+=" "+value;
                }
                return result;
            } else {
                throw  new KeyNotFoundException("ERROR: Not exist key");

            }
        }
    }
    public String SINTER (List<String> splitKey,SetLedis set) throws ArgumentNotValidException {
        if(splitKey.size()<2) {
            throw new ArgumentNotValidException("ERROR: The argument not valid") ;
        } else {
            List<HashSet<String>> listSetValues = set.SINTER(splitKey.subList(1,splitKey.size()));
            List<String> listValuesReturn = new ArrayList<String>();
            String value ="";
            Integer index = 1;
            // get value of every key
            for(HashSet<String> setValues : listSetValues) {
                String result="<h4>"+splitKey.get(index);
                index++;
                for(String s : setValues) {
                    result+=" "+s;
                }
                result+="</h4>";
                listValuesReturn.add(result);
            }

            // add to one string
            for(String s :listValuesReturn) {
                value+=s;
            }
            return value;
        }
    }
}
