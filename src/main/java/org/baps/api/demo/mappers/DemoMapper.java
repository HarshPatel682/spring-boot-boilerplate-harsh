package org.baps.api.demo.mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

import org.baps.api.demo.models.DemoModel;
import org.baps.api.demo.models.entities.Demo;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    builder = @Builder(disableBuilder = true),
    nullValueMappingStrategy = RETURN_DEFAULT
)
public interface DemoMapper {

    DemoModel demoToDemoModel(Demo demo);

    List<DemoModel> demoListToDemoModelList(List<Demo> demoList);

    Demo demoModelToDemo(DemoModel demoModel);

    List<Demo> demoModelListToDemoList(List<DemoModel> demoModelList);

}
