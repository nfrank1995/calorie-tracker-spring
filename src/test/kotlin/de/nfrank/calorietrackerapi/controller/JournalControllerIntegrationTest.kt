package de.nfrank.calorietrackerapi.controller

import de.nfrank.calorietrackerapi.domain.Journal
import de.nfrank.calorietrackerapi.repository.JournalRepository
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JournalControllerIntegrationTest @Autowired constructor(
    private val journalRepository: JournalRepository,
    private val restTemplate: TestRestTemplate
) {
    private val defaultJournalId = ObjectId.get()

    @LocalServerPort
    protected var port: Int = 0

    @BeforeEach
    fun setUp() {
        journalRepository.deleteAll()
    }

    @Test
    fun `should return all journals`() {
        saveOneJournal()

        val response = restTemplate.getForEntity(
            getRootUrl(),
            List::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(1, response.body?.size)
    }

    @Test
    fun `should return single journal by id`() {
        saveOneJournal()

        val response = restTemplate.getForEntity(
            getRootUrl() + "/$defaultJournalId",
            Journal::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(defaultJournalId, response.body?.id)
    }


    private fun getRootUrl(): String? = "http://localhost:$port/journals"

    private fun saveOneJournal() = journalRepository.save(Journal(defaultJournalId, "ct user", listOf("record1", "record2")))
}