package br.com.gc.sociotorcedor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @Class Modelo que representa o socio torcedor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Document(collection = "socios-torcedores")
public class SocioTorcedor implements Serializable {

    private static final long serialVersionUID = 8720666564621491777L;

    @Id
    private String id;

    @JsonIgnoreProperties
    @NotNull(message = "O nome é de preenchimento obrigatório.")
    private String nome;

    @JsonIgnoreProperties
    @NotNull(message = "O e-mail é de preenchimento obrigatório.")
    @Email(message = "O e-mail informado é invalido.")
    private String email;

    @JsonIgnoreProperties
    @JsonProperty("data-nascimento")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "A data nascimento é de preenchimento obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonIgnoreProperties
    @JsonProperty("time-coracao")
    @NotNull(message = "O meu time do coração é de preenchimento obrigatório.")
    private TimeCoracao timeCoracao;

    public List<Campanha> campanhas;

}
