/**
 * 
 */
package br.com.acsp.curso.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.acsp.curso.domain.Aeronave;
import br.com.acsp.curso.service.AeronaveService;

/**
 * @author eduardobregaida
 * 
 */
@Controller
public class AeronaveController extends AbstractController {

	private static final Log logger = LogFactory.getLog(AeronaveController.class);

	@Autowired
	private AeronaveService aeronaveService;

	@ModelAttribute("aeronave")
	public Aeronave getAeronave() {
		return new Aeronave();
	}

	@RequestMapping("/aeronaves")
	public String lista(ModelMap map) {
		logger.info("AeronaveController: lista");
        map.put("aeronaveMenu", "active");
        map.put("listaDeAeronaves", aeronaveService.listarOrdenadoPorModelo());
        return "aeronave/lista";
	}

	@RequestMapping(value = "/aeronave/{id}/apaga", method = RequestMethod.GET)
    @ResponseBody
	public String exclui(@PathVariable("id") Long id) {
		logger.info("AeronaveController: exclui");
		aeronaveService.excluirPorId(id);
		return "SUCCESS";
	}

	@RequestMapping(value = "/aeronave/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Aeronave buscaPorId(@PathVariable("id") Long id, ModelMap modelMap) {
		return aeronaveService.obtemPorId(id);
	}

	@RequestMapping(value = "/aeronave", method = RequestMethod.POST)
	public String salvarOuAtualizar(@Valid Aeronave aeronave, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.put("aeronave", aeronave);
            return "aeronave/formulario";
        }
        logger.info("AeronaveController: salva");
        final String msgOperacao = getMensagemOperacao(aeronave.getId());
        aeronaveService.salvar(aeronave);
        map.put("msgSucesso", "Aeronave " + aeronave.getCertificadoMatricula() + " " + msgOperacao + " com exito.");
        return "success";
    }
	
	//TODO Combo aninhada agenda
	@RequestMapping(value = "/aeronavesDisponiveis/{dataReserva}", method = RequestMethod.GET)
	@ResponseBody
	public List<Aeronave> listarAeronavesDisponiveis(@PathVariable("dataReserva") Date dataReserva){
		return null;
	}

	
}