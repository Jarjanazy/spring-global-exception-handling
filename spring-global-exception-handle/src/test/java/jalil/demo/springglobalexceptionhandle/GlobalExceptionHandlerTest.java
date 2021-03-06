package jalil.demo.springglobalexceptionhandle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TestController.class)
public class GlobalExceptionHandlerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenAGetRequestToBuggyEndPoint_DetectErrorMessage() throws Exception
    {
        MvcResult mvcResult = mockMvc
                .perform(get("/buggyMethod"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "An error happened!");
    }
    @Test
    public void givenAGetRequestToPotentialBuggyMethod_DetectErrorMessage() throws Exception
    {
        MvcResult mvcResult = mockMvc
                .perform(get("/potentialBuggyMethod"))
                .andExpect(status().is5xxServerError())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "An internal error has happened, please report the incident");
    }
    @Test
    public void givenAPostRequestToBuggyMethod_DetectInvalidParameterErrorMessage() throws Exception
    {
        MvcResult mvcResult = mockMvc
                .perform(post("/invalidParamMethod"))
                .andExpect(status().isBadRequest())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "This is a BAD REQUEST");
    }
}
