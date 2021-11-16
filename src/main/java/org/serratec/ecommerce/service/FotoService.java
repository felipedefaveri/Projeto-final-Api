package org.serratec.ecommerce.service;

import java.io.IOException;
import java.util.Optional;

import org.serratec.ecommerce.domain.Foto;
import org.serratec.ecommerce.domain.Produto;
import org.serratec.ecommerce.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    public Foto inserir(Produto produto, MultipartFile file) throws IOException {
        Foto foto = new Foto();
        foto.setDados(file.getBytes());
        foto.setNome(file.getName());
        foto.setTipo(file.getContentType());
        foto.setProduto(produto);
        return fotoRepository.save(foto);
    }

    public Foto buscar(Long id) {
        Optional<Foto> foto = fotoRepository.findById(id);
        if(foto.isPresent()) {
            return foto.get();
        }else {
            return null;
        }
    }
}