package com.projector.probabilisticcache;

import com.projector.probabilisticcache.cache.LruCache;
import com.projector.probabilisticcache.model.IdEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProbabilisticCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProbabilisticCacheApplication.class, args);
    }

}
