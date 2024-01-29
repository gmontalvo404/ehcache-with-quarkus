package com.gmontalvo404;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


@Singleton
public class FactorialService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactorialService.class.getName());

    private final CacheHelper cacheHelper;

    public FactorialService() {
        this.cacheHelper = new CacheHelper();
    }

    public Long getFactorialWithCache(Long number) {
        boolean containsFactorial = cacheHelper.getFactorialCache().containsKey(number);
        if(containsFactorial) {
            Long factorial = cacheHelper.getFactorialCache().get(number);
            LOGGER.info("Factorial with cache: {}! = {}", number, factorial);
            return factorial;
        }

        Long factorial = getFactorial(number);
        cacheHelper.getFactorialCache().put(number, factorial);
        LOGGER.info("Factorial without cache: {}! = {}", number, factorial);
        return factorial;
    }

    public Long getFactorial(Long number) {
        if(number < 2) return 1L;

        return number * getFactorial(number - 1);
    }
}
