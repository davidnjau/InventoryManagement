package com.dave.inventorymanagement.controller;

import com.dave.inventorymanagement.entity.leave_manegement.LeaveResponse;
import com.dave.inventorymanagement.entity.leave_manegement.Leaves;
import com.dave.inventorymanagement.entity.leave_manegement.TotalLeaves;
import com.dave.inventorymanagement.service.implementation.LeaveManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
