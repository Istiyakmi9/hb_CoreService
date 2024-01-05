package com.hiringbell.hbserver.model;


import com.hiringbell.hbserver.entity.Employee;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;
import java.util.TimeZone;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentSession {
    Date timeZoneNow;
    Employee employee;

    public Date getTimeZoneNow() {
        return timeZoneNow;
    }

    public void setTimeZoneNow(Date timeZoneNow) {
        this.timeZoneNow = timeZoneNow;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
