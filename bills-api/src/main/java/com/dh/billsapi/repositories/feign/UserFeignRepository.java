package com.dh.billsapi.repositories.feign;


import com.dh.billsapi.models.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "users-service")
public interface UserFeignRepository {

    @GetMapping("/users/admin")
    List<UserDTO> getAllUsers();
}
