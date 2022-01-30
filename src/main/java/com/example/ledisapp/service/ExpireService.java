package com.example.ledisapp.service;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.datastructure.StringLedis;
import com.example.ledisapp.exception.CommandNotFoundException;
import com.example.ledisapp.utils.Expire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpireService {
    public String KEYS(List<String> splitKey, SetLedis set, StringLedis string) throws CommandNotFoundException {
        if(splitKey.size()==1) {
            return Expire.KEYS(set,string);
        } else {
            throw  new CommandNotFoundException("ERROR: Command not valid");
        }
    }

    public String DEL(List<String> splitKey,SetLedis set, StringLedis string) throws CommandNotFoundException {
        if(splitKey.size()==2) {
            if(Expire.DEL(splitKey.get(1),set, string)) {
                return "DELETE KEY  SUCCESS";
            } else {
                return "DELETE KEY ERROR";
            }
        } else {
            throw  new CommandNotFoundException("ERROR: Command not valid");
        }
    }

    public String EXPIRE (List<String> splitKey,SetLedis set, StringLedis string) throws CommandNotFoundException {
        if(splitKey.size()==3) {
            try {
                return Expire.EXPIRE(splitKey.get(1),Double.parseDouble(splitKey.get(2)),set,string);
            } catch(NumberFormatException e) {
                throw  new CommandNotFoundException("ERROR: Command not valid");
            }

        } else {
            throw  new CommandNotFoundException("ERROR: Command not valid");
        }
    }
    public String TTL (List<String> splitKey,SetLedis set, StringLedis string) throws CommandNotFoundException {
        if (splitKey.size() == 2) {
            return Expire.TTL(splitKey.get(1), set, string);
        } else {
            throw  new CommandNotFoundException("ERROR: Command not valid");
        }
    }
}
