package com.synergenhealth.cdms.redisapp;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Log
@EnableCaching
@SpringBootApplication
public class RedisAppApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	IDGenerator idGenerator;


	public static void main(String[] args) {
		SpringApplication.run(RedisAppApplication.class, args);

	}

	@Bean
	CacheManager redisCache(RedisConnectionFactory cf){
		return RedisCacheManager
				.builder(cf)
				.build();
	}

	/*@Bean
	ApplicationRunner repositories(UserRepository userRepository){
		return userRunner("repositories", args->{
			Long userId = idGenerator.generatedId();

			List<User> users = Arrays.asList(new User(idGenerator.generatedId(), "User1","male",42 ),
					new User(idGenerator.generatedId(), "User2","male",32 ),
			new User(idGenerator.generatedId(), "User11","female",22 ),
			new User(idGenerator.generatedId(), "User101","female",25 ));

			users.stream().map(user -> userRepository.save(user));

			Collection<User> userColl = userRepository.findAll();
			userColl.forEach(u-> lo);

		});
	}*/

	/*@Bean
	CommandLineRunner runner(){
		return  args->{
				logger.info("Retriving book in non cacheble way");

				Instant start1 = Instant.now();
				Book book1 = bookRepository.getByTitle("titleC");
				Instant end1 = Instant.now();
				Duration duration1 = Duration.between(start1,end1);
				logger.info("Book Details:: {}",book1);
				logger.info("Duration for non-cacheble way :: {}", Duration.of(duration1.getSeconds(),ChronoUnit.SECONDS).toMillis());

				logger.info("Retriving book in cacheble way");

				Instant start2 = Instant.now();
				Book book2 = bookRepository.getByIsbn("isbn-2345");
				Instant end2 = Instant.now();
				Duration duration2 = Duration.between(start2,end2);
				logger.info("Book Details:: {}",book2);
				logger.info("Duration for cacheble way :: {}", Duration.of(duration2.getSeconds(),ChronoUnit.SECONDS).toMillis());



		};

	}*/
}
