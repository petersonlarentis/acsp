package br.com.acsp.curso.domain.agenda;

import br.com.acsp.curso.common.AbstractService;
import br.com.acsp.curso.domain.aeronave.Aeronave;
import br.com.acsp.curso.domain.aeronave.AeronaveRepository;
import br.com.acsp.curso.domain.horario.Horario;
import br.com.acsp.curso.domain.horario.HorarioRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * @author pedrosa
 */

@Service
public class AgendaService extends AbstractService<Agenda, String> {

    public static final int INTERVALO_DEFAULT = 2;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private AeronaveRepository aeronaveRepository;

    @Autowired
    private AgendaRepository repository;

    public Collection<Agenda> obterAgendamentosDoMes(Date dataRef) {
        final Date inicioMes = DateUtils.truncate(dataRef, Calendar.MONTH);
        final Date fimDoMes = DateUtils.addMonths(inicioMes, 1);
        return ((AgendaRepository) repository).findByDataReservaBetween(inicioMes, fimDoMes);
    }

    public Collection<Horario> obterHorariosLivresPorAeronave(Date dataReserva, String idAeronave) {
        Collection<Horario> horarios = horarioRepository.findAll();
        Aeronave aeronave = aeronaveRepository.findOne(idAeronave);
        Collection<Agenda> agendas = ((AgendaRepository) repository).findByDataReservaAndAeronave(dataReserva, aeronave);

        //Esses horarios serão sempre pre-determinados?
        //O intervalo será sempre 2h?
        for (Agenda agenda : agendas) {
            Horario agendaHorario = agenda.getHorario();
            if (horarios.contains(agendaHorario)) {
                int horas_a_remover = agenda.getQtdeHoras() / INTERVALO_DEFAULT + agenda.getQtdeHoras() % INTERVALO_DEFAULT;
                int horas_ja_removidas = 0;
                boolean encontrou = false;

                for (Iterator<Horario> horarioIterator = horarios.iterator(); horarioIterator.hasNext(); ) {
                    Horario horario = horarioIterator.next();
                    if ((horario.equals(agendaHorario) || encontrou) && horas_ja_removidas < horas_a_remover) {
                        encontrou = true;
                        horas_ja_removidas++;
                        horarioIterator.remove();
                    }
                }
            }
        }

        return horarios;
    }

    @Override
    public MongoRepository getRepository() {
        return (MongoRepository) this.repository;
    }

    @Override
    public String getSortAttribute() {
        return "dataReserva";
    }
}
