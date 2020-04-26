package org.acme.getting.started.command;

import org.acme.getting.started.GreetingService;
import picocli.CommandLine;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@CommandLine.Command(name = "greet", mixinStandardHelpOptions = true, description = "Greet person by their name")
public class GreetingCommand implements Runnable {
    @CommandLine.Option(names = {"-n", "--name"}, description = "Specify which user to greet")
    String name;

    @Inject
    GreetingService greetingService;

    @Override
    public void run() {
        System.out.println(greetingService.greeting(name));
    }
}
