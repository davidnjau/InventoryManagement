package com.dave.inventorymanagement.service.implementation;

import com.dave.inventorymanagement.entity.leave_manegement.LeaveResponse;
import com.dave.inventorymanagement.entity.leave_manegement.Leaves;
import com.dave.inventorymanagement.entity.leave_manegement.TotalLeaves;
import com.dave.inventorymanagement.repository.LeaveResponsesRepository;
import com.dave.inventorymanagement.repository.LeavesRepository;
import com.dave.inventorymanagement.repository.TotalLeavesRepository;
import com.dave.inventorymanagement.service.service_manager.LeaveManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveManagementServiceImpl implements LeaveManagementService {

    @Autowired
    private LeavesRepository leavesRepository;
    @Autowired
    private LeaveResponsesRepository leaveResponsesRepository;
    @Autowired
    private TotalLeavesRepository totalLeavesRepository;

    @Override
    public Leaves createLeave(Leaves leaves) {

        return leavesRepository.save(leaves);
    }

    @Override
    public LeaveResponse saveResponse(LeaveResponse leaveResponse) {
        return leaveResponsesRepository.save(leaveResponse);
    }

    @Override
    public TotalLeaves addLeaves(TotalLeaves totalLeaves) {
        return totalLeavesRepository.save(totalLeaves);
    }

    @Override
    public Leaves getLeaves(Long id) {
        System.out.println("-*-*-*-*-*"+leavesRepository.getById(id).getReason() );
        return leavesRepository.getById(id);
    }

    public List<Leaves> getAllLeaveRequest(){

        return leavesRepository.findAll();

    }



}
