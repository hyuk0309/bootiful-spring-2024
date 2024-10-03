package org.example.bootifulspring2024.adoption;

import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Transactional
@RequestMapping("/adoptions")
class AdoptionController {

	private final ApplicationEventPublisher publisher;

	public AdoptionController(ApplicationEventPublisher publishEvent) {
		this.publisher = publishEvent;
	}

	@PostMapping("/{id}")
	void adopt(@PathVariable Integer id) {
		this.publisher.publishEvent(new DogAdoptedEvent(id, "dog"));
	}

}
