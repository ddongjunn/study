package hello.typeconveter;

import hello.typeconveter.conveter.IntegerToStringConverter;
import hello.typeconveter.conveter.IpPortToStringConverter;
import hello.typeconveter.conveter.StringToIntegerConverter;
import hello.typeconveter.conveter.StringToIpPortConverter;
import hello.typeconveter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
        //registry.addConverter(new StringToIntegerConverter());
        //registry.addConverter(new IntegerToStringConverter());

        //추가 우선순위 때문에 Converter 주석처리
        registry.addFormatter(new MyNumberFormatter());
    }
}
