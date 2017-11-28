package br.com.gc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe responsavel por fazer a leitura dos arquivos json
 */
public class LeitorArquivo {

    public <T> T converterJsonToClass(String file, Class<T> clazz) throws IOException {
        final byte[] bytes = converterJsonToBytes(file);
        return new ObjectMapper().readValue(bytes, clazz);
    }

    private byte[] converterJsonToBytes(String file) throws IOException {
        Path pathObject = Paths.get("src/test/resources/campanha/".concat(file));
        return Files.readAllBytes(pathObject);
    }
}
