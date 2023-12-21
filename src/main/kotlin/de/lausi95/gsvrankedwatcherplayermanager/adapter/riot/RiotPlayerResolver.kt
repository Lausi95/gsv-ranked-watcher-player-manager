package de.lausi95.gsvrankedwatcherplayermanager.adapter.riot

import com.github.kimcore.riot.RiotAPI
import com.github.kimcore.riot.errors.RiotException
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerResolver
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component

@Component
private class RiotPlayerResolver : PlayerResolver {

  override fun resolvePlayer(summonerName: String): Player? {
    return runBlocking {
      try {
        val summonerDto = RiotAPI.summoner.getSummonerByName(summonerName)
        return@runBlocking Player(summonerDto.puuid, summonerDto.name)
      } catch (e: RiotException) {
        return@runBlocking null
      }
    }
  }
}
