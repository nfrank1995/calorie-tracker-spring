package de.nfrank.calorietrackerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalorieTrackerApiApplication

fun main(args: Array<String>) {
	runApplication<CalorieTrackerApiApplication>(*args)
}
