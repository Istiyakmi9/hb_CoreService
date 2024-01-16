package com.bot.coreservice.model;


import com.bot.coreservice.entity.Login;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentSession {
    Date timeZoneNow;
    Login user;

    public Date getTimeZoneNow() {
        return timeZoneNow;
    }

    public void setTimeZoneNow(Date timeZoneNow) {
        this.timeZoneNow = timeZoneNow;
    }

    public Login getEmployee() {
        return user;
    }

    public void setEmployee(Login employee) {
        this.user = employee;
    }
}
