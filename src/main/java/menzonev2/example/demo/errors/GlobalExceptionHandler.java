package menzonev2.example.demo.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleDatabaseErrors(Throwable e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject(e.getMessage());

        return modelAndView;
    }

}
