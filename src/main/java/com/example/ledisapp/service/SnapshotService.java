package com.example.ledisapp.service;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.datastructure.StringLedis;
import com.example.ledisapp.exception.CommandNotFoundException;
import com.example.ledisapp.utils.Snapshots;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnapshotService {
    public String SAVE(List<String> splitKey, SetLedis set, StringLedis string) throws CommandNotFoundException {
        if(splitKey.size()==1) {
            return Snapshots.SAVE(set,string);
        } else {
            throw  new CommandNotFoundException("ERROR: Command not valid");
        }
    }
    public String RESTORE(List<String> splitKey, SetLedis set, StringLedis string) throws CommandNotFoundException {
        if(splitKey.size()==1) {
            return Snapshots.RESTORE(set,string);
        } else {
            throw  new CommandNotFoundException("ERROR: Command not valid");
        }
    }
}
