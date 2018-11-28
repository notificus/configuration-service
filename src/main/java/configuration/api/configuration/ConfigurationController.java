package configuration.api.configuration;

import configuration.api.Routes;
import configuration.service.configuration.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


@RestController
public class ConfigurationController {
    @Autowired
    ConfigurationService configurationService;

    @RequestMapping(value = Routes.USER_CONFIGURATIONS_ROUTE, method = GET)
    public ResponseEntity<ConfigurationContract> getConfiguration(@PathVariable String cip) {
        return new ResponseEntity<>(ConfigurationContractTranslator.translateTo(
                configurationService.getConfiguration(cip)), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.USER_CONFIGURATIONS_ROUTE, method = PUT)
    public ResponseEntity<ConfigurationContract> updateConfiguration(@PathVariable String cip, @RequestBody ConfigurationContract configurationContract) {
        return new ResponseEntity<>(ConfigurationContractTranslator.translateTo(
                configurationService.updateConfiguration(
                        cip, ConfigurationContractTranslator.translateFrom(configurationContract))), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.USER_CONFIGURATIONS_ROUTE, method = DELETE)
    public void deleteConfiguration(@PathVariable String cip) {
        configurationService.deleteConfiguration(cip);
    }
}
