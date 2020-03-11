package com.hendisantika.springbootmultitenancysample.service.aspect;

import com.hendisantika.springbootmultitenancysample.filter.TenantContext;
import com.hendisantika.springbootmultitenancysample.service.EmployeeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-multi-tenancy-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/03/20
 * Time: 06.34
 */
@Aspect
@Component
public class EmployeeServiceAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // only applicable to employee service
    @Before("execution(* com.hendisantika.springbootmultitenancysample.service.EmployeeService.*(..)) && !execution(*" +
            " com.hendisantika.springbootmultitenancysample.service.EmployeeService.run(..)) && target" +
            "(employeeService)")
    public void aroundExecution(JoinPoint pjp, EmployeeService employeeService) throws Throwable {
        org.hibernate.Filter filter = employeeService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}