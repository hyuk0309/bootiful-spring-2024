package org.example.bootifulspring2024.adoption;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Assistant {

	@Bean
	ApplicationRunner runner(ChatClient chatClient) {
		return args -> {
			var content = chatClient
				.prompt()
				.user("do you have any neurotic dogs?")
				.call()
				.entity(DogAdoptionSuggestion.class);
			System.out.println("reply [" + content + "]");
		};
	}

	@Bean
	ChatClient chatClient(
		ChatClient.Builder builder,
		DogRepository dogRepository,
		VectorStore vectorStore
	) {
		// support system prompt
		var system = """
			You are an AI powered assistant to help people adopt a dog
			from the adoption agency named Pooch Palace with locations in
			Seoul, Las Vegas, Tokyo, Krakow, Singapore, Paris, London, and
			San Francisco. If you don't know about the dogs housed at our particular
			stores, then return a disappointed response suggesting we don't
			have any dogs available.
			""";

		// support RAG(RETRIEVAL AUGMENTED GENERATION)
		dogRepository.findAll().forEach(dog -> {
			var dogument = new Document("id: %s, name: %s, description: %s".formatted(dog.id(), dog.name(), dog.description()));
			vectorStore.add(List.of(dogument));
		});

		return builder
			.defaultSystem(system)
			.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
			.build();
	}

	record DogAdoptionSuggestion(String name, String description, Integer id) {}

}
