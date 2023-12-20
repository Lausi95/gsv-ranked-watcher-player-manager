package de.lausi95.gsvrankedwatcherplayermanager.domain.model

interface PlayerResolver {

  fun resolvePlayer(summonerName: String): Player?
}
