package com.vogella.spring.user.initialize;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.vogella.spring.user.data.UserRepository;
import com.vogella.spring.user.domain.User;

@Profile("!production")
@Component
public class UserDataInitializer implements SmartInitializingSingleton {

	private UserRepository userRepository;

	public UserDataInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void afterSingletonsInstantiated() {
		User user = new User(1, "Fabian Pfaff", "fabian.pfaff@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("ADMIN"), Instant.now(), true);
		User user2 = new User(2, "Simon Scholz", "simon.scholz@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("ADMIN"), Instant.now(), false);
		User user3 = new User(3, "Lars Vogel", "lars.vogel@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("USER"), Instant.now(), true);

		userRepository.saveAll(Arrays.asList(user, user2, user3)).subscribe();
	}

}
