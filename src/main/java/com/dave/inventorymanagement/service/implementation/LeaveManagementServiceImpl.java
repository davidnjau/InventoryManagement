package com.dave.inventorymanagement.service.implementation;

import com.dave.inventorymanagement.entity.leave_manegement.LeaveResponse;
import com.dave.inventorymanagement.entity.leave_manegement.Leaves;
import com.dave.inventorymanagement.entity.leave_manegement.TotalLeaves;
import com.dave.inventorymanagement.helper_class.LeaveDetails;
import com.dave.inventorymanagement.repository.LeaveResponsesRepository;
import com.dave.inventorymanagement.repository.LeavesRepository;
import com.dave.inventorymanagement.repository.TotalLeavesRepository;
import com.dave.inventorymanagement.service.service_manager.LeaveManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public LeaveDetails getLeaves(Long id) {
        Leaves leaves = new Leaves();
        leaves = leavesRepository.getById(id);

        LeaveDetails leaveDetails = new LeaveDetails();

        leaveDetails.setReasons(leaves.getReason());
        leaveDetails.setFrom_date(leaves.getFrom_date());
        leaveDetails.setTo_date(leaves.getTo_date());
        leaveDetails.setId(leaves.getId());
        leaveDetails.setUser_name(leaves.getUser_id());
        leaveDetails.setUser_department("");

        System.out.println("-*-*-*-"+leaves.getReason());

        return leaveDetails;


    }

    public List<Leaves> getAllLeaveRequest(int pageNo, int pageSize){

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Leaves> pagedResult = leavesRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Leaves>();
        }


    }

    public List<Leaves> getAllLeaveRequestWithoutPagination(){
        return leavesRepository.findAll();
    }


}
