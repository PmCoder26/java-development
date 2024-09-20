package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.utils;

import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.enums.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.enums.Permission.*;
import static com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.enums.Role.*;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionMapping {

    private static final Map<Role, Set<Permission>> map = Map.of(
            ADMIN, Set.of(DEPARTMENT_CREATE, DEPARTMENT_UPDATE, DEPARTMENT_DELETE, DEPARTMENT_VIEW, EMPLOYEE_CREATE, EMPLOYEE_DELETE, EMPLOYEE_VIEW, ALL_DEPARTMENTS_VIEW, ALL_EMPLOYEE_VIEW),
            USER, Set.of(DEPARTMENT_VIEW, EMPLOYEE_VIEW, EMPLOYEE_CREATE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role){
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet()); 
    }

}
