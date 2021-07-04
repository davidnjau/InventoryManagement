package com.dave.inventorymanagement.service.service_manager;

import com.dave.inventorymanagement.entity.leave_manegement.LeaveResponse;
import com.dave.inventorymanagement.entity.leave_manegement.Leaves;
import com.dave.inventorymanagement.entity.leave_manegement.TotalLeaves;

public interface LeaveManagementService {

    Leaves createLeave(Leaves leaves);
    LeaveResponse saveResponse(LeaveResponse leaveResponse);
    TotalLeaves addLeaves(TotalLeaves totalLeaves);
    Leaves getLeaves(Long id);


}
