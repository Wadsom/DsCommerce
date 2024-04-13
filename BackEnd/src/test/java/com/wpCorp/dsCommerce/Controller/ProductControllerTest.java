package com.wpCorp.dsCommerce.Controller;

import com.wpCorp.dsCommerce.DTO.ProductDTO;
import net.bytebuddy.asm.Advice;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class ProductControllerTest {

    private ProductDTO dto;


    @BeforeEach
    void setUp() {
        baseURI = "http://localhost:8080";

    }

    @Test
    void findAllPageade() {
        //ARRANGE

        //ACT
        when().get("/products").then().statusCode(200).body((Matcher<?>) dto);

        //ASSERTIONS

    }
}