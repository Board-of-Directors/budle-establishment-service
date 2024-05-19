package ru.nsu.fit.directors.establishmentservice.configuration;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.fit.directors.establishmentservice.enums.CuisineCountry;
import ru.nsu.fit.directors.establishmentservice.model.Category;
import ru.nsu.fit.directors.establishmentservice.utils.EnumUtils;

@Configuration
public class MapperConfiguration {
    private final AbstractConverter<CuisineCountry, String> convertCuisine = new AbstractConverter<>() {
        @Override
        protected String convert(CuisineCountry source) {
            return source.getReadableName();
        }
    };
    private final AbstractConverter<String, CuisineCountry> convertToCuisine = new AbstractConverter<>() {

        @Override
        protected CuisineCountry convert(String source) {
            return EnumUtils.parseEnum(source, CuisineCountry.class);
        }
    };
    private final AbstractConverter<Category, String> convertCategory = new AbstractConverter<>() {
        @Override
        protected String convert(Category source) {
            return source.getReadableName();
        }
    };
    private final AbstractConverter<String, Category> convertToCategory = new AbstractConverter<>() {

        @Override
        protected Category convert(String source) {
            return Category.getEnumByValue(source);
        }
    };

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.addConverter(convertCuisine);
        mapper.addConverter(convertCategory);
        mapper.addConverter(convertToCuisine);
        mapper.addConverter(convertToCategory);
        return mapper;
    }
}
