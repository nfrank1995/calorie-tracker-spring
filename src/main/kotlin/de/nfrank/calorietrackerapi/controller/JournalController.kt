package de.nfrank.calorietrackerapi.controller

import de.nfrank.calorietrackerapi.domain.Journal
import de.nfrank.calorietrackerapi.domain.request.JournalRequest
import de.nfrank.calorietrackerapi.repository.JournalRepository
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/journals")
class JournalController(val journalRepository: JournalRepository) {
    @GetMapping
    fun getAllJournals(): ResponseEntity<List<Journal>> {
        val journals = journalRepository.findAll()
        return ResponseEntity.ok(journals)
    }

    @GetMapping("/{id}")
    fun getJournalById(@PathVariable("id") id: String): ResponseEntity<Journal> {
        val journal = journalRepository.findOneById(ObjectId(id))
        return ResponseEntity.ok(journal)
    }

    @PostMapping
    fun createJournal(@RequestBody request: JournalRequest): ResponseEntity<Journal> {
        val journal = journalRepository.save(Journal(
            user = request.user,
            dailyRecords = request.dailyRecords
        ))
        return ResponseEntity(journal, HttpStatus.CREATED)
    }
}