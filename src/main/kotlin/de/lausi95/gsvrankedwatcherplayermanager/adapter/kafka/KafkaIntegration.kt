package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

import de.lausi95.gsvrankedwatcherplayermanager.application.PlayerApplicationService
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerNotifier
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

internal data class PlayerAddedMessage(val summonerName: String)

internal data class PlayerDeletedMessage(val summonerName: String)

internal data class PlayersUpdatedMessage(val summonerIds: List<String>)

@Component
private class KafkaIntegration(val kafkaTemplate: KafkaTemplate<String, Any>, val playerApplicationService: PlayerApplicationService) : PlayerNotifier {

  @KafkaListener(topics = ["player_added"])
  fun handlePlayerAdded(message: PlayerAddedMessage) {
    playerApplicationService.addPlayer(message.summonerName)
  }

  @KafkaListener(topics = ["player_deleted"])
  fun handlePlayerDeleted(message: PlayerDeletedMessage) {
    playerApplicationService.deletePlayer(message.summonerName)
  }

  override fun notifyPlayerAdded() {
    notifyPlayersUpdated()
  }

  override fun notifyPlayerDeleted() {
    notifyPlayersUpdated()
  }

  fun notifyPlayersUpdated() {
    val message = PlayersUpdatedMessage(playerApplicationService.getPlayers().map { it.summonerId })
    kafkaTemplate.send("players_updated", message)
  }
}
