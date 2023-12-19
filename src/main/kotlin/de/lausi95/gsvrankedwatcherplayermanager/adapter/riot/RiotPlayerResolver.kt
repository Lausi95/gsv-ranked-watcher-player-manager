package de.lausi95.gsvrankedwatcherplayermanager.adapter.riot

import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerResolver
import org.springframework.stereotype.Component
import services.ClientApi
import services.PlatformRoutes

@Component
private class RiotPlayerResolver : PlayerResolver {

  override fun resolvePlayer(summonerName: String): Player? {
    val response = ClientApi.summonerV4(PlatformRoutes.EUW1).getSummonerByName(summonerName).execute()
    if (!response.isSuccessful) {
      return null
    }

    val resolvedSummonerId = response.body()?.puuid
    val resolvedSummonerName = response.body()?.name
    if (resolvedSummonerId == null || resolvedSummonerName == null) {
      return null
    }

    return Player(resolvedSummonerId, resolvedSummonerName)
  }
}
