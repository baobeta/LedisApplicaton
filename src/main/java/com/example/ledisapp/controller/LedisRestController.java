package com.example.ledisapp.controller;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.datastructure.StringLedis;
import com.example.ledisapp.exception.ArgumentNotValidException;
import com.example.ledisapp.exception.CommandNotFoundException;
import com.example.ledisapp.exception.KeyNotFoundException;
import com.example.ledisapp.service.ExpireService;
import com.example.ledisapp.service.SetService;
import com.example.ledisapp.service.SnapshotService;
import com.example.ledisapp.service.StringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LedisRestController {

    @Autowired
    private  SetLedis set ;

    @Autowired
    private  StringLedis string ;

    @Autowired
    private StringService stringService;

    @Autowired
    private SetService setService;

    @Autowired
    private ExpireService expireService;

    @Autowired
    private SnapshotService snapshotService;

    @PostMapping("/sendkey")
    public String returnValue(@Param("key") String key) {

        HashSet<String> commandLine = buildValueCommand();
        List<String> splitKey = Arrays.asList(key.split(" "));

        // check command valid
        if(!commandLine.contains(splitKey.get(0))) {
            return "ERROR: Not found command ";
        } else {
            try {
                switch (splitKey.get(0)) {
                    case "SET":
                        return stringService.SET(splitKey, string);
                    case "GET":
                        return stringService.GET(splitKey, string);
                    case "SADD":
                        return setService.SADD(splitKey, set);
                    case "SREM":
                        return setService.SREM(splitKey, set);
                    case "SMEMBERS":
                        return setService.SMEMBERS(splitKey, set);
                    case "SINTER":
                        return setService.SINTER(splitKey, set);
                    case "KEYS":
                        return expireService.KEYS(splitKey, set, string);
                    case "DEL":
                        return expireService.DEL(splitKey, set, string);
                    case "EXPIRE":
                        return expireService.EXPIRE(splitKey, set, string);
                    case "TTL":
                        return expireService.TTL(splitKey, set, string);
                    case "SAVE":
                        return snapshotService.SAVE(splitKey, set, string);
                    case "RESTORE":
                        return snapshotService.RESTORE(splitKey, set, string);
                    default:
                        throw new CommandNotFoundException("ERROR: Command not valid");
                }
            } catch(CommandNotFoundException | ArgumentNotValidException | KeyNotFoundException e) {
                return e.getMessage();
            }


        }
    }

    public HashSet<String>  buildValueCommand() {
        HashSet<String>  value = new HashSet<>();
        value.add("GET");
        value.add("SET");
        value.add("SADD");
        value.add("SREM");
        value.add("SMEMBERS");
        value.add("SINTER");
        value.add("KEYS");
        value.add("DEL");
        value.add("EXPIRE");
        value.add("TTL");
        value.add("SAVE");
        value.add("RESTORE");

        return value;

    }
}
