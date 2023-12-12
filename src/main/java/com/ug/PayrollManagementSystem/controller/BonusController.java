package com.ug.PayrollManagementSystem.controller;


import com.ug.PayrollManagementSystem.entity.Bonus;
import com.ug.PayrollManagementSystem.model.BonusRequest;
import com.ug.PayrollManagementSystem.model.PayDetailRequest;
import com.ug.PayrollManagementSystem.service.BonusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@Slf4j
public class BonusController {

    @Autowired
    BonusService bonusService;

    // list all bonus
    @GetMapping("/bonuses")
    public String listBonuses(@RequestParam Integer employeeNo, Model model) {
        model.addAttribute("bonuses", bonusService.getAllBonuses(employeeNo));
        return "bonuses";
    }

    // add a new bonus
    @GetMapping("/bonus/add/{employeeNo}")
    public String createBonusForm(@PathVariable Integer employeeNo, Model model){

        model.addAttribute("bonus_types", bonusService.getAllBonusType());
        model.addAttribute("employeeNo", employeeNo);

        return "create_bonus";
    }


    // save new bonus
    @PostMapping("/add/bonus")
    public String saveNewBonus(@ModelAttribute("bonus") BonusRequest bonusRequest){
        log.info("Request to add new Bonus record {}", bonusRequest);
        LocalDate bonusDate = LocalDate.parse(bonusRequest.getBonusDate());
        Bonus bonus = new Bonus(bonusRequest, bonusDate);
        bonusService.saveBonus(bonus);
        return "redirect:/bonuses?employeeNo=".concat(String.valueOf(bonusRequest.getEmployeeNo()));
    }

    @GetMapping("/bonus/edit/{employeeNo}/{bonusDate}")
    public String createEditBonusForm(@PathVariable Integer employeeNo, @PathVariable String bonusDate,  Model model){
        LocalDate date = LocalDate.parse(bonusDate);
        model.addAttribute("bonus", bonusService.findBonus(employeeNo,date));
        model.addAttribute("bonus_types", bonusService.getAllBonusType());
        model.addAttribute("employeeNo", employeeNo);
        return "edit_bonus";
    }

    @PostMapping("/update/bonus/{employeeNo}/{bonusDate}")
    public String updateBonus(@PathVariable Integer employeeNo, @PathVariable String bonusDate, @ModelAttribute("bonus") BonusRequest bonusRequest, Model model){
        log.info("Request to update Bonus {}", bonusRequest);
        bonusService.updateBonus(bonusRequest, employeeNo, bonusDate);
        model.addAttribute("bonuses", bonusService.getAllBonuses(employeeNo));
        return "bonuses";
    }

}
