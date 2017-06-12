package ch.viascom.hipchat.api.models.capability;

import ch.viascom.hipchat.api.models.HipChatScope;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class CapabilityHipchatApiProvider implements Serializable {
    /**
     * The base URL for the API
     */
    private String url;

    /**
     * The scopes that this API supports.
     */
    private HashMap<String, HipChatScope> availableScopes;
}
