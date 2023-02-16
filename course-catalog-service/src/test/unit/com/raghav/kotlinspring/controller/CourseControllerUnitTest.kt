package com.raghav.kotlinspring.controller

import com.ninjasquad.springmockk.MockkBean
import com.raghav.kotlinspring.dto.CourseDto
import com.raghav.kotlinspring.service.CourseService
import com.raghav.kotlinspring.util.courseDto
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseServiceMock: CourseService

    @Test
    fun addCourse() {
        val courseDto = CourseDto(null, "Build Restful APIs using Kotlin and SpringBoot", "Development", 1)
        every { courseServiceMock.addCourse(any()) } returns courseDto(id = 1)
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
    fun addCourse_validation() {
        val courseDto = CourseDto(null, "", "", 1)
        every { courseServiceMock.addCourse(any()) } returns courseDto(id = 1)
        val savedCourseDto = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        assertEquals("CourseDto.category must not be blank, CourseDto.name must not be blank", savedCourseDto)
    }

    @Test
    fun addCourse_runtimeException() {
        val courseDto = CourseDto(null, "Build Restful APIs using Kotlin and SpringBoot", "Development", 1)
        val errorMessage = "Unexpected Error Occurred"
        every { courseServiceMock.addCourse(any()) } throws RuntimeException(errorMessage)
        val savedCourseDto = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().is5xxServerError
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        assertEquals(errorMessage, savedCourseDto)
    }

    @Test
    fun retrieveAllCourses() {
        every { courseServiceMock.retrieveAllCourses(any()) }.returnsMany(
            listOf(
                courseDto(id = 1),
                courseDto(id = 2, name = "Build Reactive Microservices using Spring WebFlux/SpringBoot")
            )
        )
        val courseDtos = webTestClient
            .get()
            .uri("/v1/courses")
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
        //existing course
        /*        val course = Course(
                    null,
                    "Build RestFul APis using SpringBoot and Kotlin", "Development"
                )*/
        every { courseServiceMock.updateCourse(any(), any()) } returns courseDto(
            id = 100,
            name = "Build RestFul APis using SpringBoot and Kotlin1"
        )
        //courseId
        //Updated CourseDTO
        val updatedCourseDto = CourseDto(
            null,
            "Build RestFul APis using SpringBoot and Kotlin1", "Development"
        )

        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{courseId}", 100)
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
        every { courseServiceMock.deleteCourse(any()) } just runs
        val updatedCourse = webTestClient
            .delete()
            .uri("/v1/courses/{courseId}", 100)
            .exchange()
            .expectStatus().isNoContent
    }
}