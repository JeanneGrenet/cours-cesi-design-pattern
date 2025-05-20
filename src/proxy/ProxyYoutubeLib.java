package proxy;

import java.util.HashMap;
import java.util.Map;

public class ProxyYoutubeLib implements IYoutubeLib {

    private YoutubeLib youtubeLib;
    private Map<String, String> cache;

    public ProxyYoutubeLib() {
        youtubeLib = new YoutubeLib();
        cache = new HashMap<>();
    }

    public String getMP4FromUrl(String url) throws InterruptedException {
        if (cache.containsKey(url)) {
            return cache.get(url);
        }
        String mp4 = youtubeLib.getMP4FromUrl(url);
        cache.put(url, mp4);
        return mp4;
    }
}
