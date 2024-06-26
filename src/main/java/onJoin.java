import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;

public class onJoin implements Listener {
    final String url;
    final String message;

    public onJoin(String url, String message) {
        this.url = url;
        this.message = message;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        DiscordWebhook wh = new DiscordWebhook(url);
        String messgage = String.format(message, event.getPlayer().getDisplayName());
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setTitle("Player Joined");
        embed.setDescription(messgage);
        embed.setColor(Color.GREEN);
        embed.addField("Players Online", String.valueOf(event.getPlayer().getServer().getOnlinePlayers().size()), true);
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
