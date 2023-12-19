package de.lausi95.gsvrankedwatcherplayermanager.application

import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerNotifier
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerRepository
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerResolver
import org.springframework.stereotype.Service

@Service
class PlayerApplicationService(
  val playerRepository: PlayerRepository,
  val playerResolver: PlayerResolver,
  val playerNotifier: PlayerNotifier
) {

  fun addPlayer(summonerName: String) {
    val player = playerResolver.resolvePlayer(summonerName) ?: return

    playerRepository.savePlayer(player)
    playerNotifier.notifyPlayerAdded()
  }

  fun deletePlayer(summonerName: String) {
    val player = playerRepository.findBySummonerName(summonerName) ?: return

    playerRepository.deletePlayer(player)
    playerNotifier.notifyPlayerDeleted()
  }

  fun getPlayers(): List<Player> {
    return playerRepository.getPlayers()
  }
}
