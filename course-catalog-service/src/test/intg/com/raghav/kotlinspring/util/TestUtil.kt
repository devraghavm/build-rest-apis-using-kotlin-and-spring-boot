package com.raghav.kotlinspring.util

import com.raghav.kotlinspring.dto.CourseDto
import com.raghav.kotlinspring.dto.InstructorDto
import com.raghav.kotlinspring.entity.Course
import com.raghav.kotlinspring.entity.Instructor

fun courseEntityList() = listOf(
    Course(
        null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development"
    ),
    Course(
        null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development",
    ),
    Course(
        null,
        "Wiremock for Java Developers", "Development",
    )
)

fun courseDto(
    id: Int? = null,
    name: String = "Build RestFul APis using Spring Boot and Kotlin",
    category: String = "Dilip Sundarraj",
//    instructorId: Int? = 1
) = CourseDto(
    id,
    name,
    category,
//    instructorId
)

fun instructorDto(
    id: Int? = null,
    name: String = "Raghav Medapati",
) = InstructorDto(
    id,
    name,
)

fun courseEntityList(instructor: Instructor? = null) = listOf(
    Course(
        null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development",
        instructor
    ),
    Course(
        null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development", instructor
    ),
    Course(
        null,
        "Wiremock for Java Developers", "Development",
        instructor
    )
)

fun instructorEntity(name: String = "Dilip Sundarraj") = Instructor(null, name)