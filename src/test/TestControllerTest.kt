import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@SpringBootTest(webEnviroment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class, MockitoExtension::class)
class TestControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `test for test`() {
        assertEquals(4+1, 5)
    }

    @Test
    fun `Assert status code and content`() {
        mockMvc.get("/").andExpect {
            status { isOk() }
            content { "blog" }
        }
    }
}