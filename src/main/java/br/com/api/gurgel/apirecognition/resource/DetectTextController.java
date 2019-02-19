package br.com.api.gurgel.apirecognition.resource;

import br.com.api.gurgel.apirecognition.model.Image;
import br.com.api.gurgel.apirecognition.service.DetectTextService;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.TextDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api")
public class DetectTextController {

    @Autowired
    private DetectTextService detectTextService;

    @GetMapping("/readtextimage")
    public Flux<TextDetection> readOnImage(@RequestBody Image image){
        return detectTextService.readOnImage(image.getPath());
    }
}
