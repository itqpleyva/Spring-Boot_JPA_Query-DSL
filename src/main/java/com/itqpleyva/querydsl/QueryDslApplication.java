package com.itqpleyva.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import com.itqpleyva.querydsl.Models.QUserModel;
import com.itqpleyva.querydsl.Models.UserModel;
import com.itqpleyva.querydsl.Repositories.UserRepository;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class QueryDslApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(QueryDslApplication.class, args);
		System.out.println("Query DSL example");
	}

	@Override
	public void run(String... args) throws Exception {
		
		UserModel user1 = new UserModel("Pepe", "M");
		UserModel user2 = new UserModel("Pepe1", "F");
		UserModel user3 = new UserModel("Pepe2", "M");
		UserModel user4 = new UserModel("Pepe3", "F");
		List<UserModel> users = Arrays.asList(user1, user1, user2, user3, user4);
		
		userRepository.saveAll(users);

		QUserModel quser = QUserModel.userModel;
		JPQLQuery<?> query = new JPAQuery<>(entityManager);
		
		List<UserModel> list_user_finded = query.select(quser).from(quser).where(quser.name.like("%P%")).fetch();

		System.out.println(list_user_finded);
	}

}
