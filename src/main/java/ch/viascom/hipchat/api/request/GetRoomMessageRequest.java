package ch.viascom.hipchat.api.request;

import ch.viascom.hipchat.api.deserializer.MessageFromDeserializer;
import ch.viascom.hipchat.api.models.message.MessageFrom;
import ch.viascom.hipchat.api.request.generic.GetRequest;
import ch.viascom.hipchat.api.request.models.GetRoomMessage;
import ch.viascom.hipchat.api.response.GetRoomMessageResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.HttpClient;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

/**
 * Created by Patrick Bösch on 04.05.16.
 */
public class GetRoomMessageRequest extends GetRequest<GetRoomMessageResponse> {
    private GetRoomMessage getRoomMessage;

    public GetRoomMessageRequest(GetRoomMessage getRoomMessage, String accessToken, String baseUrl, HttpClient httpClient, ExecutorService executorService) {
        super(accessToken, baseUrl, httpClient, executorService);
        this.getRoomMessage = getRoomMessage;
    }

    @Override
    protected String getPath() {
        return "/room/" + getRoomMessage.getRoomId() + "/history/" + getRoomMessage.getMessageId();
    }

    @Override
    protected Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Use Custom TypeAdapter (MessageFromDeserializer) because "from" can be an object or a string
        gsonBuilder.registerTypeAdapter(MessageFrom.class, new MessageFromDeserializer());
        Gson gson = gsonBuilder.create();

        return gson;
    }

    @Override
    protected HashMap<String, String> getQueryParam() {
        HashMap<String, String> param = new HashMap<>();
        param.put("timezone", String.valueOf(getRoomMessage.getTimezone()));
        param.put("include_deleted", String.valueOf(getRoomMessage.isInclude_deleted()));
        return param;
    }
}
