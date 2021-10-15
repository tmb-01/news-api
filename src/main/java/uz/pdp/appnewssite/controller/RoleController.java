package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.aop.CheckPermission;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RoleDto;
import uz.pdp.appnewssite.payload.UserDto;
import uz.pdp.appnewssite.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLES')")
    @PostMapping
    public HttpEntity<?> registerUser(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_ROLES')")
    @CheckPermission(role = "EDIT_ROLES")
    @PutMapping("{id}")
    public HttpEntity<?> registerUser(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.editRole(id,roleDto);
//        ResponseEntity<>( new ApiResponse() , HttpStatus.NOT_FOUND);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
