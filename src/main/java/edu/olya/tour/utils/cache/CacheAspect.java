package edu.olya.tour.utils.cache;

import edu.olya.tour.utils.context.WebContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CacheAspect {

    @Around("@annotation(edu.olya.tour.utils.cache.CacheConfig) && @annotation(cacheConfig)")
    public Object around(ProceedingJoinPoint pjp, CacheConfig cacheConfig) throws Throwable {

        Object result = pjp.proceed();

        Cache cache = null;

        String key = pjp.getSignature().toString();

        switch (cacheConfig.scope()) {
            case SESSION:
                cache = (Cache) WebContextHolder.getSessionContext().getAttribute(Cache.class.getName());
                if (cache == null) {
                    cache = cacheConfig.cacheImpl().newInstance();
                    WebContextHolder.getSessionContext().setAttribute(Cache.class.getName(), cache);
                }
                break;
            case APPLICATION:
            default:
                cache = (Cache) WebContextHolder.getApplicationContext().getAttribute(Cache.class.getName());
                if (cache == null) {
                    cache = cacheConfig.cacheImpl().newInstance();
                    WebContextHolder.getApplicationContext().setAttribute(Cache.class.getName(), cache);
                }
        }

        cache.put(key, result);

        return result;
    }
}
