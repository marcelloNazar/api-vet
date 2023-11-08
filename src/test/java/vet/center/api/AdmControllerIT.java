package vet.center.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import vet.center.api.atendimento.AtendimentoResponseDTO;
import vet.center.api.atendimento.AtendimentoService;
import vet.center.api.config.AuthService;
import vet.center.api.controller.AdmController;
import vet.center.api.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AdmController.class)
public class AdmControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AtendimentoService atendimentoService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AdmController admController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
