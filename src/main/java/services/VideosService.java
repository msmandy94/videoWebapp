package services;

import beens.UserBeen;
import beens.VideoBean;

import java.util.ArrayList;

/**
 * Created by mandeepsingh on 02/07/18.
 */
public interface VideosService {
    ArrayList<VideoBean> getVideosForUser(UserBeen userBeen);
    boolean deleteVideo(String videoId);

}
