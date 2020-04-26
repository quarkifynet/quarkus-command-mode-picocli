package org.acme.getting.started;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.acme.getting.started.command.GreetingCommand;
import org.acme.getting.started.command.QuarkusCommand;
import org.apache.maven.shared.utils.cli.CommandLineUtils;
import org.jboss.logging.Logger;
import picocli.CommandLine;

import javax.inject.Inject;

@QuarkusMain
public class GreetingApplication implements QuarkusApplication {
    public static final Logger LOGGER = Logger.getLogger(GreetingApplication.class);

    @Inject
    QuarkusCommand command;

    @Inject
    GreetingCommand greetingCommand;

    @Override
    public int run(String... args) throws Exception {
        if (args.length == 0) {
            Quarkus.waitForExit();
            return 0;
        }
        if (args.length == 1) {
            args = CommandLineUtils.translateCommandline(args[0]);
        }
        return new CommandLine(command)
                .addSubcommand("greet", greetingCommand)
                .execute(args);
    }

    public static void main(String[] args) {
        Quarkus.run(GreetingApplication.class, args);
    }
}