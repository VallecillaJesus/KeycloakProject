package com.dh.billsapi.service;

import com.dh.billsapi.models.Bill;
import com.dh.billsapi.models.dto.UserDTO;
import com.dh.billsapi.repositories.BillRepository;
import com.dh.billsapi.repositories.feign.UserFeignRepository;
import com.thoughtworks.xstream.security.ForbiddenClassException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

  private final BillRepository repository;
  private final UserFeignRepository userFeignRepository;

  public List<Bill> getAllBill(Jwt jwt) {
    String currentUsername = jwt.getClaims().get("username").toString();
    try{
      for ( UserDTO user : this.userFeignRepository.getAllUsers()){
        if(List.of(user.getUsername(), "admin").contains(currentUsername)){
          return repository.findAll();
        }
      }
    }catch (Exception e){}
    return new ArrayList<>();
  }

  public Bill saveBill(Bill bill) {
    return repository.save(bill);
  }

  public Bill findByCustomer(String customer) {
    return repository.findByCustomerBill(customer).orElse(null);
  }

}
