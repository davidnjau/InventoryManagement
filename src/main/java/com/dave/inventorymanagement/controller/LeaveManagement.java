package com.dave.inventorymanagement.controller;

import com.dave.inventorymanagement.entity.leave_manegement.LeaveResponse;
import com.dave.inventorymanagement.entity.leave_manegement.Leaves;
import com.dave.inventorymanagement.entity.leave_manegement.TotalLeaves;
import com.dave.inventorymanagement.helper_class.LeaveDetails;
import com.dave.inventorymanagement.service.implementation.LeaveManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/api/v1/leave_management/leaveDetails/{leave_id}", method = RequestMethod.GET)
    public LeaveDetails findLeaveDetails(@PathVariable("leave_id") Long leave_id){
        return leaveManagementService.getLeaves(leave_id);
    }



    //Web Controller
    @GetMapping("/all_leaves_requests")
    public ModelAndView index(@RequestParam(defaultValue = "0") int pageNo){

        int pageSize = 5;

        ModelAndView modelAndView = new ModelAndView("leave_management/index");
        List<Leaves> leavesList = leaveManagementService.getAllLeaveRequest(pageNo, pageSize);
        List<Leaves> leavesListNoPage = leaveManagementService.getAllLeaveRequestWithoutPagination();

        int totalPages = leavesListNoPage.size() / pageSize;

        modelAndView.addObject("leavesList", leavesList);
        modelAndView.addObject("currentPage", totalPages);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("totalItems", leavesListNoPage.size());

        return modelAndView;
    }

    @GetMapping("/leave_requests")
    public ModelAndView showRequests(){
        List<Leaves> leavesList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("leavesList", leavesList);

        return new ModelAndView("leave_management/leave_requests",params );
    }

    @GetMapping("/make_leave_request")
    public ModelAndView makeRequests(){
        List<Leaves> leavesList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("leavesList", leavesList);

        return new ModelAndView("leave_management/make_leave_request",params );
    }

}
