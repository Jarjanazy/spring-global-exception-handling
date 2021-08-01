package jalil.demo.springglobalexceptionhandle;

import org.springframework.stereotype.Service;

@Service
public class TestService
{
    public String testMethod1(){
        try{
            buggyMethod();
            return "Done!";
        }catch (RuntimeException e){
            return "An error happened!";
        }
    }

    public String testMethod2(){
        buggyMethod();
        return "Done!";
    }

    private void buggyMethod(){
        throw new RuntimeException();
    }
}
