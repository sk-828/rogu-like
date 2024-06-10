package com.example.demo.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class Aop {
	@Around("execution(* com.example.demo.base.*.*(..))")
	public Object aroundLog(ProceedingJoinPoint jp) {
		log.info("メソッド開始:{"+ jp.getSignature().toString()+"}引数={"+Arrays.deepToString(jp.getArgs())+"}");
		Object result;
		try {
			result = jp.proceed();
		} catch (Throwable e) {
			System.out.println(e.toString());
			log.error("エラー発生。引数={}", Arrays.deepToString(jp.getArgs()));
			e.printStackTrace();
			return "error";
		}
		log.info("メソッド正常終了:{"+ jp.getSignature().toString()+"} 引数={"+Arrays.deepToString(jp.getArgs())+"}");
		return result;
		//return "kensyu5/Test";
	}
}
