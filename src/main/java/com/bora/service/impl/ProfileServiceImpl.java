package com.bora.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bora.config.ProfileAMQPConfig;
import com.bora.model.Profile;
import com.bora.repository.ProfileRepository;
import com.bora.service.SlackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProfileServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
	
	@Autowired
	private ProfileRepository repository;

	@Autowired
	private SlackService slackService;
	
    @Autowired
    private RabbitTemplate rabbitTemplate;

	public List<Profile> findAll() {
		return repository.findAll();
	}

	public Profile create(Profile profile) {
		Profile entity = repository.save(profile);
		slackService.postMessage("Novo perfil criado com o email: " + entity.getEmail());
		sendProfileToRabbit(entity);
		return entity;
	}

	public Profile findById(String id) {
		Optional<Profile> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}

	public Profile update(Profile profile, String id) {
		return repository.findById(id).map(entity -> {
			entity.setEmail(profile.getEmail());
			entity.setAventureiro(profile.getAventureiro());
			entity.setDescanso(profile.getDescanso());
			entity.setFilho(profile.getFilho());
			entity.setNome(profile.getNome());
			entity.setPet(profile.getPet());
			entity.setSairAnoite(profile.getSairAnoite());
			entity.setSolteiro(profile.getSolteiro());
			return repository.save(entity);
		}).orElseGet(() -> {
			Profile entity = repository.save(profile);
			slackService.postMessage("Novo perfil criado com o email: " + entity.getEmail());
			sendProfileToRabbit(entity);
			return entity;
		});
	}

	public void deleteById(String id) {
		repository.deleteById(id);
	}
	
	@Async
	public void sendProfileToRabbit(Profile profile) {
        try {
            String json = new ObjectMapper().writeValueAsString(profile);
            rabbitTemplate.convertAndSend(ProfileAMQPConfig.EXCHANGE_NAME, "", json);
            logger.info("Postando mensagem na fila...");
        } catch (JsonProcessingException e) {
           logger.error(e.getMessage());
        }
    }
}
