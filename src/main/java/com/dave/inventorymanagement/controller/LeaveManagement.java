package com.dave.inventorymanagement.controller;

import com.dave.inventorymanagement.entity.leave_manegement.LeaveResponse;
import com.dave.inventorymanagement.entity.leave_manegement.Leaves;
import com.dave.inventorymanagement.entity.leave_manegement.TotalLeaves;
import com.dave.inventorymanagement.service.implementation.LeaveManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveManagement {

    @Autowired
    private LeaveManagementServiceImpl leaveManagementService;

    @RequestMapping(value = "/api/v1/leave_management/create_request", method = RequestMethod.POST)
    public Leaves createRequest(@RequestBody Leaves leaves) {
        return leaveManagementService.createLeave(leaves);
    }

    @RequestMapping(value = "/api/v1/leave_management/add_leaves", method = RequestMethod.POST)
    public TotalLeaves addLeaves(@RequestBody TotalLeaves totalLeaves) {
        return leaveManagementService.addLeaves(totalLeaves);
    }

    @RequestMapping(value = "/api/v1/leave_management/save_leave_response", method = RequestMethod.POST)
    public LeaveResponse createLeaveResponse(@RequestBody LeaveResponse leaveResponse) {
        return leaveManagementService.saveResponse(leaveResponse);
    }

    //Web Controller
    @GetMapping("/")
    public ModelAndView index(Model model){
        List<Leaves> leavesList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("leavesList", leavesList);

        return new ModelAndView("index",params );
    }

    @GetMapping("/leave_requests")
    public ModelAndView showRequests(){
        List<Leaves> leavesList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("leavesList", leavesList);

        return new ModelAndView("leave_requests",params );
    }

}
