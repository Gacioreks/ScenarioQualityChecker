package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.FileReader;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/files/{text}")
public class FileReaderController {
    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ArrayList get(@PathVariable String text) {

        // log the parameters
        logger.debug(text);
        //logger.debug(Arrays.toString(file));

        // perform the transformation, you should run your logic here, below is just a silly example
        FileReader reader = new FileReader();
        return reader.read(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ArrayList post(@PathVariable String text) {

        // log the parameters
        logger.debug(text);
        //logger.debug(Arrays.toString(file));

        // perform the transformation, you should run your logic here, below is just a silly example
        FileReader reader = new FileReader();
        //file=reader.read(text);
        return reader.read(text);
    }
}
