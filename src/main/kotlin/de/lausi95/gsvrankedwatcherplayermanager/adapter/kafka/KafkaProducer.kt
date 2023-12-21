package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerNotifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
private class KafkaProducer(val kafkaTemplate: KafkaTemplate<String, Any>, ) : PlayerNotifier {

  override fun notifyPlayersUpdated(players: Collection<Player>) {
    val message = PlayersUpdatedMessage(players.map { it.summonerId })
    kafkaTemplate.send("players_updated", message)
  }
}
