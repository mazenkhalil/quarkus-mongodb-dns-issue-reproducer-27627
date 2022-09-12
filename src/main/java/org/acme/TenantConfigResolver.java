package org.acme;

import io.quarkus.cache.CacheResult;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.oidc.OidcRequestContext;
import io.quarkus.oidc.OidcTenantConfig;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
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
        return getTenantConfig(tenantName == null ? "DEFAULT_TENANT" : tenantName);
//            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @CacheResult(cacheName = "tenant-cache")
    Uni<OidcTenantConfig> getTenantConfig(String tenantName) {
        return reactiveMongoClient.getDatabase("testDB").listCollectionNames().toUni().map(v -> null);
    }
}