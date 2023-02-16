package com.raghav.kotlinspring.controller

import com.raghav.kotlinspring.dto.InstructorDto
import com.raghav.kotlinspring.service.InstructorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/instructors")
@Validated
class InstructorController(val instructorService: InstructorService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstructor(@RequestBody @Valid instructorDto: InstructorDto) =
        instructorService.createInstructor(instructorDto)

}