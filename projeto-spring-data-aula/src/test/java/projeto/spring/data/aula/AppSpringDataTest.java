package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	@Test
	public void testInsert() {
		System.out.println("Iniciou spring com sucesso");
	}
	
	@Test
	public void testeInsert() {

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("javaavancado@isaacmartiniano.com");
		usuarioSpringData.setIdade(34);
		usuarioSpringData.setLogin("isaacam517");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("O Cara");
		
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuarios Cadastrados -> " + interfaceSpringDataUser.count());
		
	}
	
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getLogin());
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getId());
			System.out.println("Nome : " + telefone.getUsuarioSpringData().getNome());
		}
	}
	
	@Test
	public void testeConsultaTodos() {
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println("------------------------------------------");
			
		}
		
	}
	
	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData> usuarioSpringData  = interfaceSpringDataUser.findById(3L);
	
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setNome("Iury Gabriel");
		
		interfaceSpringDataUser.save(data);
			
	}
	
	@Test
	public void testeDelete() {
		
		interfaceSpringDataUser.deleteById(4L);
			
	}
	
	@Test
	public void testeDeleteConsultado() {
		
		Optional<UsuarioSpringData> usuarioSpringData  = interfaceSpringDataUser.findById(2L);
		interfaceSpringDataUser.delete(usuarioSpringData.get());
			
	}
	
		
	@Test
	public void testeConsulaNome() {
		
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Islly");
		
		for (UsuarioSpringData usuarioSpringData : list) {
			
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println("------------------------------------------");
			
		}
			
	}
	
	
	//BUSCA EXATAMENTE COMO ESTA NO BANCO
	@Test
	public void testeConsultaNomeParam() {
		
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Iury Gabriel");
		
			
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println("------------------------------------------");
			
			
	}
	
	@Test
	public void testeDeletePorNome(){
		interfaceSpringDataUser.deletePorNome("Usuario para Deletar");
	}
	
	@Test
	public void testeUpdateEmailPorNome(){
		interfaceSpringDataUser.updateEmailPorNome("ewerlline@gmail.com", "Ewerlline");
	}
	
	
	
	@Test
	public void testeInsertTelefone(){
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(7L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Casa");
		telefone.setNumero("8133334444");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
	}

}
