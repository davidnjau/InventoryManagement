package com.dave.inventorymanagement.repository.leave_management;

import com.dave.inventorymanagement.entity.leave_manegement.TotalLeaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalLeavesRepository extends JpaRepository<TotalLeaves, Long> {


}
