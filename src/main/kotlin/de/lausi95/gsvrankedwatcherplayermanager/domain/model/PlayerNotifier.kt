package de.lausi95.gsvrankedwatcherplayermanager.domain.model

interface PlayerNotifier {

  fun notifyPlayersUpdated(players: Collection<Player>)
}
