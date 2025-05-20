package proxy;

public interface IYoutubeLib {
    String  getMP4FromUrl(String url) throws InterruptedException;
}
