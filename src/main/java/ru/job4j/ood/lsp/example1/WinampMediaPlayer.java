package ru.job4j.ood.lsp.example1;

public class WinampMediaPlayer extends MediaPlayer {
    public void playAudio() {
        System.out.println("Playing audio...");
    }
    public void playVideo() {
        throw new VideoUnsupportedException();
    }
}
