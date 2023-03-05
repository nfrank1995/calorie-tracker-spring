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

data class DailyRecord(
    val meals: List<Meal>
)

data class Meal (
    val ingredients: List<Ingredient>,
    val type: Type
)

enum class Type {
    BREAKFAST, LUNCH, DINNER, SNACK
}

data class Ingredient(
    val name: String,
    val unit: Unit,
    val amount: Int,
    val kcal: Int,
    val category: Category,
)

enum class Unit {
    PC, G, ML
}

enum class Category {
    FRUIT, VEGETABLE, MEAT, FISH, CARBS, JUNKFOOD
}