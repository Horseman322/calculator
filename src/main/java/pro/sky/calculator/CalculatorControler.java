package pro.sky.calculator;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.calculator.CalculatorService;

import java.awt.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorControler {

    private final CalculatorService calculatorService;

    public CalculatorControler(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String welcome() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam("num1") int a,
                       @RequestParam("num2") int b){
        return BuildResult(a,b, calculatorService.sum(a, b),"+");
    }

    @GetMapping("/minus")
    public String minus(@RequestParam("num1") int a,
                        @RequestParam("num2") int b){
        return BuildResult(a,b, calculatorService.minus(a, b),"-");
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam("num1") int a,
                           @RequestParam("num2") int b){
        return BuildResult(a,b, calculatorService.multiply(a, b),"*");
    }

    @GetMapping("/divide")
    public String divide(@RequestParam("num1") int a,
                         @RequestParam("num2") int b){
        if (b == 0){
            return "на ноль делить нельзя";
        }
        return BuildResult(a,b, calculatorService.divide(a, b),"/");
    }
    private String BuildResult(int a, int b, Number result, String operation){
        return String.format("%d %s %d = %s",a, operation, b, result.toString());
    }
}