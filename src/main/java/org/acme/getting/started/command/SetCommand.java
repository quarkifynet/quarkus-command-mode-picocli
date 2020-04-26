package org.acme.getting.started.command;

import org.acme.getting.started.data.Setting;
import picocli.CommandLine;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;

@Dependent
@CommandLine.Command(name = "set")
public class SetCommand implements Runnable {
    @CommandLine.Parameters(index = "0") TableType type;
    @CommandLine.Parameters(index = "1") String key;
    @CommandLine.Parameters(index = "2") String value;

    @Override
    @Transactional
    public void run() {
        if (type == TableType.SETTING) { //make a switch when you have more options
            Setting setting = Setting.find("key = ?1", key).firstResult();
            if(setting == null) {
                setting = new Setting();
            }
            setting.key = key;
            setting.value = value;
            setting.persist();
        }
    }

    enum TableType { SETTING }
}
