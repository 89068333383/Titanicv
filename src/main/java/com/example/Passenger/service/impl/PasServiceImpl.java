package com.example.Passenger.service.impl;

import com.example.Passenger.models.FindStat;
import com.example.Passenger.models.SortParam;
import com.example.Passenger.models.Titanic;
import com.example.Passenger.repository.TitanicRepository;
import com.example.Passenger.service.PasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PasServiceImpl implements PasService {

    private int numPage;
    public void setNumPage(final int numPage) {
        this.numPage = numPage;
    }
    public int getNumPage() {
        return numPage;
    }

    private TitanicRepository titanicRepository;

    @Autowired
    public void setTitanicRepository(TitanicRepository titanicRepository){
        this.titanicRepository = titanicRepository;
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
    public List<Titanic> findByName(SortParam sortParam){
        return titanicRepository.findByNameContains('%'+sortParam.getName()+'%');
    }
    public List<Titanic> findBySort(SortParam sortParam){

        if ((sortParam.getSizeBegin()==-1)&&(sortParam.getNumBegin()==0)){
            sortParam.setNumBegin(0);
            setNumPage(0);
        }else {
            setNumPage(getNumPage()+sortParam.getSizeBegin());
            sortParam.setNumBegin(getNumPage());
        }
        Pageable pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger(), Sort.by("name"));


        switch (sortParam.getSortBy()){
            case(1): {
                pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger(), Sort.by("name"));
                break;
            }
            case(2): {
                pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger(), Sort.by("name").descending());
                break;
            }
            case(3): {
                pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger(), Sort.by("age"));
                break;
            }
            case(4): {
                pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger(), Sort.by("age").descending());
                break;
            }
            case(5): {
                pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger(), Sort.by("fare"));
                break;
            }
            case(6): {
                pageable = PageRequest.of(sortParam.getNumBegin(),sortParam.getCountPassenger(), Sort.by("fare").descending());
                break;
            }
        }

        return titanicRepository.queryBy(sortParam.getSurvived(), sortParam.getAge(), sortParam.getSex(),sortParam.getSiblings(), pageable);
    }
}
