package br.com.gc.sociotorcedor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Class Modelo que representa o meu time do coracao
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Document(collection = "times-coracoes")
public class TimeCoracao implements Serializable {

    @Id
    private String id;

    @JsonIgnoreProperties
    private MeuTimeCoracao meuTimeCoracao;

}
