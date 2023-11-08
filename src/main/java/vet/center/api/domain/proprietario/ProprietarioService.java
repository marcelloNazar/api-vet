package vet.center.api.domain.proprietario;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vet.center.api.domain.endereco.Endereco;

@Service
public class ProprietarioService {
    @Autowired
    private ProprietarioRepository proprietarioRepository;

    public Proprietario createProprietario(Proprietario proprietario) {return proprietarioRepository.save(proprietario);}

    public Proprietario updateProprietario(Long id, Proprietario proprietarioDetails) {
        Proprietario proprietario = getProprietarioById(id);
        if (proprietarioDetails.getNome() != "") {
            proprietario.setNome(proprietarioDetails.getNome());
        }
        if (proprietarioDetails.getTelefone() != "") {
            proprietario.setTelefone(proprietarioDetails.getTelefone());
        }
        if (proprietarioDetails.getTelefone1() != "") {
            proprietario.setTelefone1(proprietarioDetails.getTelefone1());
        }
        if (proprietarioDetails.getTelefone2() != "") {
            proprietario.setTelefone2(proprietarioDetails.getTelefone2());
        }
        if (proprietarioDetails.getCpf() != "") {
            proprietario.setCpf(proprietarioDetails.getCpf());
        }
        if (proprietarioDetails.getNascimento() != "") {
            proprietario.setNascimento(proprietarioDetails.getNascimento());
        }
        if (proprietarioDetails.getSexo() != "") {
            proprietario.setSexo(proprietarioDetails.getSexo());
        }
        if (proprietarioDetails.getNomeMae() != "") {
            proprietario.setNomeMae(proprietarioDetails.getNomeMae());
        }
        if (proprietarioDetails.getDescricao() != "") {
            proprietario.setDescricao(proprietarioDetails.getDescricao());
        }
        if (proprietarioDetails.getEndereco() != null) {
            Endereco enderecoDetails = proprietarioDetails.getEndereco();
            Endereco endereco = proprietario.getEndereco();

            if (endereco == null) {
                proprietario.setEndereco(enderecoDetails);
            } else {
                if (enderecoDetails.getRua() != "") {
                    endereco.setRua(enderecoDetails.getRua());
                }
                if (enderecoDetails.getNumero() != "") {
                    endereco.setNumero(enderecoDetails.getNumero());
                }
                if (enderecoDetails.getCidade() != "") {
                    endereco.setCidade(enderecoDetails.getCidade());
                }
                if (enderecoDetails.getUf() != "") {
                    endereco.setUf(enderecoDetails.getUf());
                }
                if (enderecoDetails.getCep() != "") {
                    endereco.setCep(enderecoDetails.getCep());
                }
                if (enderecoDetails.getBairro() != "") {
                    endereco.setBairro(enderecoDetails.getBairro());
                }
                if (enderecoDetails.getComplemento() != "") {
                    endereco.setComplemento(enderecoDetails.getComplemento());
                }
            }
        }
        return proprietarioRepository.save(proprietario);
    }

    public Page<Proprietario> getAllProprietarios(Pageable pageable) {return proprietarioRepository.findAll(pageable);}

    public Proprietario getProprietarioById(Long id) {
        return proprietarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Proprietario não encontrado"));
    }

    public void deleteProprietario(Long id) {proprietarioRepository.delete(getProprietarioById(id));}
}
