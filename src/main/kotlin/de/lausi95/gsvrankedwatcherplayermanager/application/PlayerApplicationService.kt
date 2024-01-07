package de.lausi95.gsvrankedwatcherplayermanager.application

import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerRepository
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerResolver
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PlayerApplicationService(
  private val playerRepository: PlayerRepository,
  private val playerResolver: PlayerResolver,
) {

  fun addPlayer(summonerName: String) {
    if (playerRepository.existsBySummonerName(summonerName))
      throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Player with summoner name $summonerName already exists.")

    val player = playerResolver.resolvePlayer(summonerName)
      ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Player with summoner name $summonerName not found.")

    playerRepository.savePlayer(player)
  }

  fun deletePlayer(summonerName: String) {
    val player = playerRepository.findBySummonerName(summonerName)
      ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Player with summoner name $summonerName does not exist.")

    playerRepository.deletePlayer(player)
  }
}
