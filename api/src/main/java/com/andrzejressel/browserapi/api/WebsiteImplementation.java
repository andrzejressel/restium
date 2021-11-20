package com.andrzejressel.browserapi.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public interface WebsiteImplementation<CONFIGURATION> {
    @Nonnull
    CompletableFuture<Void> prepare(@Nonnull Browser browser);

    @Nonnull
    Class<CONFIGURATION> getConfigurationClass();

    @Nonnull
    CompletableFuture<Void> setConfiguration(@Nonnull CONFIGURATION configuration);

    @Nonnull
    CompletableFuture<Object> invokeMethod(@Nonnull Browser browser, @Nonnull Object methodId, @Nullable byte[] body);
}
