package jalil.demo.springglobalexceptionhandle;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
public class TestController
{
    private final TestService testService;

    @GetMapping("/handlesError")
    public String testMeWithExceptionHandler(){
        return testService.testMethod1();
    }

    @GetMapping("/doesntHandlesError")
    public void testMeWithoutExceptionHandler(){
        testService.testMethod2();
    }

}
