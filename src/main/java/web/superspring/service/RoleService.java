package web.superspring.service;

import web.superspring.model.Role;
import java.util.List;

public interface RoleService {
    void saveRole(Role role);
    List<Role> getAllRoles();
}
