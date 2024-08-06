package com.example.Passenger.controller;

import com.example.Passenger.models.SortParam;
import com.example.Passenger.service.AddData;
import com.example.Passenger.service.PasService;
import com.example.Passenger.service.impl.PasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasController {

    private AddData servisAdd;
    public void setServisAdd(AddData servisAdd) {this.servisAdd = servisAdd;}

    private PasService service;
    @Autowired
    public void setService(PasServiceImpl service){
        this.service = service;
    }
    // Метод обработки GET сообщений со страницы

    @GetMapping("/passenger")
    public String index(@ModelAttribute SortParam sortParam, Model model){
        if (sortParam.getName()!=null) {
            model.addAttribute("passengers", service.FindByName(sortParam));
        }else {
            if (sortParam.getCountPassenger() == 0) sortParam.setCountPassenger(50);
            model.addAttribute("passengers", service.findRequest(sortParam));
        }
        model.addAttribute("repStat", service.findStat(sortParam));
        return "index";
    }

    @GetMapping("/passenger/AddData")
    public String addDataPassengers(){
        servisAdd.addData();
        return "index";
    }

}
