package vet.center.api.estetica;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vet.center.api.domain.animal.AnimalService;
import vet.center.api.domain.produto.Produto;
import vet.center.api.domain.produto.ProdutoService;
import vet.center.api.domain.proprietario.ProprietarioService;
import vet.center.api.domain.servico.Servico;
import vet.center.api.domain.servico.ServicoService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EsteticaService {
    @Autowired
    private EsteticaRepository esteticaRepository;
    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ServicoService servicoService;


    public Estetica createEstetica(EsteticaDTO esteticaDTO) {
        Estetica estetica = new Estetica();

        estetica.setProprietario(proprietarioService.getProprietarioById(esteticaDTO.getProprietarioId()));
        estetica.setAnimal(animalService.getAnimalById(esteticaDTO.getAnimalId()));

        BigDecimal totalProdutos = BigDecimal.ZERO;
        if (esteticaDTO.getProdutosIds() != null) {
            List<Produto> produtos = produtoService.getProdutosByIds(esteticaDTO.getProdutosIds());
            estetica.setProdutos(produtos);
            totalProdutos = produtos.stream()
                    .filter(p -> p.getValor() != null)
                    .map(Produto::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        BigDecimal totalServicos = BigDecimal.ZERO;
        if (esteticaDTO.getServicosIds() != null) {
            List<Servico> servicos = servicoService.getServicosByIds(esteticaDTO.getServicosIds());
            estetica.setServicos(servicos);
            totalServicos = servicos.stream()
                    .filter(s -> s.getValor() != null)
                    .map(Servico::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        BigDecimal total = totalProdutos.add(totalServicos);
        estetica.setTotal(total);

        BeanUtils.copyProperties(esteticaDTO, estetica, "veterinarioId", "animalId", "produtosIds", "servicosIds");

        return esteticaRepository.save(estetica);
    }

    public Estetica updateEstetica(Long id, EsteticaDTO esteticaDTO) {
        Estetica estetica = getEsteticaById(id);

        if (esteticaDTO.getProprietarioId() != null) {
            estetica.setProprietario(proprietarioService.getProprietarioById(esteticaDTO.getProprietarioId()));
        }
        if (esteticaDTO.getAnimalId() != null) {
            estetica.setAnimal(animalService.getAnimalById(esteticaDTO.getAnimalId()));
        }

        BigDecimal totalProdutos = BigDecimal.ZERO;
        if (esteticaDTO.getProdutosIds() != null) {
            List<Produto> produtos = produtoService.getProdutosByIds(esteticaDTO.getProdutosIds());
            estetica.setProdutos(produtos);
            totalProdutos = produtos.stream()
                    .filter(p -> p.getValor() != null)
                    .map(Produto::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        BigDecimal totalServicos = BigDecimal.ZERO;
        if (esteticaDTO.getServicosIds() != null) {
            List<Servico> servicos = servicoService.getServicosByIds(esteticaDTO.getServicosIds());
            estetica.setServicos(servicos);
            totalServicos = servicos.stream()
                    .filter(s -> s.getValor() != null)
                    .map(Servico::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        BigDecimal total = totalProdutos.add(totalServicos);
        estetica.setTotal(total);

        if (esteticaDTO.getRecomendacaoConsulta() != null) {
            estetica.setRecomendacaoConsulta(esteticaDTO.getRecomendacaoConsulta());
        }
        if (esteticaDTO.getObservacao() != null) {
            estetica.setObservacao(esteticaDTO.getObservacao());
        }
        if (esteticaDTO.getTemperamento() != null) {
            estetica.setTemperamento(esteticaDTO.getTemperamento());
        }
        if (esteticaDTO.getSedativo() != null) {
            estetica.setSedativo(esteticaDTO.getSedativo());
        }
        if (esteticaDTO.getOuvido() != null) {
            estetica.setOuvido(esteticaDTO.getOuvido());
        }
        if (esteticaDTO.getPele() != null) {
            estetica.setPele(esteticaDTO.getPele());
        }
        if (esteticaDTO.getEctoparasitas() != null) {
            estetica.setEctoparasitas(esteticaDTO.getEctoparasitas());
        }
        if (esteticaDTO.getMedicacao() != null) {
            estetica.setMedicacao(esteticaDTO.getMedicacao());
        }
        if (esteticaDTO.getHoraTermino() != null) {
            estetica.setHoraTermino(esteticaDTO.getHoraTermino());
        }

        return esteticaRepository.save(estetica);
    }

    public Page<Estetica> getAllEstetica(Pageable pageable) {
        return esteticaRepository.findAll(pageable);
    }

    public Estetica getEsteticaById(Long id) {
        return esteticaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Serviço de estetica não encontrado"));
    }

    public void deleteEstetica(Long id) {esteticaRepository.delete(getEsteticaById(id));}
}
