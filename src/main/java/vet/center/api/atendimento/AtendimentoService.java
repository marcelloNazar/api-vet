package vet.center.api.atendimento;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vet.center.api.domain.animal.Animal;
import vet.center.api.domain.animal.AnimalService;
import vet.center.api.domain.cirurgia.Cirurgia;
import vet.center.api.domain.cirurgia.CirurgiaDTO;
import vet.center.api.domain.cirurgia.CirurgiaService;
import vet.center.api.domain.produto.*;
import vet.center.api.domain.proprietario.ProprietarioRepository;
import vet.center.api.domain.proprietario.ProprietarioService;
import vet.center.api.domain.servico.*;
import vet.center.api.user.User;
import vet.center.api.user.UserRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private ProprietarioRepository proprietarioRepository;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AtendimentoProdutoRepository atendimentoProdutoRepository;
    @Autowired
    private AtendimentoServicoRepository atendimentoServicoRepository;

    private ProdutoDTO convertToProdutoDto(AtendimentoProduto atendimentoProduto) {
        ProdutoDTO dto = new ProdutoDTO();
        BeanUtils.copyProperties(atendimentoProduto.getProduto(), dto);
        dto.setQuantidade(atendimentoProduto.getQuantidade());
        return dto;
    }

    private ServicoDTO convertToServicoDto(AtendimentoServico atendimentoServico) {
        ServicoDTO dto = new ServicoDTO();
        BeanUtils.copyProperties(atendimentoServico.getServico(), dto);
        dto.setQuantidade(atendimentoServico.getQuantidade());
        return dto;
    }

    private AtendimentoResponseDTO convertToDto(Atendimento atendimento) {
        AtendimentoResponseDTO dto = new AtendimentoResponseDTO();
        BeanUtils.copyProperties(atendimento, dto);
        dto.setVeterinario(atendimento.getVeterinario());
        dto.setAnimal(atendimento.getAnimal());
        dto.setProprietario(atendimento.getProprietario());

        dto.setProdutos(atendimento.getAtendimentoProdutos().stream()
                .map(this::convertToProdutoDto)
                .collect(Collectors.toList()));

        dto.setServicos(atendimento.getAtendimentoServicos().stream()
                .map(this::convertToServicoDto)
                .collect(Collectors.toList()));

        return dto;
    }

    @Transactional
    public AtendimentoResponseDTO  createAtendimento(AtendimentoDTO atendimentoDTO) {
        Atendimento atendimento = new Atendimento();

        User veterinario = userRepository.findById(atendimentoDTO.getVeterinarioId()).orElseThrow(() -> new UsernameNotFoundException("Veterinario não encontrado: "));
        atendimento.setVeterinario(veterinario);
        Animal animal = animalService.getAnimalById(atendimentoDTO.getAnimalId());
        atendimento.setConcluido(false);
        atendimento.setFinalizado(false);
        atendimento.setPago(false);
        atendimento.setAnimal(animal);
        atendimento.setProprietario(animal.getProprietario());
        atendimento.setData(LocalDate.now());
        atendimento = atendimentoRepository.save(atendimento);

        return convertToDto(atendimento);
    }

    public Page<AtendimentoResponseDTO> getFinancesByMonth(Integer month, Integer year, Pageable pageable) {
        // Se nenhum mês e ano forem informados, usa o mês atual.
        if (month == null || year == null) {
            LocalDate currentDate = LocalDate.now();
            month = currentDate.getMonthValue();
            year = currentDate.getYear();
        }

        // Verifica se o mês e ano informados são válidos.
        if (month < 1 || month > 12 || year < 0) {
            throw new IllegalArgumentException("Mês ou ano inválido.");
        }

        // Cria um objeto YearMonth para o mês e ano informados.
        YearMonth selectedMonth = YearMonth.of(year, month);

        // Calcula o primeiro e o último dia do mês.
        LocalDate startOfMonth = selectedMonth.atDay(1);
        LocalDate endOfMonth = selectedMonth.atEndOfMonth();

        // Chama o repositório para obter as finanças filtradas pelo mês e ano.
        return atendimentoRepository.findAllByDataBetweenAndFinalizadoTrue(startOfMonth, endOfMonth, pageable)
                .map(this::convertToDto);
    }

    public Page<AtendimentoResponseDTO> getFinancesByMonthPago(Integer month, Integer year, Pageable pageable) {
        // Se nenhum mês e ano forem informados, usa o mês atual.
        if (month == null || year == null) {
            LocalDate currentDate = LocalDate.now();
            month = currentDate.getMonthValue();
            year = currentDate.getYear();
        }

        // Verifica se o mês e ano informados são válidos.
        if (month < 1 || month > 12 || year < 0) {
            throw new IllegalArgumentException("Mês ou ano inválido.");
        }

        // Cria um objeto YearMonth para o mês e ano informados.
        YearMonth selectedMonth = YearMonth.of(year, month);

        // Calcula o primeiro e o último dia do mês.
        LocalDate startOfMonth = selectedMonth.atDay(1);
        LocalDate endOfMonth = selectedMonth.atEndOfMonth();

        // Chama o repositório para obter as finanças filtradas pelo mês e ano.
        return atendimentoRepository.findAllByDataBetweenAndPago(startOfMonth, endOfMonth, pageable).map(this::convertToDto);
    }

    public Page<AtendimentoResponseDTO> getFinancesByMonthPagoFalse(Integer month, Integer year, Pageable pageable) {
        // Se nenhum mês e ano forem informados, usa o mês atual.
        if (month == null || year == null) {
            LocalDate currentDate = LocalDate.now();
            month = currentDate.getMonthValue();
            year = currentDate.getYear();
        }

        // Verifica se o mês e ano informados são válidos.
        if (month < 1 || month > 12 || year < 0) {
            throw new IllegalArgumentException("Mês ou ano inválido.");
        }

        // Cria um objeto YearMonth para o mês e ano informados.
        YearMonth selectedMonth = YearMonth.of(year, month);

        // Calcula o primeiro e o último dia do mês.
        LocalDate startOfMonth = selectedMonth.atDay(1);
        LocalDate endOfMonth = selectedMonth.atEndOfMonth();

        // Chama o repositório para obter as finanças filtradas pelo mês e ano.
        return atendimentoRepository.findAllByDataBetweenAndPagoFalseAndFinalizadoTrue(startOfMonth, endOfMonth, pageable)
                .map(this::convertToDto);
    }



    @Transactional
    public AtendimentoResponseDTO  createAtendimentoVeterinario(AtendimentoDTO atendimentoDTO) {
        Atendimento atendimento = new Atendimento();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User veterinario = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        atendimento.setVeterinario(veterinario);
        Animal animal = animalService.getAnimalById(atendimentoDTO.getAnimalId());
        atendimento.setConcluido(false);
        atendimento.setFinalizado(false);
        atendimento.setPago(false);
        atendimento.setAnimal(animal);
        atendimento.setProprietario(animal.getProprietario());
        atendimento.setData(LocalDate.now());
        atendimento = atendimentoRepository.save(atendimento);

        return convertToDto(atendimento);
    }

    @Transactional
    public AtendimentoResponseDTO updateAtendimento(Long id, AtendimentoUpdateDTO atendimentoDTO) {
        Atendimento atendimento = getAtendimentoById(id);

        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();


        atendimentoProdutoRepository.deleteByAtendimento(atendimento);
        atendimentoServicoRepository.deleteByAtendimento(atendimento);

        BigDecimal total = BigDecimal.ZERO;
        for (AtendimentoProdutoDTO produtoDto : atendimentoDTO.getAtendimentoProdutos()) {
            Produto produto = produtoService.getProdutoById(produtoDto.getProdutoId());
            AtendimentoProduto atendimentoProduto = new AtendimentoProduto(atendimento, produto, produtoDto.getQuantidade());
            atendimentoProduto = atendimentoProdutoRepository.save(atendimentoProduto);
            total = total.add(produto.getValor().multiply(new BigDecimal(atendimentoProduto.getQuantidade())));
        }
        for (AtendimentoServicoDTO servicoDto : atendimentoDTO.getAtendimentoServicos()) {
            Servico servico = servicoService.getServicoById(servicoDto.getServicoId());
            AtendimentoServico atendimentoServico = new AtendimentoServico(atendimento, servico, servicoDto.getQuantidade());
            atendimentoServico = atendimentoServicoRepository.save(atendimentoServico);
            total = total.add(servico.getValor().multiply(new BigDecimal(atendimentoServico.getQuantidade())));
        }
        if (atendimento.getCirurgias() != null && !atendimento.getCirurgias().isEmpty()) {
            BigDecimal cirurgiasTotal = atendimento.getCirurgias().stream()
                    .map(Cirurgia::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            total = total.add(cirurgiasTotal);
        }

        atendimento.setTotal(total);
        atendimento.setConcluido(true);


        atendimentoRepository.save(atendimento);

        return convertToDto(atendimento);
    }

    @Transactional
    public AtendimentoResponseDTO  updateAtendimentoAdm(Long id, AtendimentoAdmDTO atendimentoDTO) {
        Atendimento atendimento = getAtendimentoById(id);
        atendimento.setFinalizado(true);
        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(dataAtual);
        atendimento.setDataFechamento(dataFormatada);
        return convertToDto(atendimento);
    }

    public Page<AtendimentoResponseDTO> getAllAtendimentosAdm(Pageable pageable) {
        return atendimentoRepository.findAllByConcluidoFalse(pageable)
                .map(this::convertToDto);
    }
    public Page<AtendimentoResponseDTO> getAllAtendimentosFinalizados(Pageable pageable) {
        return atendimentoRepository.findAllByFinalizadoTrue(pageable)
                .map(this::convertToDto);
    }

    public Page<AtendimentoResponseDTO> getAllAtendimentosConcluidos(Pageable pageable) {
        return atendimentoRepository.findAllByConcluidoTrueAndFinalizadoFalse(pageable)
                .map(this::convertToDto);
    }

    public Page<AtendimentoResponseDTO> getAllAtendimentosPagos(Pageable pageable) {
        return atendimentoRepository.findAllByPagoTrue(pageable)
                .map(this::convertToDto);
    }



    public Page<AtendimentoResponseDTO> getAllAtendimentoFinalizado(Pageable pageable) {
        return atendimentoRepository.findAllByFinalizadoTrue(pageable)
                .map(this::convertToDto);
    }
    public Page<AtendimentoResponseDTO> getAllAtendimentosPagosFalse(Pageable pageable) {
        return atendimentoRepository.findAllByFinalizadoTrueAndPagoFalse(pageable)
                .map(this::convertToDto);
    }

    public Page<AtendimentoResponseDTO> getAllAtendimentos(Pageable pageable) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User veterinario = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
            Long veterinarioId = veterinario.getId();
            return atendimentoRepository.findAllByVeterinarioIdAndConcluidoFalse(veterinarioId, pageable)
                    .map(this::convertToDto);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    public Page<AtendimentoResponseDTO> getAllAtendimentosConcluidosUser(Pageable pageable) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User veterinario = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
            Long veterinarioId = veterinario.getId();
            return atendimentoRepository.findAllByVeterinarioIdAndConcluidoTrueAndFinalizadoFalse(veterinarioId, pageable)
                    .map(this::convertToDto);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    public Atendimento getAtendimentoById(Long id) {
        return atendimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));
    }

    public void deleteAtendimento(Long id) {
        atendimentoRepository.delete(getAtendimentoById(id));
    }
}
