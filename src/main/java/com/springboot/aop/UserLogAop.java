package com.springboot.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.entity.User;
import com.springboot.service.UserService;
@Aspect
@Component
public class UserLogAop {
	
	@Autowired
	private UserService userService;
	
	// 定义切入点
    public static final String POINT_CUT = "execution(public * com.springboot.controller.LoginController.login(..))";
//  public static final String POINT_CUT = "execution(public * com.springboot.Controller.LoginController.*(..))";
  @Pointcut(POINT_CUT)
  public void pointCut(){
  }
//    @Pointcut("execution(public * com.springboot.Controller.LoginController.login*(..))")

//    切点表达式中，..两个点表明多个，*代表一个，  上面24行表达式代表切入com.springboot.service.impl包下的所有类的所有方法，
//    方法参数不限，返回类型不限。  其中访问修饰符可以不写，不能用*，，第一个*代表返回类型不限，第二个*表示所有类，
//    第三个*表示所有方法，..两个点表示方法里的参数不限。  然后用@Pointcut切点注解
//    原文链接：https://blog.csdn.net/u012326462/java/article/details/82529835
    @Before("pointCut()")
    public void before() throws Throwable {  
        System.out.println("----------方法前[校验]-----------");
    }
    @After("pointCut()")  
    public void after()throws Throwable{  
//        System.out.println(".....方法后.....");
    }  
    /**
     * 后置返回
     *      如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     *            参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = "pointCut()",returning = "LoginResult")
    public void afterReturn(JoinPoint joinPoint,Object LoginResult) {
    	System.out.println("----------AfterReturning-----------");
        Object[] obj = joinPoint.getArgs();//鑾峰彇杩炴帴鐐规柟娉曡繍琛屾椂鐨勫叆鍙傚垪琛�
        String user = (String)obj[0];
        JSONObject object = JSON.parseObject(user);
        String userName = object.get("userName").toString();
        int userId = 0;
        userId= userService.getUserId(userName);
        String uid = Integer.toString(userId);
        System.out.println(userId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		java.util.Date date=new java.util.Date();  
		String nowTime=sdf.format(date); 
//		boolean result = (boolean)LoginResult;
		if(userId!=0&&userName!="") {
			userService.registerLog(userId,nowTime);
		}
		else
		{
			System.out.println("用户名、用户ID有问题");
		}
    }
}
