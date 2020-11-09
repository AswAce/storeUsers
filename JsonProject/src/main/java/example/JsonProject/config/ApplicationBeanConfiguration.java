package example.JsonProject.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import example.JsonProject.utils.FIleIotImpl;
import example.JsonProject.utils.FileIot;
import example.JsonProject.utils.ValidationUtil;
import example.JsonProject.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtilImpl validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public FIleIotImpl fileIot() {
        return new FIleIotImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();
    }
}
