package com.parimal.firstProject.SpringBootFirstProject;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Below annotation is required, because this loads the application context which will be needed to dependency injection.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SpringBootFirstProjectApplicationTests {

    @Autowired
    private SampleService service;

    @BeforeEach
    void setupBefore(){     // executes before each test-method
        log.info("Starting the method before each test method");
    }

    @AfterEach
    void setUpAfterEach(){      // executes after each test-method
        log.info("Starting the method after each test method");
    }

    @BeforeAll
    static void setUpBeforeAll(){      // executes only once, before all tests and should be static.
        log.info("Starting before all tests, executes only once");
//        service = new SampleService();
    }

    @AfterAll
    static void setUpAfterAll(){        // executes only once, after all tests and should be static.
        log.info("Starting after all tests, executes only once");
    }

    @Test
    @Order(value = 1)
    void contextLoad(){
        service.meth();
    }

    @Test
    @DisplayName(value = "Test-1")
    @Order(value = 2)
    void test1(){
        log.info("This is test1");
    }

    @Test
    @DisplayName("Test-2")
    @Order(value = 3)
    void test2(){
        log.info("This is test2");
    }

    // Now, testing the custom methods.
    private int testTwoNum(int a, int b){
        return a + b;
    }

    @Test
    @Order(value = 4)
    void testAddTow(){
        Assertions.assertEquals(10, testTwoNum(5, 5));
        // Now, using the AssertJ.
            // Number tests.
        assertThat(testTwoNum(10, 20))      // imported the static method.
                .isEqualTo(30);
    }

    @Test
    @Order(value = 5)
    void testString(){
        // String tests.
        assertThat("Hello World")
                .isEqualTo("Hello World")
                .contains("Hl");
    }

    @Test
    @Order(value = 6)
    void testBoolean(){
        // Boolean tests.
        assertThat(true).isFalse();
    }

    @Test
    @Order(value = 8)
    void testList(){
        // Lists or Arrays tests.
    }

    @Test
    @Order(value = 7)
    void testExceptionHandle(){
        assertThatThrownBy(() -> divideTwoNum(10, 2))
                .isInstanceOf(ArithmeticException.class);
    }

    double divideTwoNum(int a, int b){
        try{
            return (double) a/b;
        } catch (ArithmeticException e){
            log.error("The arithmetic exception from the test is: " + e.getLocalizedMessage());
            throw e;
        }
    }

}

