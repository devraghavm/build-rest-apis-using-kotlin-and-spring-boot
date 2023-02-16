package com.raghav.kotlinspring.service

import com.raghav.kotlinspring.dto.InstructorDto
import com.raghav.kotlinspring.entity.Instructor
import com.raghav.kotlinspring.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(val instructorRepository: InstructorRepository) {
    fun createInstructor(instructorDto: InstructorDto): InstructorDto {
        val instructorEntity = instructorDto.let {
            Instructor(it.id, it.name)
        }
        instructorRepository.save(instructorEntity)
        return instructorEntity.let {
            InstructorDto(it.id, it.name)
        }
    }

    fun findByInstructorId(instructorId: Int): Optional<Instructor> {
        return instructorRepository.findById(instructorId)
    }

}
