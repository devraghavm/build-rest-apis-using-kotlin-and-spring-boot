package com.raghav.kotlinspring.controller

import com.raghav.kotlinspring.dto.CourseDto
import com.raghav.kotlinspring.entity.Course
import com.raghav.kotlinspring.repository.CourseRepository
import com.raghav.kotlinspring.repository.InstructorRepository
import com.raghav.kotlinspring.util.PostgreSQLContainerInitializer
import com.raghav.kotlinspring.util.courseEntityList
import com.raghav.kotlinspring.util.instructorEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
//@Testcontainers
class CourseControllerIntgTest : PostgreSQLContainerInitializer() {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    /*    companion object {

            @Container
            val postgresDB = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:13-alpine")).apply {
                withDatabaseName("testdb")
                withUsername("postgres")
                withPassword("secret")
            }

            @JvmStatic
            @DynamicPropertySource
            fun properties(registry: DynamicPropertyRegistry) {
                registry.add("spring.datasource.url", postgresDB::getJdbcUrl)
                registry.add("spring.datasource.username", postgresDB::getUsername)
                registry.add("spring.datasource.password", postgresDB::getPassword)
            }
        }*/


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
    fun addCourse() {
        val instructor = instructorRepository.findAll().first()
        val courseDto =
            CourseDto(null, "Build Restful APIs using Kotlin and SpringBoot", "Development", instructor!!.id)
        val savedCourseDto = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedCourseDto!!.id != null
        }
    }

    @Test
    fun retrieveAllCourses() {
        val courseDtos = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(CourseDto::class.java)
            .returnResult()
            .responseBody

        println("courseDtos: $courseDtos")
        Assertions.assertEquals(3, courseDtos!!.size)
    }

    @Test
    fun retrieveAllCourses_ByName() {
        val uri = UriComponentsBuilder.fromUriString("/v1/courses")
            .queryParam("course_name", "SpringBoot")
            .toUriString()
        val courseDtos = webTestClient
            .get()
            .uri(uri)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(CourseDto::class.java)
            .returnResult()
            .responseBody

        println("courseDtos: $courseDtos")
        Assertions.assertEquals(2, courseDtos!!.size)
    }

    @Test
    fun updateCourse() {
        val instructor = instructorRepository.findAll().first()
        //existing course
        val course = Course(
            null,
            "Build RestFul APis using SpringBoot and Kotlin",
            "Development",
            instructor
        )
        courseRepository.save(course)
        //courseId
        //Updated CourseDTO
        val updatedCourseDto = CourseDto(
            null,
            "Build RestFul APis using SpringBoot and Kotlin1",
            "Development",
            course.instructor!!.id

        )

        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{courseId}", course.id)
            .bodyValue(updatedCourseDto)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("Build RestFul APis using SpringBoot and Kotlin1", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        val instructor = instructorRepository.findAll().first()
        //existing course
        val course = Course(
            null,
            "Build RestFul APis using SpringBoot and Kotlin",
            "Development",
            instructor
        )
        courseRepository.save(course)
        //courseId
        val updatedCourse = webTestClient
            .delete()
            .uri("/v1/courses/{courseId}", course.id)
            .exchange()
            .expectStatus().isNoContent
    }
}