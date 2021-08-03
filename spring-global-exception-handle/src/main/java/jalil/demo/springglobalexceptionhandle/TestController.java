package jalil.demo.springglobalexceptionhandle;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private void buggyMethod(){
        throw new RuntimeException();
    }
    private void undercoverBuggyMethod(){
        throw new RuntimeException("oops");
    }

}
