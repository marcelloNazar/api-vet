package vet.center.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vet.center.api.domain.endereco.Endereco;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private Role role;
    private String nome;
    private String email;
    private String telefone;
    private String crmv;
    private Endereco endereco;

}
