package projeto.spring.data.aula;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser InterfaceSpringDataUser;
	
	@Test
	public void testeInsert() {

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("javaavancado@isaacmartiniano.com");
		usuarioSpringData.setIdade(34);
		usuarioSpringData.setLogin("isaacam517");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Ewerlline");
		
		InterfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuarios Cadastrados -> " + InterfaceSpringDataUser.count());
		
	}
	
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = InterfaceSpringDataUser.findById(2L);
		
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getLogin());
	}
	
	@Test
	public void testeConsultaTodos() {
		
		Iterable<UsuarioSpringData> lista = InterfaceSpringDataUser.findAll();
		
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
		
		Optional<UsuarioSpringData> usuarioSpringData  = InterfaceSpringDataUser.findById(3L);
	
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setNome("Iury Gabriel");
		
		InterfaceSpringDataUser.save(data);
			
	}
	
	@Test
	public void testeDelete() {
		
		InterfaceSpringDataUser.deleteById(4L);
			
	}
	
	@Test
	public void testeDeleteConsultado() {
		
		Optional<UsuarioSpringData> usuarioSpringData  = InterfaceSpringDataUser.findById(2L);
		InterfaceSpringDataUser.delete(usuarioSpringData.get());
			
	}
	
		
	
	
	
	
	

}
