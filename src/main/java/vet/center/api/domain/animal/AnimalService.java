package vet.center.api.domain.animal;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vet.center.api.domain.proprietario.Proprietario;
import vet.center.api.domain.proprietario.ProprietarioService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ProprietarioService proprietarioService;

    private AnimalResponseDTO convertToDto(Animal animal) {
        AnimalResponseDTO dto = new AnimalResponseDTO();
        BeanUtils.copyProperties(animal, dto);
        dto.setProprietario(proprietarioService.getProprietarioById(animal.getProprietario().getId()));
        dto.setIdade(calcularIdade(animal.getNascimento()));
        return dto;
    }

    public AnimalResponseDTO createAnimal(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setProprietario(proprietarioService.getProprietarioById(animalDTO.getProprietarioId()));
        animal.setData(LocalDate.now());
        BeanUtils.copyProperties(animalDTO, animal, "proprietarioId");
        animal = animalRepository.save(animal);
        return convertToDto(animal);
    }

    public AnimalResponseDTO updateAnimal(Long id, AnimalDTO animalDetails) {
        Animal animal = getAnimalById(id);
        animal.setData(LocalDate.now());

        if (animalDetails.getNome() != null) {
            animal.setNome(animalDetails.getNome());
        }
        if (animalDetails.getEspecie() != null) {
            animal.setEspecie(animalDetails.getEspecie());
        }
        if (animalDetails.getRaca() != null) {
            animal.setRaca(animalDetails.getRaca());
        }
        if (animalDetails.getSexo() != null) {
            animal.setSexo(animalDetails.getSexo());
        }
        if (animalDetails.getPeso() != null) {
            animal.setPeso(animalDetails.getPeso());
            animal.setData(LocalDate.now());
        }
        if (animalDetails.getNascimento() != null) {
            animal.setNascimento(animalDetails.getNascimento());
        }

        if (animalDetails.getCor() != null) {
            animal.setCor(animalDetails.getCor());
        }
        if (animalDetails.getTemperamento() != null) {
            animal.setTemperamento(animalDetails.getTemperamento());
        }
        if (animalDetails.getCastrado() != null) {
            animal.setCastrado(animalDetails.getCastrado());
        }
        if (animalDetails.getProprietarioId() != null) {
            Proprietario proprietario = proprietarioService.getProprietarioById(animalDetails.getProprietarioId());
            animal.setProprietario(proprietario);
        }
        animal = animalRepository.save(animal);
        return convertToDto(animal);
    }

    public Page<AnimalResponseDTO> getAllAnimals(Pageable pageable) {
        return animalRepository.findAll(pageable).map(this::convertToDto);
    }


    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));
    }

    public void deleteAnimal(Long id) {
        animalRepository.delete(getAnimalById(id));
    }

    public List<AnimalResponseDTO> getAnimalsByProprietarioId(Long proprietarioId) {
        return animalRepository.findByProprietarioId(proprietarioId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private String calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return null;
        }

        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);
        int anos = periodo.getYears();
        int meses = periodo.getMonths();
        int dias = periodo.getDays();

        StringBuilder idade = new StringBuilder();
        if (anos > 0) {
            idade.append(anos).append(" ano").append(anos > 1 ? "s" : "");
        }
        if (meses > 0) {
            if (idade.length() > 0) {
                idade.append(", ");
            }
            idade.append(meses).append(" mês").append(meses > 1 ? "es" : "");
        }


        return idade.toString();
    }
}
