package dcms.be.swd.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("dcms.be.swd")
@EnableJpaRepositories("dcms.be.swd")
@EnableTransactionManagement
public class DomainConfig {
}
