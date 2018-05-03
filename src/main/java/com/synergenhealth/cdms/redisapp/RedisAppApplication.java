package com.synergenhealth.cdms.redisapp;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Log
@EnableCaching
@EnableRedisHttpSession
@SpringBootApplication
public class RedisAppApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	VisitService visitService;

	@Autowired
	Util util;


	public static void main(String[] args) {
		SpringApplication.run(RedisAppApplication.class, args);

	}


	@Bean
	CacheManager redisCache(RedisConnectionFactory cf){
		return RedisCacheManager
				.builder(cf)
				.build();
	}

	@Bean
	CommandLineRunner runner(){
		return args -> log.info("CommandLine Runner");
	}

	/*
	*
	* Redis using as a database.
	* */

	@Bean
	ApplicationRunner applicationRunner(){
		return args -> {

			List<User> userList = Arrays.asList(
					new User(util.generatedId(), "User1","male",42 ),
					new User(util.generatedId(), "User2","male",32 ),
					new User(util.generatedId(), "User11","female",22 ),
					new User(util.generatedId(), "User101","female",25 )
			);

			userRepository.saveAll(userList);

		//	userList.stream().map(u -> userRepository.save(u));

			Collection<User> userCollection = userRepository.findAll();
			userCollection.forEach(u-> log.info(u.toString()));

		};
	}

	/*
	*
	* 	Redis using as message store
	* */

	@Bean
	ApplicationRunner pub(RedisTemplate<String, String> redisTemplate){
		return args -> redisTemplate.convertAndSend("MessageKey", "Message :: "+ Instant.now().toString());
	}

	@Bean
	RedisMessageListenerContainer listenerContainer(RedisConnectionFactory factory){

		MessageListener m1 = ((message, bytes) -> {
			String str = new String(message.getBody());
			log.info("message from :: "+str);
		});

		RedisMessageListenerContainer rmlc = new RedisMessageListenerContainer();
		rmlc.addMessageListener(m1, new PatternTopic("MessageKey"));
		rmlc.setConnectionFactory(factory);
		return rmlc;
	}

	/*
	*
	*  Redis using as caching
	* */

	@Bean
	ApplicationRunner cache(){
		return args -> {
			Runnable runnable = ()-> {
				Collection<Visit> visits = visitService.getAllVisitsByLicenseKey(137139);
				log.info("size of visit collection ::"+visits.size());
			};
			log.info("first time :: "+util.measure(runnable));
			log.info("second time :: "+util.measure(runnable));
			log.info("thrid time :: "+util.measure(runnable));
		};
	}
}
