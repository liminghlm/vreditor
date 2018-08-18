package fuwu.aop;



import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * Created by mingming.li on 2016/9/8.
 */

@Aspect
public class LogAsp  implements Ordered {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogAsp.class);

    @Around("@annotation(fuwu.aop.ForLog)")
    public Object writeLog(final ProceedingJoinPoint point)throws Throwable{
        long start = System.currentTimeMillis();

        Signature si = point.getSignature();
        Object[] args = point.getArgs();

        Method method = null;
        String[] paramsNames =null;

        if(si instanceof MethodSignature){
            method = ((MethodSignature)si).getMethod();
            paramsNames = ((MethodSignature)si).getParameterNames();
        }

        Object object = point.proceed();
        ForLog forLog = method.getAnnotation(ForLog.class);
        String value = forLog.value();
        long end = System.currentTimeMillis();
        logger.info("method {} with params {} values {} returned {} takes {} millSeconds",method.getName(),paramsNames,args, JSON.toJSON(object),end-start);
        return object;


    }

    @Override
    public int getOrder() {
        return 2;
    }
}
