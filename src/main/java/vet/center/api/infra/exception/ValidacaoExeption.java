package vet.center.api.infra.exception;

public class ValidacaoExeption extends RuntimeException {
    public ValidacaoExeption(String mensagem) {
        super(mensagem);
    }
}
