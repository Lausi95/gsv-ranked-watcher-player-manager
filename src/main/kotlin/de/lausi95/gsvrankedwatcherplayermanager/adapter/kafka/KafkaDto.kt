package de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka

internal data class PlayerAddedMessage(val summonerName: String)

internal data class PlayerDeletedMessage(val summonerName: String)

internal data class PlayersUpdatedMessage(val summonerIds: List<String>)
