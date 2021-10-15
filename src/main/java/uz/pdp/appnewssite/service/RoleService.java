package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RoleDto;
import uz.pdp.appnewssite.payload.UserDto;
import uz.pdp.appnewssite.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {

        if (roleRepository.existsByName(roleDto.getName())) {
            return new ApiResponse("This role is already exist", false);
        } else {
            Role role = new Role();
            role.setName(roleDto.getName());
            role.setRoleNames(roleDto.getRoleNames());
            role.setDescription(roleDto.getDescription());

            roleRepository.save(role);
            return new ApiResponse("Role saved", true);
        }

    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {
        return new ApiResponse("edited role", true);
    }
}
