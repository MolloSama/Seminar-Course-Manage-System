package com.bkg.coursemanager.common;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*下面这段注解的意思如下：
      1) execution(): 表达式主体
      2) 第一个public *号：表示返回类型， *号表示所有的类型。
      3) 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.king.controller包、子孙包下所有类的方法。
      4) 第二个*号：表示类名，*号表示所有的类。
      5) *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数*/
    @Pointcut("execution(public * com.bkg.coursemanager.controller..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        logger.info("**************打印请求信息开始***************");
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            logger.info("name:{},value:{}", name, request.getParameter(name));
        }


        logger.info("**************打印请求信息结束***************");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {

        logger.info("**************打印结果信息开始***************");
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("**************打印结果信息结束***************");
    }
}