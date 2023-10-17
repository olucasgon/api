package com.residencia.biblioteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.dto.LivroResumidoDTO;
import com.residencia.biblioteca.entities.Livro;
import com.residencia.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepo;

	public List<Livro> ListarLivros() {
		return livroRepo.findAll();
	}

	public List<LivroResumidoDTO> getLivrosResumidos() {
		List<Livro> livros = livroRepo.findAll();
		List<LivroResumidoDTO> livrosDTO = new ArrayList<>();

		for (Livro livro : livros) {
			LivroResumidoDTO livroResDTO = new LivroResumidoDTO();
			livroResDTO.setCodigoLivro(livro.getCodigoLivro());
			livroResDTO.setNomeLivro(livro.getNomeLivro());
			livroResDTO.setDataLancamento(livro.getDataLancamento());
			livroResDTO.setNomeEditora(livro.getEditora().getNome());
			livrosDTO.add(livroResDTO);
		}

		return livrosDTO;
	}

	public LivroResumidoDTO getLivroResumidoPorId(Integer id) {
		Livro livro = livroRepo.findById(id).orElse(null);

		if (livro != null) {
			LivroResumidoDTO livroResDTO = new LivroResumidoDTO(livro.getCodigoLivro(), livro.getNomeLivro(),
					livro.getDataLancamento(), livro.getEditora().getNome());

			return livroResDTO;

		}

		return null;
	}

	public Livro buscarLivroPorId(Integer id) {
		return livroRepo.findById(id).orElse(null);
	}

	public Livro salvarLivro(Livro livro) {
		return livroRepo.save(livro);
	}

	public Livro atualizarLivro(Livro livro) {
		return livroRepo.save(livro);
	}

	public Boolean deletarLivro(Livro livro) {
		if (livro == null) {
			return false;
		}
		Livro livroExistente = buscarLivroPorId(livro.getCodigoLivro());

		if (livroExistente == null) {
			return false;
		}
		livroRepo.delete(livro);

		Livro livroContinuaExistindo = buscarLivroPorId(livro.getCodigoLivro());

		if (livroContinuaExistindo == null) {
			return true;
		}
		return false;

	}
}
