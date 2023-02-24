package de.nfrank.calorietrackerapi.domain.request

class JournalRequest (
    val user: String,
    val dailyRecords: List<String>
)