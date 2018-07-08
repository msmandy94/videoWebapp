package services;

import DAO.MongoClientPool;
import beens.UserBeen;
import beens.UserType;
import beens.VideoBean;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by mandeepsingh on 29/06/18.
 */
public class VideosServiceImpl implements VideosService {
    private static Logger logger = LoggerFactory.getLogger(VideosServiceImpl.class.getName());

    private VideosServiceImpl() {
    }

    @Override
    public ArrayList<VideoBean> getVideosForUser(UserBeen userBeen) {
        String userId = userBeen.getUserId();
        UserType userType = userBeen.getUserType();

        BasicDBObject bsonObject = new BasicDBObject();
        if (userType.equals(UserType.CONTRIBUTOR)) {
            bsonObject.append("ownerUserId", userId);
        } else if (userType.equals(UserType.CONTRIBUTOR)) {
            bsonObject.append("isPublic", true);
        }
        ArrayList<Document> videosDocument = MongoClientPool.getUserVideosCollection().find(bsonObject).into(new ArrayList<Document>());
        return documentToVideoBeenAdaptor(videosDocument);
    }

    private ArrayList<VideoBean> documentToVideoBeenAdaptor(ArrayList<Document> videosDocument) {
        if (videosDocument == null || videosDocument.isEmpty()) {
            //todo: generate appropriate exception and error message
            return null;
        }
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        for (Document doc : videosDocument) {
            VideoBean video = new VideoBean();
            if (doc.containsKey("videoId")) {
                video.setVideoId((String) doc.get("videoId"));
            }
            if (doc.containsKey("sourceURL")) {
                video.setSourceURL((String) doc.get("sourceURL"));
            }
            if (doc.containsKey("thumbnailURL")) {
                video.setThumbnailURL((String) doc.get("thumbnailURL"));
            }
            if (doc.containsKey("title")) {
                video.setTitle((String) doc.get("title"));
            }
            if (doc.containsKey("description")) {
                video.setDescription((String) doc.get("description"));
            }
            if (doc.containsKey("ownerUserId")) {
                video.setOwnerUserId((String) doc.get("ownerUserId"));
            }
            if (doc.containsKey("isPublic")) {
                video.setPublic(doc.getBoolean("isPublic", true));
            }
            videos.add(video);
        }

        return videos;
    }


    @Override
    public boolean deleteVideo(String videoId) {

        return false;
    }

    private static class VideosSingletonHelper {
        private static final VideosServiceImpl INSTANCE = new VideosServiceImpl();
    }

    public static VideosServiceImpl getInstance() {
        return VideosSingletonHelper.INSTANCE;
    }

}
