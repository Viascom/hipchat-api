package ch.viascom.hipchat.api;


import ch.viascom.groundwork.foxhttp.FoxHttpClient;
import ch.viascom.groundwork.foxhttp.annotation.processor.FoxHttpAnnotationParser;
import ch.viascom.groundwork.foxhttp.authorization.BearerTokenAuthorization;
import ch.viascom.groundwork.foxhttp.authorization.FoxHttpAuthorizationScope;
import ch.viascom.groundwork.foxhttp.builder.FoxHttpClientBuilder;
import ch.viascom.groundwork.foxhttp.exception.FoxHttpException;
import ch.viascom.groundwork.foxhttp.interceptor.FoxHttpInterceptorType;
import ch.viascom.groundwork.foxhttp.interceptor.response.HttpErrorResponseInterceptor;
import ch.viascom.groundwork.foxhttp.log.SystemOutFoxHttpLogger;
import ch.viascom.groundwork.foxhttp.parser.GsonParser;
import ch.viascom.hipchat.api.api.*;
import ch.viascom.hipchat.api.deserializer.MessageFromDeserializer;
import ch.viascom.hipchat.api.deserializer.MessageLinkDeserializer;
import ch.viascom.hipchat.api.models.message.MessageFrom;
import ch.viascom.hipchat.api.models.message.MessageLink;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Created by patrick.boesch@viascom.ch on 11.04.16.
 */
public class HipChat {

    private static final Logger log = LogManager.getLogger(HipChat.class);
    private String baseUrl = "https://api.hipchat.com/v2";
    private String accessToken;
    private FoxHttpClient client;

    private CapabilitiesApi capabilitiesApi;
    private EmoticonsApi emoticonsApi;
    private ExtensionsApi extensionsApi;
    private GroupsApi groupsApi;
    private IntegrationsApi integrationsApi;
    private InvitesApi invitesApi;
    private OAuthSessionsApi oAuthSessionsApi;
    private PrefsPublicsApi prefsPublicsApi;
    private RoomsApi roomsApi;
    private UsersApi usersApi;

    public HipChat(String accessToken) throws FoxHttpException {

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(MessageFrom.class, new MessageFromDeserializer());
        gsonBuilder.registerTypeAdapter(MessageLink.class, new MessageLinkDeserializer());

        client = new FoxHttpClientBuilder(new GsonParser(gsonBuilder.create()))
                .setFoxHttpLogger(new SystemOutFoxHttpLogger(true, "ch.viascom.hipchat"))
                .addFoxHttpInterceptor(FoxHttpInterceptorType.RESPONSE, new HttpErrorResponseInterceptor())
                .addFoxHttpPlaceholderEntry("host", baseUrl)
                .build();

        setAccessToken(accessToken);
        setBaseUrl(baseUrl);
    }


    public HipChat(String accessToken, String baseUrl) {
        setBaseUrl(baseUrl);
        setAccessToken(accessToken);
    }

    public HipChat setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        client.getFoxHttpPlaceholderStrategy().addPlaceholder("host", baseUrl);
        return this;
    }

    public HipChat setAccessToken(String accessToken) {
        this.accessToken = accessToken;

        client.getFoxHttpAuthorizationStrategy().addAuthorization(
                Arrays.asList(
                        FoxHttpAuthorizationScope.create("{host}/emoticon*"),
                        FoxHttpAuthorizationScope.create("{host}/extension*"),
                        FoxHttpAuthorizationScope.create("{host}/group*"),
                        FoxHttpAuthorizationScope.create("{host}/addon*"),
                        FoxHttpAuthorizationScope.create("{host}/invite*"),
                        FoxHttpAuthorizationScope.create("{host}/oauth*"),
                        FoxHttpAuthorizationScope.create("{host}/room*"),
                        FoxHttpAuthorizationScope.create("{host}/user*")
                ), new BearerTokenAuthorization(accessToken));

        return this;
    }

    public CapabilitiesApi capabilitiesApi() throws FoxHttpException {
        if (capabilitiesApi == null) {
            capabilitiesApi = new FoxHttpAnnotationParser().parseInterface(CapabilitiesApi.class, client);
        }

        return capabilitiesApi;
    }

    public EmoticonsApi emoticonsApi() throws FoxHttpException {
        if (emoticonsApi == null) {
            emoticonsApi = new FoxHttpAnnotationParser().parseInterface(EmoticonsApi.class, client);
        }

        return emoticonsApi;
    }

    public ExtensionsApi extensionsApi() throws FoxHttpException {
        if (extensionsApi == null) {
            extensionsApi = new FoxHttpAnnotationParser().parseInterface(ExtensionsApi.class, client);
        }

        return extensionsApi;
    }

    public GroupsApi groupsApi() throws FoxHttpException {
        if (groupsApi == null) {
            groupsApi = new FoxHttpAnnotationParser().parseInterface(GroupsApi.class, client);
        }

        return groupsApi;
    }

    public IntegrationsApi integrationsApi() throws FoxHttpException {
        if (integrationsApi == null) {
            integrationsApi = new FoxHttpAnnotationParser().parseInterface(IntegrationsApi.class, client);
        }

        return integrationsApi;
    }

    public InvitesApi invitesApi() throws FoxHttpException {
        if (invitesApi == null) {
            invitesApi = new FoxHttpAnnotationParser().parseInterface(InvitesApi.class, client);
        }

        return invitesApi;
    }

    public OAuthSessionsApi oAuthSessionsApi() throws FoxHttpException {
        if (oAuthSessionsApi == null) {
            oAuthSessionsApi = new FoxHttpAnnotationParser().parseInterface(OAuthSessionsApi.class, client);
        }

        return oAuthSessionsApi;
    }

    public PrefsPublicsApi prefsPublicsApi() throws FoxHttpException {
        if (prefsPublicsApi == null) {
            prefsPublicsApi = new FoxHttpAnnotationParser().parseInterface(PrefsPublicsApi.class, client);
        }

        return prefsPublicsApi;
    }

    public RoomsApi roomsApi() throws FoxHttpException {
        if (roomsApi == null) {
            roomsApi = new FoxHttpAnnotationParser().parseInterface(RoomsApi.class, client);
        }

        return roomsApi;
    }

    public UsersApi usersApi() throws FoxHttpException {
        if (usersApi == null) {
            usersApi = new FoxHttpAnnotationParser().parseInterface(UsersApi.class, client);
        }

        return usersApi;
    }
}
