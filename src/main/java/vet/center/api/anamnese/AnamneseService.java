package vet.center.api.anamnese;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vet.center.api.atendimento.AtendimentoService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnamneseService {
    @Autowired
    private AnamneseRepository anamneseRepository;
    @Autowired
    private AtendimentoService atendimentoService;

    public Anamnese createAnamnese(AnamneseDTO anamneseDTO) {
        Anamnese anamnese = new Anamnese();

        anamnese.setData(LocalDateTime.now());

        BeanUtils.copyProperties(anamneseDTO, anamnese, "data");

        return anamneseRepository.save(anamnese);
    }

    public Anamnese updateAnamnese(Long id, AnamneseDTO anamneseDTO) {
        Anamnese anamnese = getAnamneseById(id);
        BeanUtils.copyProperties(anamneseDTO, anamnese);

        return anamneseRepository.save(anamnese);
    }

    public Page<Anamnese> getAllAnamneses(Pageable pageable) {return anamneseRepository.findAll(pageable);}

    public Anamnese getAnamneseById(Long id) {
        return anamneseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Anamnese não encontrada"));
    }

    public void deleteAnamnese(Long id) {anamneseRepository.delete(getAnamneseById(id));}

    public Anamnese getAnamnesesByAtendimentoId(Long atendimentoId) {
        return anamneseRepository.findAnamneseByAtendimentoId(atendimentoId);
    }

}
