package com.dave.inventorymanagement.repository.leave_management;

import com.dave.inventorymanagement.entity.leave_manegement.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves, Long> {
}
