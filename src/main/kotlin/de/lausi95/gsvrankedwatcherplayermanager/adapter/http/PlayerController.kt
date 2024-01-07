package de.lausi95.gsvrankedwatcherplayermanager.adapter.http

import de.lausi95.gsvrankedwatcherplayermanager.application.PlayerApplicationService
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.PlayerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.net.URI

data class PlayerResource(
  val summonerId: String,
  val summonerName: String,
) {

  constructor(player: Player): this(
    player.summonerId,
    player.summonerName
  )
}

data class PlayerCollection(
  val players: List<PlayerResource>
)

data class AddPlayerRequest(
  val summonerName: String
)

@RestController
@RequestMapping("/players")
class PlayerController(
  private val playerRepository: PlayerRepository,
  private val playerApplicationService: PlayerApplicationService) {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getPlayers(): ResponseEntity<PlayerCollection> {
    val playerResources = playerRepository.getPlayers().map(::PlayerResource)
    return ResponseEntity.ok(PlayerCollection(playerResources))
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun addPlayer(@RequestBody request: AddPlayerRequest): ResponseEntity<Any> {
    val summonerName = requireNotNull(request.summonerName)
    val summonerId = playerApplicationService.addPlayer(summonerName)
    return ResponseEntity.created(URI.create("/players/$summonerId")).build()
  }

  @GetMapping("/{summonerName}")
  @ResponseStatus(HttpStatus.OK)
  fun getPlayer(@PathVariable summonerName: String): ResponseEntity<PlayerResource> {
    val player = playerRepository.findBySummonerName(summonerName)
      ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Player with summoner name $summonerName not found")

    return ResponseEntity.ok(PlayerResource(player))
  }

  @DeleteMapping("/{summonerName}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deletePlayer(@PathVariable summonerName: String): ResponseEntity<Any> {
    playerApplicationService.deletePlayer(summonerName)
    return ResponseEntity.noContent().build()
  }
}
