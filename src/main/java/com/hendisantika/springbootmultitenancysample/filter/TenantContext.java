package com.hendisantika.springbootmultitenancysample.filter;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-multi-tenancy-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/03/20
 * Time: 06.28
 */
public class TenantContext {
    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }
}
