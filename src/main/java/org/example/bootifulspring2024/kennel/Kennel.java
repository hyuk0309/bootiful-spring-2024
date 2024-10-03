package org.example.bootifulspring2024.kennel;

import org.example.bootifulspring2024.adoption.DogAdoptedEvent;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
class Kennel {

	@ApplicationModuleListener // = (@Async + @Transactional + @EventListener)
	void onDogAdoptedEvent(DogAdoptedEvent dogAdoptedEvent) throws Exception {
		System.out.println("starting handling for [" + dogAdoptedEvent + "]");
		Thread.sleep(10_000);
		System.out.println(
			"onDogAdoptedEvent [" + dogAdoptedEvent + "]! " + "Hurray! guess we'd better prepare the paperwork...");
		Thread.sleep(10_000);
		System.out.println("all done [" + dogAdoptedEvent + "]!");
	}

}
