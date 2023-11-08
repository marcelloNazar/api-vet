package vet.center.api.domain.produto;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Long id, Produto produtoDetails) {
        Produto produto = getProdutoById(id);

        if (produtoDetails.getNome() != null) {
            produto.setNome(produtoDetails.getNome());
        }
        if (produtoDetails.getValor() != null) {
            produto.setValor(produtoDetails.getValor());
        }
        if (produtoDetails.getDescricao() != null) {
            produto.setDescricao(produtoDetails.getDescricao());
        }


        return produtoRepository.save(produto);
    }

    public Page<Produto> getAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public void deleteProduto(Long id) {
        produtoRepository.delete(getProdutoById(id));
    }

    public List<Produto> getProdutosByIds(List<Long> ids) {return (List<Produto>) produtoRepository.findAllById(ids);}
}
