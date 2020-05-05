package com.bora.service.impl;

import org.springframework.stereotype.Service;

import com.bora.service.SlackService;
import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.SlackClientFactory;
import com.hubspot.slack.client.SlackClientRuntimeConfig;
import com.hubspot.slack.client.methods.params.chat.ChatPostMessageParams;

@Service
public class SlackServiceImpl implements SlackService {

	@Override
	public void postMessage(String mensagem) {
		SlackClientRuntimeConfig runtimeConfig = SlackClientRuntimeConfig.builder()
				.setTokenSupplier(() -> "xoxb-1116245948209-1096913554982-wfHJ9xumsbZqX3dyaZkyghC9").build();

		SlackClient slackClient = SlackClientFactory.defaultFactory().build(runtimeConfig);

		slackClient
				.postMessage(
						ChatPostMessageParams.builder().setText(mensagem).setChannelId("apis-de-integração").build())
				.join().unwrapOrElseThrow();
	}

}
