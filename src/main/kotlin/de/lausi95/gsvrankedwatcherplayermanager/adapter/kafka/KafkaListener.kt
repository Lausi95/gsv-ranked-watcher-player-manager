package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import de.lausi95.gsvrankedwatcherplayermanager.application.PlayerApplicationService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
private class KafkaListener(val playerApplicationService: PlayerApplicationService, val objectMapper: ObjectMapper) {

  @KafkaListener(topics = [SERVICE_UP_TOPIC])
  fun handleServiceUp(message: String) {
    val serviceUpMessage = objectMapper.readValue(message, ServiceUpMessage::class.java)
    if (serviceUpMessage.requiredData.contains("players")) {
      playerApplicationService.boradcastPlayers()
    }
  }

  @KafkaListener(topics = [PLAYER_ADDED_TOPIC])
  fun handlePlayerAdded(message: String) {
    val playerAddedMessage = objectMapper.readValue(message, PlayerAddedMessage::class.java)
    playerApplicationService.addPlayer(playerAddedMessage.summonerName)
  }

  @KafkaListener(topics = [PLAYER_DELETED_TOPIC])
  fun handlePlayerDeleted(message: String) {
    val playerDeletedMessage = objectMapper.readValue(message, PlayerDeletedMessage::class.java)
    playerApplicationService.deletePlayer(playerDeletedMessage.summonerName)
  }
}
