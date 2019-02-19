package br.com.api.gurgel.apirecognition.service.impl;

import br.com.api.gurgel.apirecognition.service.DetectTextService;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class DetectTextServiceImpl implements DetectTextService {

    @Autowired
    private AmazonRekognition amazonRekognition;

    @Override
    public Flux<TextDetection> readOnImage(String imgPath) {
        ByteBuffer byteBuffer;

        File source = new File(imgPath);

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(source))){
            byteBuffer = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));

            DetectTextRequest request = new DetectTextRequest()
                    .withImage(new Image()
                            .withBytes(byteBuffer));

            DetectTextResult result = amazonRekognition.detectText(request);
            List<TextDetection> textDetections = result.getTextDetections();

            System.out.println("Detected lines and words for " + imgPath);
            for (TextDetection text: textDetections) {

                System.out.println("Detected: " + text.getDetectedText());
                System.out.println("Confidence: " + text.getConfidence().toString());
                System.out.println("Id : " + text.getId());
                System.out.println("Parent Id: " + text.getParentId());
                System.out.println("Type: " + text.getType());
                System.out.println();
            }

            return Flux.fromIterable(textDetections);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }


        throw new IllegalArgumentException("Erro no caminho da imagem");
    }
}
