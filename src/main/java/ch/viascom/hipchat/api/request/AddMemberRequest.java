package ch.viascom.hipchat.api.request;

import ch.viascom.hipchat.api.request.generic.PutRequest;
import ch.viascom.hipchat.api.request.models.AddMember;
import ch.viascom.hipchat.api.response.NoContentResponse;
import ch.viascom.hipchat.api.util.JsonUtil;
import org.apache.http.client.HttpClient;

import java.util.concurrent.ExecutorService;

/**
 * Created by Nikola Stankovic on 20.04.2016.
 */
public class AddMemberRequest extends PutRequest<NoContentResponse> {
    private AddMember addMember;

    public AddMemberRequest(AddMember addMember, String accessToken, String baseUrl, HttpClient httpClient, ExecutorService executorService) {
        super(accessToken, baseUrl, httpClient, executorService);
        this.addMember = addMember;
    }

    @Override
    protected String getJsonBody() {
        return JsonUtil.getJsonBody(addMember);
    }

    @Override
    protected String getPath() {
        return "/room/" + addMember.getRoomId() + "/member/" + addMember.getUserId();
    }
}