package com.example.Passenger.service.impl;

import com.example.Passenger.models.FindStat;
import com.example.Passenger.models.SortParam;
import com.example.Passenger.models.Titanic;
import com.example.Passenger.repository.TitanicRepository;
import com.example.Passenger.service.PasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PasServiceImpl implements PasService {

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

        if ((sortParam.getSizeBegin()==-1)&(sortParam.getNumBegin()==0)){
            sortParam.setNumBegin(0);
        }else sortParam.setNumBegin(sortParam.getNumBegin() + sortParam.getSizeBegin());;

        Pageable pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger());
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
}
