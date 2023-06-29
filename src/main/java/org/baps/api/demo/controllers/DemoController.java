package org.baps.api.demo.controllers;

import org.baps.api.demo.common.utils.FieldsUtil;
import org.baps.api.demo.exceptions.DataNotFoundException;
import org.baps.api.demo.mappers.DemoMapper;
import org.baps.api.demo.models.DemoModel;
import org.baps.api.demo.models.entities.Demo;
import org.baps.api.demo.services.DemoService;

import lombok.RequiredArgsConstructor;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@SuppressFBWarnings({"EI_EXPOSE_REP2"})
@RequiredArgsConstructor
@RestController
@Tag(name = "Demo")
@RequestMapping("/demo")
public class DemoController {

    private final DemoService demoService;
    private final DemoMapper demoMapper;

    @Operation(
        summary = "Get Demo",
        description = """
            Get Demo API description
            """
    )
    @GetMapping
    public ResponseEntity<List<DemoModel>> listDemo(
        @RequestParam(value = "filter", required = false) final String filter,
        @RequestParam(value = "fields", required = false) final String fields) {
        final List<Demo> demoList = filter != null ? demoService.search(filter) : demoService.listDemo();
        final List<DemoModel> demoModelList = demoMapper.demoListToDemoModelList(demoList);
        demoModelList.stream().forEach(c -> selectFields(c, fields));
        return ResponseEntity.ok(demoModelList);
    }

    @GetMapping("/{demoId}")
    public ResponseEntity<DemoModel> getDemo(final @PathVariable String demoId) {
        final Demo demo = demoService.getDemo(demoId)
            .orElseThrow(() -> new DataNotFoundException("No demo found for id: " + demoId));
        final DemoModel demoModel = demoMapper.demoToDemoModel(demo);
        return ResponseEntity.ok(demoModel);
    }

    @PostMapping
    public ResponseEntity<DemoModel> createDemo(final @RequestBody DemoModel demoModel) {
        final Demo demo = demoMapper.demoModelToDemo(demoModel);
        final Demo createdDemo = demoService.createDemo(demo);
        final DemoModel createdDemoModel = demoMapper.demoToDemoModel(createdDemo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDemoModel);
    }

    @PatchMapping("/{demoId}")
    public ResponseEntity<DemoModel> updateDemo(final @PathVariable String demoId,
                                                final @RequestBody Map<String, Object> demoModel) {
        final Demo updatedDemo = demoService.updateDemo(demoId, demoModel);
        final DemoModel updatedDemoModel = demoMapper.demoToDemoModel(updatedDemo);
        return ResponseEntity.ok(updatedDemoModel);
    }

    @DeleteMapping("/{demoId}")
    public ResponseEntity<Void> deleteDemo(final @PathVariable String demoId) {
        demoService.deleteDemo(demoId);
        return ResponseEntity.noContent().build();
    }

    private <T> void selectFields(final T source, final String fields) {
        FieldsUtil.setFields(source, getFields(fields));
    }

    private List<String> getFields(final String fields) {
        return !StringUtils.hasLength(fields) ? Collections.emptyList() : Arrays.asList(fields.split("\\,"));
    }
}
