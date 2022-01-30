package com.example.ledisapp.service;

import com.example.ledisapp.datastructure.SetLedis;
import com.example.ledisapp.datastructure.StringLedis;
import com.example.ledisapp.exception.ArgumentNotValidException;
import com.example.ledisapp.exception.KeyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StringService {


    public String SET(List<String> splitKey, StringLedis string) throws ArgumentNotValidException {
        if(splitKey.size()!=3) {
            throw new ArgumentNotValidException("ERROR: The argument not valid") ;
        } else {
            return string.SET(splitKey.get(1),splitKey.get(2));

        }
    }

    public String GET(List<String> splitKey, StringLedis string) throws ArgumentNotValidException, KeyNotFoundException {
        if(splitKey.size()!=2) {
            throw new ArgumentNotValidException("ERROR: The argument not valid") ;
        } else {
            return string.GET(splitKey.get(1));
        }
    }
}
