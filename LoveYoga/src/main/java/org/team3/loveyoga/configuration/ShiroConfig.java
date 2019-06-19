package org.team3.loveyoga.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.team3.loveyoga.realm.UserRealm;


@Configuration
public class ShiroConfig {
	
	//配置credentialMatcher
	@Bean
	public  CredentialsMatcher matcher(){
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");//设置加密方式
		matcher.setHashIterations(1024);
		return matcher;
	}
	
	//配置realm
	@Bean 
	public UserRealm realm(CredentialsMatcher matcher){
		System.out.println("正在创建realm...");
		UserRealm userRealm=new UserRealm();
		userRealm.setCredentialsMatcher(matcher); //给creentialMatcher属性赋值
		
		//7、3在realm中开启缓存
		userRealm.setCachingEnabled(true);
		//开启验证缓存
		userRealm.setAuthenticationCachingEnabled(true);
		//缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
		userRealm.setAuthenticationCacheName("authenticationCache");
		//开启授权缓存
		userRealm.setAuthenticationCachingEnabled(true);
		//缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
		userRealm.setAuthorizationCacheName("authorizationCache");
		return userRealm;
	}
	//配置安全管理器
	@Bean
	public SecurityManager securityManager(UserRealm userRealm){
		System.out.println("正在创建安全管理器...");
		DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
		manager.setRealm(userRealm);
		//7.2  设置缓存
		manager.setCacheManager(ehCacheManager());
		
		return manager;
	}
	//配置shiro的过滤器
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
		//配置安全管理器
		bean.setSecurityManager(securityManager);
		//配置登入界面
		bean.setLoginUrl("/html/login.html");
		//配置未授权的界面
		//bean.setUnauthorizedUrl("/html/error.html");
		//引入自定义的过滤器

		//设置其他过滤选项
		Map<String, String> map=new HashMap<>();
		map.put("/logout", "logout");  //注销
		map.put("/**", "anon");  	 //暂时不开权限，所有请求自由访问，最后来整权限问题；
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
	
	
	//注册自定义的角色过滤器(可选)

	
	//6、开启shiro的注解
	//6、1
	@Bean
	public LifecycleBeanPostProcessor processor(){
		LifecycleBeanPostProcessor processor=new LifecycleBeanPostProcessor();
		return processor;
	}
	//6、2开启注解，shiro的注解利用aop实现，需要使用代理来实现
	@Bean
	public DefaultAdvisorAutoProxyCreator creator(LifecycleBeanPostProcessor processor){
		DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
	//6、3配置通知
	@Bean
	public AuthorizationAttributeSourceAdvisor sourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	//7、配置缓存管理器
	//7、1
	@Bean
	public EhCacheManager ehCacheManager(){
		EhCacheManager cacheManager=new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return cacheManager;
	}
	
	
}
