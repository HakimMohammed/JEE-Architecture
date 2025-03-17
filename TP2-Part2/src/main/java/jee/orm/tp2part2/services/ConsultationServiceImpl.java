package jee.orm.tp2part2.services;

import jakarta.transaction.Transactional;
import jee.orm.tp2part2.entities.Consultation;
import jee.orm.tp2part2.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public Consultation save(Consultation consultation) {
        consultation.setId(UUID.randomUUID().toString());
        return consultationRepository.save(consultation);
    }
}
