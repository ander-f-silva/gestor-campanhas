package br.com.gc.campanha.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Class Modelo que representa a campanha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Document(collection = "campanhas")
public class Campanha implements Serializable {

    private static final long serialVersionUID = -6779263115421569853L;

    @Id
    private String id;

    @NotNull(message = "O nome é de preenchimento obrigatório.")
    private String nome;

    @JsonProperty("time-coracao")
    @NotNull(message = "O campo meu time do coração é de preenchimento obrigatório.")
    private TimeCoracao timeCoracao;

    @JsonProperty("data-inicio-vigencia")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "A data inicio é de preenchimento obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicioVigencia;

    @JsonProperty("data-fim-vigencia")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "A data fim é de preenchimento obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFimVigencia;

    /**
     * Adicionar mais um dia na data final da campannha.
     *
     * @param campanhaAtual com os dados que será comparado
     */
    public void adicionarUmDiaParaDataFinal(Campanha campanhaAtual) {
        dataFimVigencia = dataFimVigencia.plusDays(1);

        if (this.dataFimVigencia.isEqual(campanhaAtual.getDataFimVigencia())) {
            dataFimVigencia = dataFimVigencia.plusDays(1);
        }
    }

    /**
     * Adicionar mais um dia na data final da campannha.
     *
     * @return booleano caso a data seja igual
     */
    public boolean validarDatasInicioMaiorDataFim() {
        return dataInicioVigencia.isAfter(dataFimVigencia);
    }
}