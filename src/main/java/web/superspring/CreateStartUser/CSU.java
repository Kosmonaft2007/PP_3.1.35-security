package web.superspring.CreateStartUser;

import web.superspring.model.Role;
import web.superspring.model.User;
import web.superspring.service.RoleServiceImpl;
import web.superspring.service.UserService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class CSU {
    private final UserService userService;
    private final RoleServiceImpl roleService;
    public CSU(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    private void dataBaseInit() {
        Set<Role> adminRole = new HashSet<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        adminRole.add(roleAdmin);
        adminRole.add(roleUser);
        Set<Role> userRole = new HashSet<>();
        userRole.add(roleUser);
        User admin = new User("admin", "admin", 25, "admin@admin.ru", "admin", adminRole);
        User user = new User("user", "user", 27, "user@user.ru", "user", userRole);
        userService.add(admin);
        userService.add(user);
   }
}