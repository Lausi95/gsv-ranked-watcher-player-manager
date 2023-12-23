package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

const val SERVICE_UP_TOPIC = "service_up"
internal data class ServiceUpMessage(
  val requiredData: List<String>
)

const val PLAYER_ADDED_TOPIC = "player_added"
internal data class PlayerAddedMessage(
  val summonerName: String
)

const val PLAYER_DELETED_TOPIC = "player_deleted"
internal data class PlayerDeletedMessage(
  val summonerName: String
)

const val PLAYERS_UPDATED_TOPIC = "players_updated"
internal data class PlayersUpdatedMessage(
  val summonerIds: List<String>
)
