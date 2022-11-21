package com.dh.billsapi.repositories;

import com.dh.billsapi.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, String> {

  Optional<Bill> findByCustomerBill(String customer);
}
