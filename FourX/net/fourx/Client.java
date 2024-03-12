package net.fourx;

import lombok.Getter;
import lombok.Setter;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.fourx.manager.AddonManager;
import net.fourx.manager.font.FontManager;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.io.IOException;

@Getter
public enum Client {
    INSTANCE;
    private final String name = "FourX", version = "2.1.6";
    public final int clientColor = new Color(152, 255, 255, 255).getRGB();
    private AddonManager addonManager;
    private FontManager fontManager;
    private boolean running = true;
    private long started;

    public void initClient() {
        startRPC();
        addonManager = new AddonManager();
        initFont();
        Display.setTitle(getName() + " " + getVersion());
    }

    private void initFont() {
        try {
            fontManager = new FontManager();
        } catch (IOException | FontFormatException ignored) {
        }
    }

    private void startRPC(){
//        started = System.currentTimeMillis();
//        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
//            @Override
//            public void apply(DiscordUser discordUser) {
//                updateRPC("Loading FourX...", "");
//            }
//        }).build();
//
//        DiscordRPC.discordInitialize("1164270803400007813", handlers, true);
//
//        new Thread("DiscordRPC"){
//            @Override
//            public void run(){
//                while (running)
//                    DiscordRPC.discordRunCallbacks();
//            }
//        }.start();
    }

    public void stopRPC(){
//        running = false;
//        DiscordRPC.discordShutdown();
    }

    public void updateRPC(String title, String details){
//        DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder(title);
//        builder.setBigImage("logo", "");
//        builder.setDetails(details);
//        builder.setStartTimestamps(started);
//        DiscordRPC.discordUpdatePresence(builder.build());
    }
}
