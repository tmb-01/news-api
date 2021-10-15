package uz.pdp.appnewssite.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.RoleNames;
import uz.pdp.appnewssite.exception.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean exist = false;
//        for (RoleNames roleName :
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.role())) {
                exist = true;
                break;
            }
        }

        if (!exist) {
            throw new ForbiddenException(checkPermission.role(), "Forbidden");
        }

    }
}
