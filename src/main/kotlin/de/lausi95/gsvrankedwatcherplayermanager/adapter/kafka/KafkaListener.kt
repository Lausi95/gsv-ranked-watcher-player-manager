package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import de.lausi95.gsvrankedwatcherplayermanager.application.PlayerApplicationService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
private class KafkaListener(val playerApplicationService: PlayerApplicationService, val objectMapper: ObjectMapper) {

  @KafkaListener(topics = ["player_added"])
  fun handlePlayerAdded(message: String) {
    val playerAddedMessage = objectMapper.readValue(message, PlayerAddedMessage::class.java)
    playerApplicationService.addPlayer(playerAddedMessage.summonerName)
  }

  @KafkaListener(topics = ["player_deleted"])
  fun handlePlayerDeleted(message: String) {
    val playerDeletedMessage = objectMapper.readValue(message, PlayerDeletedMessage::class.java)
    playerApplicationService.deletePlayer(playerDeletedMessage.summonerName)
  }
}
