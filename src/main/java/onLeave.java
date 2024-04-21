import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;

public class onLeave implements Listener {
    final String url;
    final String message;

    public onLeave(String url, String message) {
        this.url = url;
        this.message = message;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        DiscordWebhook wh = new DiscordWebhook(url);
        String messgage = String.format(message, event.getPlayer().getDisplayName());
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setTitle("Player Left");
        embed.setDescription(messgage);
        embed.setColor(Color.RED);
        // get the number of players online
        embed.addField("Players Online", String.valueOf(event.getPlayer().getServer().getOnlinePlayers().size() - 1), true);
        wh.addEmbed(embed);
        try {
            wh.execute();
        } catch (MalformedURLException e) {
            System.out.println("[MinecraftDiscordWebhook] Invalid webhook URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
