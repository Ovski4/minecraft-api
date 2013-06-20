package ovski.api.command;

import org.bukkit.entity.Player;

public abstract class SubCommand
{
    protected Player player;
    protected String[] args;

    protected SubCommand(Player player, String[] args)
    {
        this.player = player;
        this.args = args;
    }

    public boolean process()
    {
        if (!check())
            return true;
        else
            return launch();
    }

    protected boolean check()
    {
        return false;
    }

    protected boolean launch()
    {
        return false;
    }
}
