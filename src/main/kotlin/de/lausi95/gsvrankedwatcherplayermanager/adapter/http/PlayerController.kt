package de.lausi95.gsvrankedwatcherplayermanager.adapter.http

import de.lausi95.gsvrankedwatcherplayermanager.application.PlayerApplicationService
import de.lausi95.gsvrankedwatcherplayermanager.domain.model.Player
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/players")
private class PlayerController(val playerApplicationService: PlayerApplicationService) {

  @GetMapping
  fun players(): List<Player> {
    return playerApplicationService.getPlayers();
  }
}
