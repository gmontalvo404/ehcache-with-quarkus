package com.gmontalvo404;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;

public class CacheHelper {
    private static final String ALIAS_FACTORIAL = "factorial";
    private final Cache<Long, Long> factorialCache;

    public CacheHelper() {
        String path = System.getProperty("user.dir");
        PersistentCacheManager persistentCacheManager =
                CacheManagerBuilder.newCacheManagerBuilder()
                        .with(CacheManagerBuilder.persistence(path + File.separator + "cache-folder"))
                        .withCache(ALIAS_FACTORIAL, CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(Long.class, Long.class,
                                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                                .heap(10, EntryUnit.ENTRIES)
                                                .disk(10, MemoryUnit.MB, true))
                        )
                        .build(true);
        factorialCache = persistentCacheManager.getCache(ALIAS_FACTORIAL, Long.class, Long.class);
    }

    public Cache<Long, Long> getFactorialCache() {
        return factorialCache;
    }
}
