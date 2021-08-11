package luis.api.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import luis.api.dados.Funcionario;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 * @author Luis
 * Cache para possiveis chamadas de IDP
 */

@Aspect
@Configuration
@EnableCaching
public class CacheAspect {
    private static final Logger logger = LogManager.getLogger(CacheAspect.class);

    static final String   CACHE_REGISTRY_ID = "oidcapi";
    static long     CACHE_LIFESPAN_NUM_UNITS = 3600;
    static final TimeUnit CACHE_LIFESPAN_TIME_UNIT = TimeUnit.SECONDS;
    private static final String DEFAULT_TEMPLATE = null; // default infinispan cache template
    final RemoteCacheManager cacheManager;

    public CacheAspect(RemoteCacheManager remoteCacheManager) {
        this.cacheManager = remoteCacheManager;
    }


    @Around("execution(* luis.api.*.Funcionario*Repository.findByMat*(..))")
    public Object captureFindByMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
       
        try {
             if (joinPoint.getArgs()[0] instanceof String) {
                Funcionario cachedFuncionario = (Funcionario) cacheManager.administration().getOrCreateCache(CACHE_REGISTRY_ID, DEFAULT_TEMPLATE).get((String)joinPoint.getArgs()[0]); 
                if (cachedFuncionario != null) {
                    logger.warn("Cache HIT: {}", () -> (String)joinPoint.getArgs()[0]);
                    return Optional.of(cachedFuncionario);
                } 
             }
        } catch (Exception e) {
            logger.error("Cache MISS - Exception: {0}"+ e.getMessage());
        }

        final Object proceed = joinPoint.proceed(); // pull from repository
        if (((Optional)proceed).isPresent()) {
	        Funcionario pulledFuncionario = (Funcionario) ((Optional)proceed).get();
                try {
                    cacheManager.getCache(CACHE_REGISTRY_ID).putAsync((String)joinPoint.getArgs()[0], pulledFuncionario, 
                        CACHE_LIFESPAN_NUM_UNITS, CACHE_LIFESPAN_TIME_UNIT);
                } catch (Exception e) {
                    logger.error("PUT in Cache - Exception: {0}"+ e.getMessage());
                }
        }

        final long executionTime = System.currentTimeMillis() - start;

        logger.warn("Cache HIT: {}", () -> " executed in %d ms" + executionTime);

        return proceed;
    }
}
