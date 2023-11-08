package vet.center.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vet.center.api.atendimento.*;
import vet.center.api.domain.animal.Animal;
import vet.center.api.domain.animal.AnimalService;
import vet.center.api.user.User;
import vet.center.api.user.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AtendimentoServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private AnimalService animalService;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @InjectMocks
    private AtendimentoService atendimentoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAtendimentoVeterinarioNotFound() {
        // Dados de exemplo
        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
        atendimentoDTO.setVeterinarioId(1L);
        atendimentoDTO.setAnimalId(1L);

        // Mock do repositório do usuário (retorna Optional vazio)
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Execução do método de criação do atendimento
        assertThrows(UsernameNotFoundException.class, () -> atendimentoService.createAtendimento(atendimentoDTO));
    }

    @Test
    public void testCreateAtendimentoAnimalNotFound() {
        // Dados de exemplo
        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
        atendimentoDTO.setVeterinarioId(1L);
        atendimentoDTO.setAnimalId(1L);

        // Mock do repositório do usuário
        User veterinarioMock = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(veterinarioMock));

        // Mock do serviço do animal (retorna null)
        when(animalService.getAnimalById(1L)).thenReturn(null);

        // Execução do método de criação do atendimento
        assertThrows(NullPointerException.class, () -> atendimentoService.createAtendimento(atendimentoDTO));
    }
}
