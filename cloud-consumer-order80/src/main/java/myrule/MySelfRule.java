package myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.ribbon.Ribbon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
//    Ribbon 规则设置
    @Bean
    public IRule myRule(){
        return  new RandomRule();   //自定义为随机
    }
}
