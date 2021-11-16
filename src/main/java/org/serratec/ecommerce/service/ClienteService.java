package org.serratec.ecommerce.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.dto.ClienteDTO;
import org.serratec.ecommerce.dto.ClienteInserirDTO;
import org.serratec.ecommerce.exception.ClienteException;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<ClienteDTO> listar() {
        List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente : clientes) {
            ClienteDTO dto = new ClienteDTO(cliente);
            clientesDTO.add(dto);
        }
        return clientesDTO;
    }

    public ClienteDTO inserir(ClienteInserirDTO clienteInserirDTO) throws ClienteException {
    	Cliente clienteEmail = clienteRepository.findByEmail(clienteInserirDTO.getEmail());
    	Cliente clienteCpf = clienteRepository.findByCpf(clienteInserirDTO.getCpf());
    	Cliente clienteNomeUsuario = clienteRepository.findByNomeUsuario(clienteInserirDTO.getNomeUsuario());
    	Cliente cliente = new Cliente();
		if(clienteEmail != null) {
			throw new ClienteException("Este email já existe! Insira outro");
		}
		cliente.setEmail(clienteInserirDTO.getEmail());
		cliente.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
		cliente.setTelefone(clienteInserirDTO.getTelefone());
		cliente.setDataNascimento(clienteInserirDTO.getDataNascimento());
		cliente.setNumero(clienteInserirDTO.getNumero());
		cliente.setComplemento(clienteInserirDTO.getComplemento());
		cliente.setEndereco(clienteInserirDTO.getEndereco());
		if(clienteCpf != null) {
			throw new ClienteException("Este cpf já existe! Insira outro");
		}
		cliente.setCpf(clienteInserirDTO.getCpf());
		if(clienteNomeUsuario != null) {
			throw new ClienteException("Este nome de usuário já existe! Insira outro");
		}
		cliente.setNomeUsuario(clienteInserirDTO.getNomeUsuario());
		cliente.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
		cliente = clienteRepository.save(cliente);
		return new ClienteDTO(cliente);
    }
	
    public Page<ClienteDTO> buscarPorNome(String paramNome, Pageable pageable) {
		Page<ClienteDTO> clientes = clienteRepository.findByNomeCompletoContainingIgnoreCase(paramNome, pageable);
		return clientes;
	}
	
	public ClienteDTO atualizar(Long id, ClienteInserirDTO clienteInserirDTO) {
		if (!clienteRepository.existsById(id)) {
			return null;
		}
    	Cliente cliente = new Cliente();
		cliente.setEmail(clienteInserirDTO.getEmail());
		cliente.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
		cliente.setTelefone(clienteInserirDTO.getTelefone());
		cliente.setDataNascimento(clienteInserirDTO.getDataNascimento());
		cliente.setEndereco(clienteInserirDTO.getEndereco());
		cliente.setCpf(clienteInserirDTO.getCpf());
		cliente.setNumero(clienteInserirDTO.getNumero());
		cliente.setNomeUsuario(clienteInserirDTO.getNomeUsuario());
		cliente.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
		cliente = clienteRepository.save(cliente);
		return new ClienteDTO(cliente);
	}
	
	public Boolean deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			return false;
		}
		clienteRepository.deleteById(id);
		return true;
	}
	
	public Cliente buscarPorId(Long id) {
		Optional <Cliente> opCliente = clienteRepository.findById(id);
		Cliente cliente = new Cliente(); 
	
		if(opCliente.isPresent()) {
			cliente = opCliente.get();
		} else {
			cliente = null;
		}
		return cliente;
	}

}