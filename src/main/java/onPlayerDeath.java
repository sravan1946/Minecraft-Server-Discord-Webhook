import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;

public class onPlayerDeath implements Listener {
    final String url;
    final String message;

    public onPlayerDeath(String url, String message) {
        this.url = url;
        this.message = message;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        DiscordWebhook wh = new DiscordWebhook(url);
        String messgage = String.format(message, event.getDeathMessage());
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setTitle("Player Death");
        embed.setDescription(messgage);
        embed.setColor(Color.RED);
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
