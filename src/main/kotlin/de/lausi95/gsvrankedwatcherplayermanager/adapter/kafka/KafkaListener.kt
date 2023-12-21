package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

import de.lausi95.gsvrankedwatcherplayermanager.application.PlayerApplicationService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
private class KafkaListener(val playerApplicationService: PlayerApplicationService) {

  @KafkaListener(topics = ["player_added"])
  fun handlePlayerAdded(message: PlayerAddedMessage) {
    playerApplicationService.addPlayer(message.summonerName)
  }

  @KafkaListener(topics = ["player_deleted"])
  fun handlePlayerDeleted(message: PlayerDeletedMessage) {
    playerApplicationService.deletePlayer(message.summonerName)
  }
}
