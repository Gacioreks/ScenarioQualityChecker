package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.logic.FileReader;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/files/{text}")
public class FileReaderController {
    private static final Logger logger = LoggerFactory.getLogger(FileReaderController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ArrayList<String> get(@PathVariable String text) {

        // log the parameters
        logger.debug(text);

        //FileReader reader = new FileReader();
        return FileReader.read(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ArrayList<String> post(@PathVariable String text, @RequestBody ArrayList<String> s) {

        logger.debug(text);
        //logger.debug(s.title);

        //FileReader reader = new FileReader();
        return FileReader.read(text);
    }
}