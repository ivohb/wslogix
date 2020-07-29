package com.wslogix.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wslogix.model.Acesso;
import com.wslogix.model.Empresa;
import com.wslogix.model.Modulo;
import com.wslogix.model.Perfil;
import com.wslogix.model.Processo;
import com.wslogix.model.Usuario;
import com.wslogix.model.UsuarioLogix;

@Service
public class DBService {

	@Autowired
	private EmpresaService empService;
	@Autowired
	private UsuarioLogixService userService;
	@Autowired
	private PerfilService perSevice;
	@Autowired
	private UsuarioService uService;
	@Autowired
	private ModuloService mService;
	@Autowired
	private ProcessoService pService;
	@Autowired
	private AcessoService aService;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void insereDados() throws ParseException {
		
		Empresa empresa = new Empresa();
		empresa.setId("01");
		empresa.setNome("Microchips informática");
		empresa.setCnpj("052.213.326/0001-09");
		empresa.setDatFundacao(new java.util.Date());
		empService.insert(empresa);
		
		UsuarioLogix user = new UsuarioLogix();
		user.setId("admlog");
		user.setEmpresaPadrao("01");
		user.setNome("Adiministrador do sistema");
		user.setEmail("cantorivojose@gmail.com");
		userService.insert(user); 
		
		Perfil perfil = new Perfil();
		perfil.setNome("Adiministrador do sistema");
		perfil.setSituacao("A");
		perSevice.insert(perfil);
		
		Usuario u = new Usuario();
		u.setCodigo("ivo");
		u.setCodigoErp("admlog");
		u.setCpfCnpj("00167409808");
		u.setEmail("ivohb.me@gmail.com");
		u.setNome("Ivo Barbosa");
		u.setPerfil(1);
		u.setPessoa("F");
		u.setSenha(pe.encode("ivo"));
		u.setSituacao("A");
		uService.insert(u);
		
		Modulo m = new Modulo();
		m.setIcone("/assets/icon/edi-cliente.png");
		m.setPath("edi-cliente");
		m.setTitulo("EDI Cliente");
		m.setSituacao("A");
		mService.insert(m);
		
		m = new Modulo();
		m.setIcone("/assets/icon/edi-kanban.png");
		m.setPath("edi-kanban");
		m.setTitulo("EDI Kanban");
		m.setSituacao("A");
		mService.insert(m);

		m = new Modulo();
		m.setIcone("/assets/icon/inventario.png");
		m.setPath("inventario");
		m.setTitulo("Inventário");
		m.setSituacao("A");
		mService.insert(m);

		m = new Modulo();
		m.setIcone("/assets/icon/configuracao.png");
		m.setPath("configuracao");
		m.setTitulo("Configuração");
		m.setSituacao("A");
		mService.insert(m);

		Processo p = new Processo();
		p.setIcone("/assets/icon/pedido-compra.png");
		p.setModulo(1);
		p.setPath("edi-cliente-preparar");
		p.setTitulo("Preparação");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/integrar.png");
		p.setModulo(1);
		p.setPath("edi-cliente-integrar");
		p.setTitulo("Integração");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/iniciar.png");
		p.setModulo(2);
		p.setPath("edi-kanban-iniciar");
		p.setTitulo("Iniciar");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/consultar.png");
		p.setModulo(2);
		p.setPath("edi-kanban-consultar");
		p.setTitulo("Consultar");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/integrar.png");
		p.setModulo(2);
		p.setPath("edi-kanban-integrar");
		p.setTitulo("Integrar");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/rotativo.png");
		p.setModulo(3);
		p.setPath("inventario-rotativo");
		p.setTitulo("Rotativo");
		p.setSituacao("A");
		pService.insert(p);
		
		p = new Processo();
		p.setIcone("/assets/icon/usuario.png");
		p.setModulo(4);
		p.setPath("usuarios");
		p.setTitulo("Usuário");
		p.setSituacao("A");
		pService.insert(p);
		
		p = new Processo();
		p.setIcone("/assets/icon/modulo.png");
		p.setModulo(4);
		p.setPath("modulos");
		p.setTitulo("Módulo");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/processo.png");
		p.setModulo(4);
		p.setPath("processos");
		p.setTitulo("Processo");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/acesso.png");
		p.setModulo(4);
		p.setPath("acessos");
		p.setTitulo("Acesso");
		p.setSituacao("A");
		pService.insert(p);

		p = new Processo();
		p.setIcone("/assets/icon/perfil.png");
		p.setModulo(4);
		p.setPath("perfis");
		p.setTitulo("Perfil");
		p.setSituacao("A");
		pService.insert(p);

		Acesso a = null;
		List<Processo> procs = pService.findAll();
		for (Processo processo : procs) {
			a = new Acesso();
			a.setPerfil(1);
			a.setProcesso(processo.getId());
			aService.insert(a);
		}

	}

	
}
