- 远程连接（在redis客户端下文件执行）
	
		redis-cli.exe -h 'ip' -p 'port' -a 'password'
例:

		redis-cli.exe -h 120.79.210.182 -p 6379 -a pwd



##SpringBoot2.0使用

- pom.xml引入依赖
	
	
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-data-redis</artifactId>
	    </dependency>

- 添加cache的配置类
		
		package com.csidez.blog.util.config;

		import com.fasterxml.jackson.annotation.JsonAutoDetect;
		import com.fasterxml.jackson.annotation.PropertyAccessor;
		import com.fasterxml.jackson.databind.ObjectMapper;
		import org.springframework.cache.CacheManager;
		import org.springframework.cache.annotation.CachingConfigurerSupport;
		import org.springframework.cache.annotation.EnableCaching;
		import org.springframework.cache.interceptor.KeyGenerator;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.data.redis.cache.RedisCacheConfiguration;
		import org.springframework.data.redis.cache.RedisCacheManager;
		import org.springframework.data.redis.cache.RedisCacheWriter;
		import org.springframework.data.redis.connection.RedisConnectionFactory;
		import org.springframework.data.redis.core.RedisTemplate;
		import org.springframework.data.redis.core.StringRedisTemplate;
		import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
		
		import java.lang.reflect.Method;
		import java.time.Duration;
		import java.util.HashMap;
		import java.util.Map;
		
		@Configuration
		@EnableCaching
		public class RedisConfig extends CachingConfigurerSupport {

	    @Bean
	    public KeyGenerator keyGenerator() {
	        return new KeyGenerator() {
	            @Override
	            public Object generate(Object target, Method method, Object... params) {
	                StringBuilder sb = new StringBuilder();
	                sb.append(target.getClass().getName());
	                sb.append(method.getName());
	                for (Object obj : params) {
	                    sb.append(obj.toString());
	                }
	                return sb.toString();
	            }
	        };
	    }
	
	    @SuppressWarnings("rawtypes")
	    @Bean
	    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
	        //user信息缓存配置
	        RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
	                .entryTtl(Duration.ofMinutes(30)).disableCachingNullValues().prefixKeysWith("role");
	        //product信息缓存配置
	        RedisCacheConfiguration productCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
	                .entryTtl(Duration.ofMinutes(10)).disableCachingNullValues().prefixKeysWith("test");
	        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
	        redisCacheConfigurationMap.put("role", userCacheConfiguration);
	        redisCacheConfigurationMap.put("test", productCacheConfiguration);
	        //初始化一个RedisCacheWriter
	        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
	        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
	        //ClassLoader loader = this.getClass().getClassLoader();
	        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
	        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
	        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
	
	        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
	        //设置默认超过期时间是30秒
	        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
	        //初始化RedisCacheManager
	        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
	        return cacheManager;
	    }
	
	    @Bean
	    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
	        StringRedisTemplate template = new StringRedisTemplate(factory);
	        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);
	        template.setValueSerializer(jackson2JsonRedisSerializer);
	        template.afterPropertiesSet();
	        return template;
	    }
	
	}
			
- 测试使用
	
		package com.csidez.blog;
	
		import com.csidez.blog.model.UserInfo;
		import org.junit.Assert;
		import org.junit.Test;
		import org.junit.runner.RunWith;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.test.context.SpringBootTest;
		import org.springframework.data.redis.core.RedisTemplate;
		import org.springframework.data.redis.core.StringRedisTemplate;
		import org.springframework.data.redis.core.ValueOperations;
		import org.springframework.test.context.junit4.SpringRunner;
		
		import java.util.concurrent.TimeUnit;
		
		@RunWith(SpringRunner.class)
		@SpringBootTest
		public class RedisTest {
	
	    @Autowired
	    private StringRedisTemplate stringRedisTemplate;
	
	    @Autowired
	    private RedisTemplate redisTemplate;
	
	    @Test
	    public void redisStringTest() throws Exception {
	        stringRedisTemplate.opsForValue().set("aaa","111");
	        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
	    }
	
	    @Test
	    public void redisObjTest() throws Exception{
	        UserInfo user=new UserInfo();
	        user.setName("周海彬");
	        user.setPassword("123");
	        ValueOperations<String, UserInfo> operations=redisTemplate.opsForValue();
	        operations.set("com.csidez", user);
	        operations.set("com.csidez.f", usr,1,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        //redisTemplate.delete("com.neo.f");
	        boolean exists=redisTemplate.hasKey("com.neo.f");
	        if(exists){
	            System.out.println("exists is true");
	        }else{
	            System.out.println("exists is false");
	        }
	        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
	    }
	
	}


###参考：
	
	http://www.ityouknow.com/springboot/2016/03/06/spring-boot-redis.html