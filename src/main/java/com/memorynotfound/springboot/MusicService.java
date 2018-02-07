package com.memorynotfound.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class MusicService {

    private static Logger log = LoggerFactory.getLogger(MusicService.class);

    @CacheEvict(cacheNames = CacheConfig.CACHE_NAME, allEntries = true)
    public void evictCache(){}

    @Cacheable(cacheNames = CacheConfig.CACHE_NAME, condition = "#instrument.equals('trombone')")
    public String play(String instrument) {
        log.info("Executing: " + this.getClass().getSimpleName() + ".play(\"" + instrument + "\");");
        return "paying " + instrument + "!";
    }

}
