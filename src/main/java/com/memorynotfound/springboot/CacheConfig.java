package com.memorynotfound.springboot;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.spring.cache.CacheBuilder;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

    public static final String CACHE_NAME = "instruments";

    @Bean(destroyMethod = "disconnect")
    public Cluster cluster() {
        //this connects to a Couchbase instance running on localhost
        return CouchbaseCluster.create();
    }

    @Bean(destroyMethod = "close")
    public Bucket bucket() {
        //this will be the bucket where every cache-related data will be stored
        //note that the bucket "default" must exist
        return cluster().openBucket("default", "");
    }

    @Bean
    public CacheManager cacheManager() {
        CacheBuilder cacheBuilder = CacheBuilder.newInstance(bucket()).withExpiration(0);
        return new CouchbaseCacheManager(cacheBuilder, CACHE_NAME);
    }

}
