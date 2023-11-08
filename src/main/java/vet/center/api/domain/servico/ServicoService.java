package vet.center.api.domain.servico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public Servico createServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico updateServico(Long id, Servico servicoDetails) {
        Servico servico = getServicoById(id);

        if (servicoDetails.getNome() != null) {
            servico.setNome(servicoDetails.getNome());
        }
        if (servicoDetails.getValor() != null) {
            servico.setValor(servicoDetails.getValor());
        }
        if (servicoDetails.getDescricao() != null) {
            servico.setDescricao(servicoDetails.getDescricao());
        }

        return servicoRepository.save(servico);
    }

    public Page<Servico> getAllServicos(Pageable pageable) {
        return servicoRepository.findAll(pageable);
    }

    public Servico getServicoById(Long id) {
        return servicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Servico não encontrado"));
    }

    public void deleteServico(Long id) {servicoRepository.delete(getServicoById(id));}

    public List<Servico> getServicosByIds(List<Long> ids) {return (List<Servico>) servicoRepository.findAllById(ids);}
}
