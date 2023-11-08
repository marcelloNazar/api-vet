package vet.center.api.domain.cirurgia;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vet.center.api.atendimento.Atendimento;
import vet.center.api.atendimento.AtendimentoRepository;
import vet.center.api.atendimento.AtendimentoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CirurgiaService {

    @Autowired
    private CirurgiaRepository cirurgiaRepository;
    @Autowired
    private AtendimentoService atendimentoService;
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    private CirurgiaResponseDTO convertToDto(Cirurgia cirurgia) {
        CirurgiaResponseDTO dto = new CirurgiaResponseDTO();
        BeanUtils.copyProperties(cirurgia, dto);
        return dto;
    }

    public CirurgiaResponseDTO createCirurgia(CirurgiaDTO cirurgiaDTO) {
        Cirurgia cirurgia = new Cirurgia();
        Atendimento atendimento = atendimentoService.getAtendimentoById(cirurgiaDTO.getAtendimentoId());
        cirurgia.setAtendimento(atendimento);
        BeanUtils.copyProperties(cirurgiaDTO, cirurgia, "atendimentoId");
        atendimento.getCirurgias().add(cirurgia);
        cirurgia = cirurgiaRepository.save(cirurgia);
        return convertToDto(cirurgia);
    }

    public Cirurgia getCirurgiaById(Long id) {
        return cirurgiaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cirurgia não encontrado"));
    }

    public void deleteCirurgia(Long id) {
        Cirurgia cirurgia = getCirurgiaById(id);
        Atendimento atendimento = atendimentoService.getAtendimentoById(cirurgia.getAtendimento().getId());
        if (atendimento.getCirurgias() != null) {
            // Verifique se a cirurgia a ser removida está na lista
            if (atendimento.getCirurgias().contains(cirurgia)) {
                // Subtraia o valor da cirurgia do total do atendimento
                BigDecimal valorDaCirurgia = cirurgia.getValor();
                BigDecimal totalAtualDoAtendimento = atendimento.getTotal();

                // Verifique se o total do atendimento não é nulo
                if (totalAtualDoAtendimento != null) {
                    BigDecimal novoTotalDoAtendimento = totalAtualDoAtendimento.subtract(valorDaCirurgia);
                    atendimento.setTotal(novoTotalDoAtendimento);
                }

                // Remova a cirurgia da lista
                atendimento.getCirurgias().remove(cirurgia);

                 atendimentoRepository.save(atendimento);
            }
        }
        cirurgiaRepository.delete(getCirurgiaById(id));
    }

    public List<CirurgiaResponseDTO> getCirurgiasByAtendimentoId(Long atendimentoId) {
        return cirurgiaRepository.findByAtendimentoId(atendimentoId).stream().map(this::convertToDto).collect(Collectors.toList());
    }
    
}
