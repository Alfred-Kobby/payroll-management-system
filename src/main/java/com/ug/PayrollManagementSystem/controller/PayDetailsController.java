package com.ug.PayrollManagementSystem.controller;


import com.ug.PayrollManagementSystem.model.PayDetailRequest;
import com.ug.PayrollManagementSystem.service.PayDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class PayDetailsController {

    @Autowired
    PayDetailsService payDetailsService;

    // list all pay_details
    @GetMapping("/pay-details")
    public String listPayDetails(@RequestParam Integer employeeNo, Model model) {
        model.addAttribute("pay_details", payDetailsService.getAllPayDetails(employeeNo));
        return "pay_details";
    }

    // add a new pay_details
    @GetMapping("/pay-detail/add/{employeeNo}")
    public String createPayDetailForm(@PathVariable Integer employeeNo, Model model){

        model.addAttribute("pay_types", payDetailsService.getAllPayType());
        model.addAttribute("employeeNo", employeeNo);

        return "create_pay_detail";
    }


    // save new pay_details
    @PostMapping("/add/pay-detail")
    public String saveNewPayDetail(@ModelAttribute("pay_detail") PayDetailRequest payDetailRequest){
        log.info("Request to add new Pay Detail; {}", payDetailRequest);
        payDetailsService.savePayDetails(payDetailRequest);
        return "redirect:/pay-details?employeeNo=".concat(String.valueOf(payDetailRequest.getEmployeeNo()));
    }

}
