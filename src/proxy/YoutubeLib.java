package proxy;

public class YoutubeLib implements IYoutubeLib {
    public String  getMP4FromUrl(String url) throws InterruptedException {
        Thread.sleep(5000);
        return "C:/downloads/" + url + ".mp4";
    }
}
