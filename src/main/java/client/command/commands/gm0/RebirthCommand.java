package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;

public class RebirthCommand extends Command {
    {
        setDescription("Rebirth your character at level 200 to grow stronger. Usage: @rebirth");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (!player.doRebirth()) {
            player.dropMessage("You must reach level " + player.getMaxClassLevel() +
                    " before you can rebirth. Current rebirths: " + player.getReborns());
        }
    }
}
