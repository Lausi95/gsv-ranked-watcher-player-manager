package de.lausi95.gsvrankedwatcherplayermanager.adapter.mongo

import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerRepository
import org.springframework.stereotype.Component

@Component
private class MongoPlayerRepository : PlayerRepository {

  override fun savePlayer(player: Player) {
    TODO("Not yet implemented")
  }

  override fun findBySummonerName(summonerName: String): Player? {
    TODO("Not yet implemented")
  }

  override fun deletePlayer(player: Player) {
    TODO("Not yet implemented")
  }

  override fun getPlayers(): List<Player> {
    TODO("Not yet implemented")
  }
}
