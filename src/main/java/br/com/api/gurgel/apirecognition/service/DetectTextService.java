package br.com.api.gurgel.apirecognition.service;

import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.TextDetection;
import reactor.core.publisher.Flux;

public interface DetectTextService {

    Flux<TextDetection> readOnImage(String imgPath);
}
