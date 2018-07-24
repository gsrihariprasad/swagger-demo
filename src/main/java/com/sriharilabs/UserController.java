package com.sriharilabs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	private final TradingUserRepository tradingUserRepository;
	
	@EventListener(classes= ApplicationReadyEvent.class)
	public void show() {
		
		System.out.println(" ITS Working....");
	}

	public UserController(TradingUserRepository tradingUserRepository) {
		this.tradingUserRepository = tradingUserRepository;
		
		TradingUser t=new TradingUser();
		t.setFullName("srihari");
		t.setUserName("gs");
		TradingUser t1=new TradingUser();
		t1.setFullName("srihari1");
		t1.setUserName("gs1");
		TradingUser t2=new TradingUser();
		t2.setFullName("srihari2");
		t2.setUserName("gs2");
		
		List list=new ArrayList();
		list.add(t);
		list.add(t1);
		list.add(t2);
		Flux<TradingUser> f=tradingUserRepository.saveAll(list);
		
//		List<TradingUser> l=f.collectList().block();
//		l.forEach(s->{
//			System.out.println(s.getFullName());
//		});
		System.out.println(" successfully inserted..."+f.log().count());
		
	}

	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<TradingUser> listUsers() {
		return this.tradingUserRepository.findAll();
	}

	@GetMapping(path = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<TradingUser> showUsers(@PathVariable String username) {
		return this.tradingUserRepository.findByUserName(username);
	}

}