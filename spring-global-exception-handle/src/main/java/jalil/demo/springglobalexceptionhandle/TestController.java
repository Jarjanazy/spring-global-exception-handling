package jalil.demo.springglobalexceptionhandle;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

@RestController @RequiredArgsConstructor
public class TestController
{
    @GetMapping("/buggyMethod")
    public String testMeWithExceptionHandler(){
        try{
            buggyMethod();
            return "Done!";
        }catch (RuntimeException e){
            return "An error happened!";
        }
    }
    @GetMapping("/potentialBuggyMethod")
    public String testMeWithoutExceptionHandler(){
        undercoverBuggyMethod();
        return "Done!";
    }

    @PostMapping("/invalidParamMethod")
    public String testForInvalidParam(){
        buggyParameters();
        return "Done";
    }
    private void buggyMethod(){
        throw new RuntimeException();
    }
    private void undercoverBuggyMethod(){
        throw new RuntimeException("oops");
    }
    private void buggyParameters(){
        throw new InvalidParameterException();
    }
}
