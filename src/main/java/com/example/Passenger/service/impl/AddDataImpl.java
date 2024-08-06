package com.example.Passenger.service.impl;

import com.example.Passenger.models.Pclass;
import com.example.Passenger.models.Titanic;
import com.example.Passenger.service.AddData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.springframework.cglib.beans.BeanMap.create;

public class AddDataImpl implements AddData {

    @Override
    public boolean addData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("titanic.csv")));
            String fileContent;
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            int count = 0;
            while (line != null) {
                Titanic titanic = new Titanic();
                String[] split = line.split(",");
                boolean _survived = (Integer.parseInt(split[0])==1)? true:false;
                titanic.setSurvived(_survived);
                var _pclass = Pclass.setTitle(Integer.parseInt(split[1]));
                titanic.setPclass(_pclass);
                titanic.setName(split[2]);
                titanic.setSex(split[3]);
                titanic.setAge((int)(Double.valueOf(split[4])*100)); //года в сотнях
                titanic.setSiblings(Integer.valueOf(split[5]));
                titanic.setParents(Integer.valueOf(split[6]));
                titanic.setFare(((int) (Double.valueOf(split[7])*1000)));//стоимость в $*1000
                create(titanic);
                line = bufferedReader.readLine();
                count++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
