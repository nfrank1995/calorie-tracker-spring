package de.nfrank.calorietrackerapi.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("journals")
data class Journal (
    @JsonSerialize(using = ToStringSerializer::class)
    @Id
    val id: ObjectId = ObjectId.get(),
    val user: String,
    val dailyRecords: List<String>,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now()
)