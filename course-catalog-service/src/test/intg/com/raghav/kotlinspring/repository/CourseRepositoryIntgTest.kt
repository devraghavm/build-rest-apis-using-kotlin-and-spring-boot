package com.raghav.kotlinspring.repository

import com.raghav.kotlinspring.util.PostgreSQLContainerInitializer
import com.raghav.kotlinspring.util.courseEntityList
import com.raghav.kotlinspring.util.instructorEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.util.stream.Stream

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryIntgTest : PostgreSQLContainerInitializer() {
    @Autowired
    lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        instructorRepository.deleteAll()
        val instructor = instructorEntity()
        instructorRepository.save(instructor)
        val courses = courseEntityList(instructor)
        courseRepository.saveAll(courses)
    }

    @Test
    fun findByNameContaining() {
        val courses = courseRepository.findByNameContaining("SpringBoot")

        assertEquals(2, courses.size)
    }

    @Test
    fun findCoursesByName() {
        val courses = courseRepository.findCoursesByName("SpringBoot")

        assertEquals(2, courses.size)
    }

    @ParameterizedTest
    @MethodSource("courseAndSize")
    fun findCoursesByName_approach2(name: String, expectedSize: Int) {
        val courses = courseRepository.findCoursesByName(name)

        assertEquals(expectedSize, courses.size)
    }

    companion object {
        @JvmStatic
        fun courseAndSize(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("SpringBoot", 2),
                Arguments.arguments("Wiremock", 1)
            )
        }
    }
}