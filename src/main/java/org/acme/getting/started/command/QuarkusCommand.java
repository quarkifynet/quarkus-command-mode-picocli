package org.acme.getting.started.command;

import picocli.CommandLine;

@CommandLine.Command(subcommands = {
        CommandLine.HelpCommand.class
})
public class QuarkusCommand {
}
