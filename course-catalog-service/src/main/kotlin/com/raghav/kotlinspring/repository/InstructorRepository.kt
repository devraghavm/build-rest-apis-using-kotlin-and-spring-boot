package com.raghav.kotlinspring.repository

import com.raghav.kotlinspring.entity.Instructor
import org.springframework.data.repository.CrudRepository

interface InstructorRepository : CrudRepository<Instructor, Int> {

}
