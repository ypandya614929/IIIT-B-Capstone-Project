package bookmyconsultation.notificationservice.config;

import com.amazonaws.services.s3.model.ObjectMetadata;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


@Configuration
public class Freemarker {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig(){
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27);
        TemplateLoader loader = new ClassTemplateLoader(this.getClass(), "/templates");
        configuration.setTemplateLoader(loader);
        FreeMarkerConfigurer freeMarkerConfig = new FreeMarkerConfigurer();
        freeMarkerConfig.setConfiguration(configuration);
        return freeMarkerConfig;
    }

    @Bean
    ObjectMetadata objectMetadata(){
        return new ObjectMetadata();
    }

}
