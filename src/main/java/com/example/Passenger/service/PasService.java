package com.example.Passenger.service;

import com.example.Passenger.models.FindStat;
import com.example.Passenger.models.SortParam;
import com.example.Passenger.models.Titanic;
import com.example.Passenger.repository.TitanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


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

}
