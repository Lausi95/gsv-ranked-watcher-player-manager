package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerNotifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
private class KafkaProducer(val kafkaTemplate: KafkaTemplate<String, Any>, val objectMapper: ObjectMapper) : PlayerNotifier {

  override fun notifyPlayersUpdated(players: Collection<Player>) {
    val playersUpdatedMessage = PlayersUpdatedMessage(players.map { it.summonerId })
    val message = objectMapper.writeValueAsString(playersUpdatedMessage)
    kafkaTemplate.send(PLAYERS_UPDATED_TOPIC, message)
  }
}
