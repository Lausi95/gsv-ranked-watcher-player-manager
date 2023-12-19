package de.lausi95.gsvrankedwatcherplayermanager

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.library.Architectures
import org.junit.jupiter.api.Test

class GsvRankedWatcherPlayerManagerApplicationTests {

  @Test
  fun testArchitecture() {
    val classes = ClassFileImporter()
      .withImportOption(ImportOption.DoNotIncludeTests())
      .importPackages("de.lausi95.gsvrankedwatcherplayermanager")

    Architectures.onionArchitecture()
      .withOptionalLayers(true)
      .adapter("kafka", "de.lausi95.gsvrankedwatcherplayermanager.adapter.kafka")
      .adapter("riot", "de.lausi95.gsvrankedwatcherplayermanager.adapter.riot")
      .domainModels("de.lausi95.gsvrankedwatcherplayermanager.domain.model.*")
      .applicationServices("de.lausi95.gsvrankedwatcherplayermanager.application")
      .check(classes)
  }
}
