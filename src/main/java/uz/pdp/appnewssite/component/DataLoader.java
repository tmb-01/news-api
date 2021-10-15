package uz.pdp.appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.RoleNames;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.appnewssite.entity.enums.RoleNames.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Value("${spring.datasource.initialization-mode}")
    private String initialModeType;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (initialModeType.equals("always")) {

            RoleNames[] roleNames = RoleNames.values();
            Role admin = roleRepository.save(new Role(AppConstants.ADMIN, Arrays.asList(roleNames), "admin of system"));
            Role user = roleRepository.save(new Role(AppConstants.USER, Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT), "simple user"));


            User adminUser = new User();
            adminUser.setFullName("Admin");
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRoles(admin);
            adminUser.setEnabled(true);

            userRepository.save(adminUser);

            User simpleUser = new User();
            simpleUser.setFullName("User");
            simpleUser.setUsername("user");
            simpleUser.setPassword(passwordEncoder.encode("user123"));
            simpleUser.setRoles(user);
            simpleUser.setEnabled(true);

            userRepository.save(simpleUser);

            System.out.println("Program started running");
//        System.out.println(args.toString());
        }
    }
}
