package de.lausi95.gsvrankedwatcherplayermanager.domain.model

interface PlayerResolver {

  /**
   * Resolves a player based on the given summoner name.
   *
   * @param summonerName the name of the summoner to resolve
   * @return the resolved Player object, or null if no Player is found
   */
  fun resolvePlayer(summonerName: String): Player?
}
