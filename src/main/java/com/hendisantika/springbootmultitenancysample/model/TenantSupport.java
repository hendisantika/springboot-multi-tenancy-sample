package com.hendisantika.springbootmultitenancysample.model;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-multi-tenancy-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/03/20
 * Time: 06.25
 */
public interface TenantSupport {
    String getTenantId();

    void setTenantId(String tenantId);
}
