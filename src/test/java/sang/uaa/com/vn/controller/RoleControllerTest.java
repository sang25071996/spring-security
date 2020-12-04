//package sang.uaa.com.vn.controller;
//
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import sang.uaa.com.vn.entites.Role;
//import sang.uaa.com.vn.repository.RoleRepository;
//
//@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)
//public class RoleControllerTest {
//	
//	
//	@Autowired
//	private RoleRepository roleRepository;
//	
//	@Test
//	public void getRoleById() {
//		Role role = new Role();
//		role.setId(1L);
//		role.setName("CC");
//		when(roleRepository.findByRoleId(10L)).thenReturn(role);
//	}
//}
