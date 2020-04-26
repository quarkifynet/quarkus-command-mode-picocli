package org.acme.getting.started.command;

import org.acme.getting.started.GreetingService;
import org.jboss.logging.Logger;
import picocli.CommandLine;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.concurrent.Callable;

@Dependent
@CommandLine.Command(name = "greet", mixinStandardHelpOptions = true, description = "Greet person by their name")
public class GreetingCommand implements Callable<Integer> {
    public static final Logger LOGGER = Logger.getLogger(GreetingCommand.class);

    @CommandLine.Option(names = {"--name"}, description = "Specify which user to greet")
    String name;

    @Inject
    GreetingService greetingService;

    @Override
    public Integer call() throws Exception {
        LOGGER.info(greetingService.greeting(name));
        return 0;
    }

}
