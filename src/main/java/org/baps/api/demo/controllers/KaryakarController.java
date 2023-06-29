package org.baps.api.demo.controllers;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.baps.api.demo.common.utils.FieldsUtil;
import org.baps.api.demo.exceptions.DataNotFoundException;
import org.baps.api.demo.models.entities.Karyakar;
import org.baps.api.demo.services.KaryakarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@SuppressFBWarnings({"EI_EXPOSE_REP2"})
@RequiredArgsConstructor
@RestController
@Tag(name = "kms_database")
@RequestMapping("/karyakars")
public class KaryakarController {
    private final KaryakarService karyakarService;
    // private final ObjectWrapper wrapper;

    @Operation(
        summary = "Get Karyakar (using filters)",
        description = """
            Get Demo API description
            """
    )
    @GetMapping
    public ResponseEntity<List<Karyakar>> listKaryakars(
        @RequestParam(value = "filter", required = false) final String filter,
        @RequestParam(value = "fields", required = false) final String fields) {
        final List<Karyakar> karykarList = filter != null ? karyakarService.search(filter) : karyakarService.listKaryakars();
        // final List<Karyakar> demoModelList = wrapper.demoListToDemoModelList(demoList);
        karykarList.stream().forEach(c -> selectFields(c, fields));
        return ResponseEntity.ok(karykarList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Karyakar> getKaryakar(final @PathVariable Long id) {
        final Karyakar karyakar = karyakarService.getKaryakar(id)
            .orElseThrow(() -> new DataNotFoundException("No karyakar found for id: " + id));
        // final KaryakarModel demoModel = w.demoToKaryakarModel(demo);
        return ResponseEntity.ok(karyakar);
    }

    @PostMapping
    public ResponseEntity<Karyakar> createKaryakar(final @RequestBody Karyakar karyakar) {
        // final Karyakar demo = demoMapper.demoModelToKaryakar(karyakar);
        final Karyakar createdKaryakar = karyakarService.createKaryakar(karyakar);
        createdKaryakar.setCreated_at(Instant.now());
        createdKaryakar.setLast_modified_at(Instant.now());
        // final KaryakarModel createdKaryakarModel = demoMapper.demoToKaryakarModel(createdKaryakar);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdKaryakar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKarykar(final @PathVariable Long id) {
        karyakarService.deleteKaryakar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Karyakar> updateKaryakar(final @PathVariable Long id,
                                                final @RequestBody Karyakar karaykarRequest) {
        final Karyakar karyakar = karyakarService.updateKarykar(id, karaykarRequest);
        return ResponseEntity.ok(karyakar);
    }


    private <T> void selectFields(final T source, final String fields) {
        FieldsUtil.setFields(source, getFields(fields));
    }

    private List<String> getFields(final String fields) {
        return !StringUtils.hasLength(fields) ? Collections.emptyList() : Arrays.asList(fields.split("\\,"));
    }
}
