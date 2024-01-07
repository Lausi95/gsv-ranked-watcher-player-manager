package de.lausi95.gsvrankedwatcherplayermanager.domain.model

interface PlayerRepository {

  fun getPlayers(): List<Player>

  fun savePlayer(player: Player)

  fun findBySummonerName(summonerName: String): Player?

  fun deletePlayer(player: Player)

  fun existsBySummonerName(summonerName: String): Boolean
}
