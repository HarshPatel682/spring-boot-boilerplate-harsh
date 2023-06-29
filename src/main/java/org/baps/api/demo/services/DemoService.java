package org.baps.api.demo.services;

import static io.github.perplexhub.rsql.RSQLJPASupport.toSpecification;

import org.baps.api.demo.exceptions.DataNotFoundException;
import org.baps.api.demo.models.entities.Demo;
import org.baps.api.demo.repositories.DemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.ValidationException;

@SuppressFBWarnings({"EI_EXPOSE_REP2"})
@RequiredArgsConstructor
@Slf4j
@Service
public class DemoService {

    private final DemoRepository demoRepository;
    private final ObjectMapper objectMapper;

    public List<Demo> listDemo() {
        log.info("Inside listDemo");
        return demoRepository.findAll();
    }

    public Optional<Demo> getDemo(final String demoId) {
        log.info("Inside getDemo : {}", demoId);
        return demoRepository.findById(demoId);
    }

    @Transactional(readOnly = true)
    public List<Demo> search(final String filter) {
        //final Map<String, JoinType> joinHints = Map.of(
        //   "Demo.subentity",
        //   JoinType.INNER
        //);
        return demoRepository.findAll(toSpecification(filter, true, null, null));
    }

    public Demo createDemo(final Demo demo) {
        log.info("Inside createDemo: {}", demo);
        return demoRepository.save(demo);
    }

    public void deleteDemo(final String id) {
        log.info("Inside deleteDemo: {}", id);
        demoRepository.deleteById(id);
    }

    public Demo updateDemo(final String id, final Map<String, Object> demoRequest) {
        log.info("Inside updateDemo: {}", demoRequest);
        final Demo existingDemo = getDemo(id)
            .orElseThrow(() -> new DataNotFoundException("No demo found for id: " + id));
        try {
            final Demo demoToUpdate = objectMapper.readerForUpdating(existingDemo)
                .readValue(objectMapper.writeValueAsBytes(demoRequest));
            return demoRepository.save(demoToUpdate);
        } catch (final IOException e) {
            throw new ValidationException("Invalid request data", e);
        }
    }
}
