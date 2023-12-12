package com.ug.PayrollManagementSystem.controller;


import com.ug.PayrollManagementSystem.entity.SickLeave;
import com.ug.PayrollManagementSystem.model.SickLeaveRequest;
import com.ug.PayrollManagementSystem.service.SickLeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@Slf4j
public class SickLeaveController {

    @Autowired
    SickLeaveService sickLeaveService;

    // list all sick leaves
    @GetMapping("/sick-leaves")
    public String listSickLeave(@RequestParam Integer employeeNo, Model model) {
        model.addAttribute("sick_leaves", sickLeaveService.getAllSickLeaves(employeeNo));
        return "sick_leaves";
    }

    // add a new sick leave
    @GetMapping("/sick-leave/add/{employeeNo}")
    public String createSickLeaveForm(@PathVariable Integer employeeNo, Model model){
        model.addAttribute("employeeNo", employeeNo);

        return "create_sick_leave";
    }


    // save new sick leave
    @PostMapping("/add/sick-leave")
    public String saveNewSickLeave(@ModelAttribute("sick_leave") SickLeaveRequest sickLeaveRequest){
        log.info("Request to add new Sick leave record {}", sickLeaveRequest);
        LocalDate startDate = LocalDate.parse(sickLeaveRequest.getStartDate());
        LocalDate endDate = LocalDate.parse(sickLeaveRequest.getEndDate());
        SickLeave sickLeave = new SickLeave(sickLeaveRequest.getEmployeeNo(), startDate, endDate, sickLeaveRequest.getReason());
        sickLeaveService.saveSickLeave(sickLeave);
        return "redirect:/sick-leaves?employeeNo=".concat(String.valueOf(sickLeaveRequest.getEmployeeNo()));
    }

    @GetMapping("/sick-leave/delete/{employeeNo}/{startDate}")
    public String deleteSickLeave(@PathVariable Integer employeeNo, @PathVariable String startDate,  Model model){
        LocalDate date = LocalDate.parse(startDate);
        sickLeaveService.deleteSickLeave(employeeNo, date);
        return "redirect:/sick-leaves?employeeNo=".concat(String.valueOf(employeeNo));
    }

}
