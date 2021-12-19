package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.base.Scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RestController
@RequestMapping("/{text}/count")
public class StepsCountController {
    private static final Logger logger = LoggerFactory.getLogger(Scenario.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text) throws FileNotFoundException {

        // log the parameters
        logger.debug(text);

        Scenario s=new Scenario(text);
        s.Stepscount();

        File myFile = new File("./files/json/steps_count.json");
        Scanner myReader = new Scanner(myFile);
        String out=myReader.nextLine();

        return out;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text, @RequestBody String out) throws FileNotFoundException {

        logger.debug(text);
        logger.debug(out);

        return out;
    }
}