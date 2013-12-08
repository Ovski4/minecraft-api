package ovski.minecraft.api.command;

import org.bukkit.entity.Player;

/**
 * SubCommand
 * 
 * A sub command to lighten classes that implements commandExecutor
 * 
 * @author baptiste <baptiste.bouchereau@gmail.com>
 */
public abstract class SubCommand
{
    /**
     * The player launching the command
     */
    protected Player player;

    /**
     * The command arguments
     */
    protected String[] args;

    protected SubCommand(Player player, String[] args)
    {
        this.player = player;
        this.args = args;
    }

    /**
     * Process the command
     * 
     * @return true or false
     */
    public boolean process()
    {
        if (!check())
            return true;
        else
            return launch();
    }

    /**
     * Check the arguments of the command
     * 
     * @return true or false
     */
    protected boolean check()
    {
        return false;
    }

    /**
     * Launch the command
     * 
     * @return true or false
     */
    protected boolean launch()
    {
        return false;
    }
}
