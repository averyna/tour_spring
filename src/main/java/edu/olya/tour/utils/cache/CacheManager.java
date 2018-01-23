package edu.olya.tour.utils.cache;

import edu.olya.tour.utils.cache.impl.ExpirationEntityCache;
import edu.olya.tour.utils.context.WebContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//@Aspect
public class CacheManager {

    //@Around("@annotation(edu.olya.tour.utils.cache.CacheConfig) && @annotation(cacheConfig)")
    public Object around(ProceedingJoinPoint pjp, CacheConfig cacheConfig) throws Throwable {

        boolean cacheEnabled = cacheConfig != null;

        Cache cache = null;

        String key = null;

        if (cacheEnabled) {
            key = pjp.getSignature().toString();
          // test  pjp.getSignature().toShortString();

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

            //получаем Object value(список стран) из  "ExpirationValue" fields: long expiredAt; Object value;
            Object cachedValue = cache.get(key);

            if (cachedValue != null) {//может быть null, если ExpirationValue==null или now > value.expiredAt
                return cachedValue;
            }
        }

        Object result = pjp.proceed();

        if (cache != null) {

            ((ExpirationEntityCache) cache).setExpirationTime(
                    Long.parseLong(cacheConfig.params()[0].value()));
            cache.put(key, result);
        }

        return result;
    }
}
/////////////////////////////////inside pjp//////////////////////////////////////////////////////////////////
//
//        System.err.println("getKind: " + pjp.getKind()); // method-execution
//        System.err.println("getSignature.toString: "+ pjp.getSignature().toString());//List edu.olya.tour.service.FilterService.getAllHotels()
//        System.err.println("getSignature.getName: "+ pjp.getSignature().getName()); //getAllHotels
//        System.err.println("getSignature:getDeclaringType "+ pjp.getSignature().getDeclaringType());// interface edu.olya.tour.service.FilterService
//        System.err.println("getSignature:getDeclaringTypeName "+ pjp.getSignature().getDeclaringTypeName()); // edu.olya.tour.service.FilterService
//        System.err.println("pjp.getClass.getName: " + pjp.getClass().getName()); // org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint
//
//        System.err.println("entering: " + pjp); // execution(List edu.olya.tour.service.FilterService.getAllHotels())
//        System.err.println("  w/args: " + pjp.getArgs()); // w/args: [Ljava.lang.Object;@c3063e3
//        System.err.println("      at: " + pjp.getSourceLocation()); //at: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@decb74c
//
//        System.err.println("pjp.getTarget().getClass().getName: " + pjp.getTarget().getClass().getName()); // edu.olya.tour.service.impl.FilterServiceImpl
//
//        Method m = original.getMethod(pjp.getSignature().getName(), new Class[]{pjp.getArgs()});


////////////////////////////another way to get method's annotation/////////////////////////////////////////////////////


//        Class original = pjp.getTarget().getClass();
//
//        List<Class> args = new ArrayList<>();
//               for(Object a : pjp.getArgs()) {
//                   args.add(a.getClass());
//               }
//
//        Class[] argsC = new Class[args.size()];
//        args.toArray(argsC);

//        Method m = original.getMethod(pjp.getSignature().getName(), argsC);
//        CacheConfig cacheConfig = m.getAnnotation(CacheConfig.class);

//org.apache.catalina.util.ParameterMap comes into args for TourServiceImpl where Map is required
//throws "java.lang.NoSuchMethodException: edu.olya.tour.service.impl.TourServiceImpl.searchTours(org.apache.catalina.util.ParameterMap)"
//it works fine with "new Class[]{java.util.Map.class})" instead of argsC



///////////////////before AOP///////////////////////////////////////////////////////////////////////////////
//    public static <T> T wrap(T service) {
//        Class[] interfaces = service.getClass().getInterfaces();
//
//        return (T) Proxy.newProxyInstance(
//                CacheManager.class.getClassLoader(),
//                interfaces,
//                new InvocationHandler() {
//                    final T originalReference = service;
//
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        try {
//                            Object behindProxy = originalReference;
//                            //можно без while, но если оберток несколько, то без цикла не обойтись
//                            while (Proxy.isProxyClass(behindProxy.getClass())) {
//                                try {
//                                    Object handler = Proxy.getInvocationHandler(originalReference);
//                                    Class handlerClass = handler.getClass();
//                                    Field objField = handlerClass.getDeclaredField("originalReference");
//                                    objField.setAccessible(true);
//                                    behindProxy = objField.get(handler);
//                                } catch (NoSuchFieldException e) {
//                                    e.printStackTrace();
//                                } catch (IllegalAccessException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            Method m = behindProxy.getClass().getMethod(method.getName(), method.getParameterTypes());
//                            CacheConfig cacheConfig = m.getAnnotation(CacheConfig.class);
//
//
//                            //Returns this element's annotation for the specified type if such an annotation is present, else null.
//                            //interface edu.olya.tour.utils.cache.CacheConfig
////                            CacheConfig cacheConfig = method.getAnnotation(CacheConfig.class);
//                            boolean cacheEnabled = cacheConfig != null;
//
//                            Cache cache = null;
//
//                            String key = null;
//
//                            if (cacheEnabled) {
//                                StringBuilder keySb = new StringBuilder(method.getName());
//                                if (args != null) {
//                                    for (Object s : args) {
//                                        //s.toString вернет имя класса, сущностью которого является аргуметн и хэш-код аргумента
//                                        //по сути это "return getClass().getName() + "@" + Integer.toHexString(hashCode());"
//                                        keySb.append(s == null ? "null" : s.toString());
//                                    }
//                                }
//                                key = keySb.toString();
//                                switch (cacheConfig.scope()) {
//                                    case SESSION:
//                                        cache = (Cache) WebContextHolder.getSessionContext().getAttribute(Cache.class.getName());
//                                        if (cache == null) {
//                                            cache = cacheConfig.cacheImpl().newInstance();
//                                            WebContextHolder.getSessionContext().setAttribute(Cache.class.getName(), cache);
//                                        }
//                                        break;
//                                    case APPLICATION:
//                                    default:
//                                        cache = (Cache) WebContextHolder.getApplicationContext().getAttribute(Cache.class.getName());
//                                        if (cache == null) {
//                                            cache = cacheConfig.cacheImpl().newInstance();
//                                            WebContextHolder.getApplicationContext().setAttribute(Cache.class.getName(), cache);
//                                        }
//                                }
//
//                                //получаем Object value(список стран) из  "ExpirationValue" fields: long expiredAt; Object value;
//                                Object cachedValue = cache.get(key);
//
//                                if (cachedValue != null) {//может быть null, если ExpirationValue==null или now > value.expiredAt
//                                    return cachedValue;
//                                }
//                            }
//
//                            Object result = method.invoke(originalReference, args);
//
//                            if (cache != null) {
//
//                                ((ExpirationEntityCache) cache).setExpirationTime(
//                                        Long.parseLong(cacheConfig.params()[0].value()));
//                                cache.put(key, result);
//                            }
//
//                            return result;
//                        } catch (Exception e) {
//                            throw e;
//                        }
//                    }
//                }
//        );