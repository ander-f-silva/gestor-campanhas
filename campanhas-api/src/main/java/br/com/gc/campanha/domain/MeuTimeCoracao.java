package br.com.gc.campanha.domain;

/**
 * Enum que representa a estrutura do meu tipo do coração
 */
public enum MeuTimeCoracao {

    CORINTHIANS("597fa106cce6ff25aeea87b8"),
    PALMEIRAS("597fa0edcce6ff25283f42fe"),
    SANTOS("597f6281cce6ff52bed0bb9e"),
    SAO_PAULO("597f6269cce6ff5251a9da0f");

    private String idTimeCoracao;

    MeuTimeCoracao(String idTimeCoracao) {
        this.idTimeCoracao = idTimeCoracao;
    }

    public String getIdTimeCoracao() {
        return idTimeCoracao;
    }

    public static MeuTimeCoracao get(String id) {

        for (MeuTimeCoracao meuTimeCoracao:values()) {
            if(meuTimeCoracao.getIdTimeCoracao().equals(id)) {
                return meuTimeCoracao;
            }
        }

        return null;

    }
}
