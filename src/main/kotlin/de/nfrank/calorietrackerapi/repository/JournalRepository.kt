package de.nfrank.calorietrackerapi.repository

import de.nfrank.calorietrackerapi.domain.Journal
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository


interface JournalRepository: MongoRepository<Journal, String>{
    fun findOneById(id: ObjectId): Journal
}