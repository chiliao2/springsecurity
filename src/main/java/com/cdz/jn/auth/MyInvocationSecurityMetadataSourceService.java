package com.cdz.jn.auth;

import com.cdz.jn.entity.Permission;
import com.cdz.jn.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionRepository permissionRepository;
    private HashMap<String, Collection<ConfigAttribute>> map;

    private void loadMap() {
        map = new HashMap<String, Collection<ConfigAttribute>>();
        List<Permission> permissions = permissionRepository.findAll();
        if (permissions != null) {
            for (Permission p : permissions) {
                Collection<ConfigAttribute> cas = new ArrayList<ConfigAttribute>();
                ConfigAttribute ca = new SecurityConfig(p.getName());
                cas.add(ca);
                map.put(p.getUrl(), cas);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadMap();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
