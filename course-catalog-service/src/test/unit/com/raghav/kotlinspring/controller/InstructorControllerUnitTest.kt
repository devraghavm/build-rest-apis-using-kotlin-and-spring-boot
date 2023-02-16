package com.raghav.kotlinspring.controller

import com.ninjasquad.springmockk.MockkBean
import com.raghav.kotlinspring.dto.InstructorDto
import com.raghav.kotlinspring.service.InstructorService
import com.raghav.kotlinspring.util.instructorDto
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [InstructorController::class])
@AutoConfigureWebTestClient
class InstructorControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var instructorServiceMock: InstructorService

    @Test
    fun createInstructor() {
        val instructorDto = InstructorDto(null, "Raghav Medapati")
        every { instructorServiceMock.createInstructor(any()) } returns instructorDto(id = 1)
        val savedInstructorDto = webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedInstructorDto!!.id != null
        }
    }

    @Test
    fun createInstructor_validation() {
        val instructorDto = InstructorDto(null, "")
        every { instructorServiceMock.createInstructor(any()) } returns instructorDto(id = 1)
        val savedInstructorDto = webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDto)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals(
            "InstructorDto.name must not be blank",
            savedInstructorDto
        )
    }
}