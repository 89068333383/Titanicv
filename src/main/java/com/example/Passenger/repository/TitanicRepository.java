package com.example.Passenger.repository;

import com.example.Passenger.models.FindStat;
import com.example.Passenger.models.Titanic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TitanicRepository extends JpaRepository<Titanic, Integer> {

    // запрос по фильтрации

    @Query(" select t from Titanic t where ( (((t.survived=true)and(?1=1))or((t.survived=false)and(?1=2))or(?1=0))\n" +
            "                                    and (((?2=1)and(t.age>=1600))or((?2=2)and(t.age<1600))or(?2=0))\n" +
            "                                    and (((?3=1)and(t.sex='male'))or((?3=2)and(t.sex='female'))or(?3=0))\n" +
            "                                    and (((?4=1)and(t.siblings>=1))or((?4=2)and(t.siblings=0))or(?4=0))) order by (((t.age)*(?5)+(t.fare)*(?6))), t.name \n" +
            "    ")
    List<Titanic> findRequest(int _survived, int _age,  int _sex, int _siblings, int sortByAge, int sortByFare,int sortByName, Pageable pageable);

    // запрос для сбора стат.данных - сумма стоимости билетов из отфильтрованных данных

    @Query(" select sum(t.fare) from Titanic t where ( (((t.survived=true)and(?1=1))or((t.survived=false)and(?1=2))or(?1=0))\n" +
            "                                    and (((?2=1)and(t.age>=1600))or((?2=2)and(t.age<1600))or(?2=0))\n" +
            "                                    and (((?3=1)and(t.sex='male'))or((?3=2)and(t.sex='female'))or(?3=0))\n" +
            "                                    and (((?4=1)and(t.siblings>=1))or((?4=2)and(t.siblings=0))or(?4=0))\n" +
            "    )")
    Integer findStatFare(int _survived, int _age, int _sex, int _siblings);

    // запрос для сбора стат.данных - количество выживших из отфильтрованных данных


    @Query(" select count(*) from Titanic t where ( (((t.survived=true)and(?1=1))or((t.survived=false)and(?1=2))or(?1=0))\n" +
            "                                    and (((?2=1)and(t.age>=1600))or((?2=2)and(t.age<1600))or(?2=0))\n" +
            "                                    and (((?3=1)and(t.sex='male'))or((?3=2)and(t.sex='female'))or(?3=0))\n" +
            "                                    and (((?4=1)and(t.siblings>=1))or((?4=2)and(t.siblings=0))or(?4=0))\n" +
            "    and(t.survived=true))")
   Integer findStatSurvived(int _survived, int _age, int _sex, int _siblings);

    // запрос для сбора стат.данных - количество пассажиров, имеющих родственников из отфильтрованных данных


    @Query(" select count(*) from Titanic t where ( (((t.survived=true)and(?1=1))or((t.survived=false)and(?1=2))or(?1=0))\n" +
            "                                    and (((?2=1)and(t.age>=1600))or((?2=2)and(t.age<1600))or(?2=0))\n" +
            "                                    and (((?3=1)and(t.sex='male'))or((?3=2)and(t.sex='female'))or(?3=0))\n" +
            "                                    and (((?4=1)and(t.siblings>=1))or((?4=2)and(t.siblings=0))or(?4=0))\n" +
            "    and(t.siblings>0))")
   Integer findStatSiblings(int _survived, int _age, int _sex, int _siblings);

    //запрос на поиск по части имени

    @Query("select t from Titanic t where t.name ilike ?1")
    List<Titanic> findByNameContains(String name);


}