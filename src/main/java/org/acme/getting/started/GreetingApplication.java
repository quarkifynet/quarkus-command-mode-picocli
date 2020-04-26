package org.acme.getting.started;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.acme.getting.started.command.GreetingCommand;
import org.acme.getting.started.command.QuarkusCommand;
import org.acme.getting.started.command.SetCommand;
import org.apache.maven.shared.utils.cli.CommandLineUtils;
import picocli.CommandLine;

import javax.inject.Inject;

@QuarkusMain
public class GreetingApplication implements QuarkusApplication {
    @Inject
    GreetingCommand greetingCommand;
    @Inject
    SetCommand setCommand;

    @Override
    public int run(String... args) throws Exception {
        if (args.length == 0) {
            Quarkus.waitForExit();
            return 0;
        }
        if (args.length == 1) {
            args = CommandLineUtils.translateCommandline(args[0]);
        }
        return new CommandLine(new QuarkusCommand())
                .addSubcommand(greetingCommand)
                .addSubcommand(setCommand)
                .execute(args);
    }

    public static void main(String[] args) {
        Quarkus.run(GreetingApplication.class, args);
    }
}