package com.residencia.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.entities.Aluno;
import com.residencia.biblioteca.repositories.AlunoRepository;

//Temos que colocar em todos os services
@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepo;

	public List<Aluno> listarAlunos() {
		return alunoRepo.findAll();
	}

	public Aluno buscarAlunoPorId(Integer id) {
		// ESTES SÂO ALGUMAS OPÇÔES
//		return alunoRepo.findById(id).get();

//		Optional<Aluno> alunoBanco = alunoRepo.findById(id);
//		if(alunoBanco.isPresent())
//			return alunoBanco.get();
//		else
//			return null;

		return alunoRepo.findById(id).orElse(null);
	}

	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);
	}

	public Aluno atualizarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);
	}

	public Boolean deletarAluno(Aluno aluno) {
		if(aluno == null) {
			return false;
		}
		Aluno alunoExistente = buscarAlunoPorId(aluno.getNumeroMatriculaAluno());

		if(alunoExistente == null) {
			return false;
		}
		alunoRepo.delete(aluno);

		Aluno alunoContinuaExistindo = buscarAlunoPorId(aluno.getNumeroMatriculaAluno());

		if(alunoContinuaExistindo == null) {
			return true;
		}
		return false;

	}
}
