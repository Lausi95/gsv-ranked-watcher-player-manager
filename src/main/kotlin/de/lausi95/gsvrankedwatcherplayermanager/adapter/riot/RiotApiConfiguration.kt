package de.lausi95.gsvrankedwatcherplayermanager.adapter.riot

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import services.ClientApi
import services.interceptors.TokenProvider

@Configuration
private class RiotApiConfiguration(@Value("riot.api-key") val apiKey: String) {

  @EventListener(ApplicationStartedEvent::class)
  fun setUpRiotApi() {
    ClientApi.apply {
      tokenProvider = object : TokenProvider {
        override fun getToken(): String {
          return apiKey
        }
      }
    }
  }
}
