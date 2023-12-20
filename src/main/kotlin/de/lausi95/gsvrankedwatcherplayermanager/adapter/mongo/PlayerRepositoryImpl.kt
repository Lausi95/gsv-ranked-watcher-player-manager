package de.lausi95.gsvrankedwatcherplayermanager.adapter.mongo

import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerRepository
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

private data class PlayerEntity(@Id val summonerId: String, val summonerName: String) {

  constructor(player: Player) : this(player.summonerId, player.summonerName)

  fun toPlayer(): Player = Player(summonerId, summonerName)
}

private interface PlayerMongoRepository : MongoRepository<PlayerEntity, String> {

  fun findBySummonerName(summerName: String): PlayerEntity?
}

@Component
private class PlayerRepositoryImpl(val repository: PlayerMongoRepository) : PlayerRepository {

  override fun savePlayer(player: Player) {
    repository.save(PlayerEntity(player))
  }

  override fun findBySummonerName(summonerName: String): Player? {
    return repository.findBySummonerName(summonerName)?.toPlayer()
  }

  override fun deletePlayer(player: Player) {
    repository.deleteById(player.summonerId)
  }

  override fun getPlayers(): List<Player> {
    return repository.findAll().map { it.toPlayer() }
  }
}
