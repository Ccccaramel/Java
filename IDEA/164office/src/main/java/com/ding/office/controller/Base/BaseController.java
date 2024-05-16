package com.ding.office.controller.Base;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.security.CurrentUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
    protected CurrentUser getCurrentUser(){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (CurrentUser)authentication.getPrincipal();
    }

    protected boolean isLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return true;
        }
        return false;
    }

    protected Integer getUserId(){
        return getCurrentUser().getUser().getId();
    }

    protected String getUserName(){
        return getCurrentUser().getUser().getName();
    }

    protected boolean isRoot(){
        return getCurrentUser().getUser().getRole().equals(DictionaryCode.USER_ROLE_1);
    }
}
