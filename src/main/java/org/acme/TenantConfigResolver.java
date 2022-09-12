package org.acme;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.oidc.OidcRequestContext;
import io.quarkus.oidc.OidcTenantConfig;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TenantConfigResolver implements io.quarkus.oidc.TenantConfigResolver {

    private static final String TENANT_ID_HEADER = "tenant-id";

    @Inject
    ReactiveMongoClient reactiveMongoClient;

    @Override
    public Uni<OidcTenantConfig> resolve(RoutingContext routingContext, OidcRequestContext<OidcTenantConfig> requestContext) {
        String tenantName = routingContext.request().headers().get(TENANT_ID_HEADER);
        return getTenantConfig(tenantName);
    }

    Uni<OidcTenantConfig> getTenantConfig(String tenantName) {
       return reactiveMongoClient.getDatabase("testDB").createCollection("testColl").map( v -> null);
    }
}