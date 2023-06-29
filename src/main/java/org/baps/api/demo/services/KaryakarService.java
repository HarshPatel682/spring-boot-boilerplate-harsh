package org.baps.api.demo.services;

import static io.github.perplexhub.rsql.RSQLJPASupport.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.baps.api.demo.exceptions.DataNotFoundException;
import org.baps.api.demo.models.entities.Karyakar;
import org.baps.api.demo.repositories.KaryakarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@SuppressFBWarnings({"EI_EXPOSE_REP2"})
@RequiredArgsConstructor
@Slf4j
@Service
public class KaryakarService {
    private final KaryakarRepository karyakarRepository;

    public List<Karyakar> listKaryakars() {
        log.info("Inside listKaryakars");
        return karyakarRepository.findAll();
    }

    public Optional<Karyakar> getKaryakar(final Long id) {
        log.info("Inside getKaryakar : {}", id);
        return karyakarRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Karyakar> search(final String filter) {
        //final Map<String, JoinType> joinHints = Map.of(
        //   "Demo.subentity",
        //   JoinType.INNER
        //);
        return karyakarRepository.findAll(toSpecification(filter, true, null, null));
    }

    public Karyakar createKaryakar(final Karyakar karyakar) {
        log.info("Inside createKaryakar: {}", karyakar);
        return karyakarRepository.save(karyakar);
    }

    public void deleteKaryakar(final Long id) {
        log.info("Inside deleteKaryakar: {}", id);
        karyakarRepository.deleteById(id);
    }

    public Karyakar updateKarykar(final Long id, final Karyakar karyakarRequest) {
        log.info("Inside updateKarykar: {}", karyakarRequest);
        final Karyakar existingKaryakar = getKaryakar(id)
            .orElseThrow(() -> new DataNotFoundException("No demo found for id: " + id));

        //There is probably a better way to do this, buuuuut it works
        if(karyakarRequest.getFirstName() != null) {
            existingKaryakar.setFirstName(karyakarRequest.getFirstName());
        }

        if(karyakarRequest.getLastName() != null) {
            existingKaryakar.setLastName(karyakarRequest.getLastName());
        }

        if(karyakarRequest.getAge() > 0) {
            existingKaryakar.setAge(karyakarRequest.getAge());
        }

        existingKaryakar.setCity(karyakarRequest.getCity());

        existingKaryakar.setGender(karyakarRequest.getGender());

        existingKaryakar.setLast_modified_at(Instant.now());

        return karyakarRepository.save(existingKaryakar);  
    }

}
