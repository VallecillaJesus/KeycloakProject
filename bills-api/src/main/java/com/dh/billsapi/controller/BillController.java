package com.dh.billsapi.controller;


import com.dh.billsapi.models.Bill;
import com.dh.billsapi.repositories.feign.UserFeignRepository;
import com.dh.billsapi.service.BillService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

  private final BillService service;
  private final UserFeignRepository feignUserRepository;

  @GetMapping("/all")
  public ResponseEntity<List<Bill>> getAll(@AuthenticationPrincipal Jwt jwt) {
    return ResponseEntity.ok().body(service.getAllBill(jwt));
  }

  @PostMapping()
  @PreAuthorize("hasAuthority('GROUP_provider')")
  public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
    return ResponseEntity.ok().body(service.saveBill(bill));
  }

  @GetMapping("/findBy")
  public ResponseEntity<Bill> findByCustomer(@RequestParam String customer) {
    Bill bill = service.findByCustomer(customer);
    if (bill != null) {
      return ResponseEntity.ok().body(bill);
    }
    return ResponseEntity.notFound().build();
  }
}
