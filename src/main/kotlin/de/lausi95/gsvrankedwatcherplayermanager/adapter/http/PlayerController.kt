package de.lausi95.gsvrankedwatcherplayermanager.adapter.http

import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class PlayerResource(
  val summonerId: String
) {

  constructor(player: Player): this(player.summonerId)
}

data class PlayerCollection(
  val players: List<PlayerResource>
)

@RestController
@RequestMapping("/players")
class PlayerController(private val playerRepository: PlayerRepository) {

  @GetMapping
  fun getPlayers(): PlayerCollection {
    val playerResources = playerRepository.getPlayers().map(::PlayerResource)
    return PlayerCollection(playerResources)
  }
}
