package com.example.Passenger.service;

import com.example.Passenger.models.FindStat;
import com.example.Passenger.models.Pclass;
import com.example.Passenger.models.SortParam;
import com.example.Passenger.models.Titanic;
import com.example.Passenger.repository.TitanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.springframework.cglib.beans.BeanMap.create;


@Service
public class PasService {

    private TitanicRepository titanicRepository;

    @Autowired
    public void setTitanicRepository(TitanicRepository titanicRepository){
        this.titanicRepository = titanicRepository;
    }

    // метод для обработки фильтрации, сортировки и пагинации
    public List<Titanic> findRequest(SortParam sortParam){
        int sortByFare=0;
        int sortByAge=0;
        int sortByName=0;

        if ((sortParam.sizeBegin==-1)&(sortParam.numBegin==0)){
            sortParam.numBegin=0;
        }else sortParam.numBegin = sortParam.numBegin + sortParam.sizeBegin;

        Pageable pageable = PageRequest.of(sortParam.numBegin,sortParam.countPassenger);
        switch (sortParam.getSortBy()){
            case(3): {
                    sortByAge = 1;
                    break;
            }
            case(4): {
                sortByAge = -1;
                break;
            }
            case(5): {
                sortByFare = 1;
                break;
            }
            case(6): {
                sortByFare = -1;
                break;
            }
        }
        return titanicRepository.findRequest(sortParam.getSurvived(), sortParam.getAge(), sortParam.getSex(),sortParam.getSiblings(),sortByAge,sortByFare,sortByName, pageable);
    }

    //метод сбора стат.данных
    public FindStat findStat(SortParam  sortParam){
        FindStat findStat = new FindStat();

        findStat.setFare(titanicRepository.findStatFare(sortParam.getSurvived(), sortParam.getAge(), sortParam.getSex(),sortParam.getSiblings()));
        findStat.setSiblings(titanicRepository.findStatSiblings(sortParam.getSurvived(), sortParam.getAge(), sortParam.getSex(),sortParam.getSiblings()));
        findStat.setSurvived(titanicRepository.findStatSurvived(sortParam.getSurvived(), sortParam.getAge(), sortParam.getSex(),sortParam.getSiblings()));
        return findStat;
    }

    // Метод для поиска по имени
    public List<Titanic> FindByName(SortParam sortParam){
        return titanicRepository.findByNameContains('%'+sortParam.getName()+'%');
    }

    public boolean addData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("titanic.csv")));
            String fileContent;
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            int count = 0;
            while (line != null) {
                Titanic titanic = new Titanic();
//                create(titanic);
//                titanic.setId(1);


                String[] split = line.split(",");
//                titanic.setId(count);
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

                System.out.println(titanic.toString());
                create(titanic);

                System.out.println(titanic.toString());

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
