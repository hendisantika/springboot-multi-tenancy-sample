package com.hendisantika.springbootmultitenancysample.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-multi-tenancy-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/03/20
 * Time: 06.26
 */
@Component
public class TenantFilter implements Filter {

    private static final String TENANT_HEADER = "X-TenantID";

}
