package com.sriharilabs;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

public interface TradingUserRepository extends ReactiveMongoRepository<TradingUser, String> {

	Mono<TradingUser> findByUserName(String userName);

}
