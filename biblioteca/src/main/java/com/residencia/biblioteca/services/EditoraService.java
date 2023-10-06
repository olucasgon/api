package com.residencia.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.residencia.biblioteca.entities.Editora;
import com.residencia.biblioteca.repositories.EditoraRepository;

@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepo;
	
	public List<Editora> ListarEditoras(){
		return editoraRepo.findAll();
	}
	
	public Editora buscarEditorPorId(Integer id) {
		return editoraRepo.findById(id).get();
	}
	
	public Editora salvarEditora(Editora editora) {
		return editoraRepo.save(editora);
	}
	
	public Editora atualizarEditora(Editora editora) {
		return editoraRepo.save(editora);
	}
	
	public void deletarEditora(Editora editora) {
	  editoraRepo.delete(editora);
	}
}
