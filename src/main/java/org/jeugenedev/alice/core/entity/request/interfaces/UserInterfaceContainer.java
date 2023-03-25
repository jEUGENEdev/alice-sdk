package org.jeugenedev.alice.core.entity.request.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInterfaceContainer {
    private Screen screen;
    @JsonProperty("account_linking")
    private AccountLinking accountLinking;
    @JsonProperty("audio_player")
    private AudioPlayer audioPlayer;

    public UserInterfaceContainer() {}

    public UserInterfaceContainer(Screen screen, AccountLinking accountLinking, AudioPlayer audioPlayer) {
        this.screen = screen;
        this.accountLinking = accountLinking;
        this.audioPlayer = audioPlayer;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public AccountLinking getAccountLinking() {
        return accountLinking;
    }

    public void setAccountLinking(AccountLinking accountLinking) {
        this.accountLinking = accountLinking;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public String toString() {
        return "UserInterfaceContainer{" +
                "screen=" + screen +
                ", accountLinking=" + accountLinking +
                ", audioPlayer=" + audioPlayer +
                '}';
    }

    static class Screen implements UserInterface {}
    static class AccountLinking implements UserInterface {}
    static class AudioPlayer implements UserInterface {}

    enum Interfaces { SCREEN, ACCOUNT_LINKING, AUDIO_PLAYER }
}