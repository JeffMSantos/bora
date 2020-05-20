package com.bora.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bora.service.SlackService;
import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.SlackClientFactory;
import com.hubspot.slack.client.SlackClientRuntimeConfig;
import com.hubspot.slack.client.methods.params.chat.ChatPostMessageParams;

@Service
public class SlackServiceImpl implements SlackService {

	@Value("${slack_access_token}")
	private String token;
	
	@Async
	@Override
	public void postMessage(String mensagem) {
		SlackClientRuntimeConfig runtimeConfig = SlackClientRuntimeConfig.builder()
				.setTokenSupplier(() -> token).build();

		SlackClient slackClient = SlackClientFactory.defaultFactory().build(runtimeConfig);

		slackClient
				.postMessage(
						ChatPostMessageParams.builder().setText(mensagem).setChannelId("apis-de-integração").build())
				.join().unwrapOrElseThrow();
	}

}
