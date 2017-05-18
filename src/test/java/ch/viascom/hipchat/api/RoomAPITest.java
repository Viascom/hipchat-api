package ch.viascom.hipchat.api;

import ch.viascom.hipchat.api.api.RoomsAPI;
import ch.viascom.hipchat.api.exception.APIException;
import ch.viascom.hipchat.api.models.Card;
import ch.viascom.hipchat.api.models.Message;
import ch.viascom.hipchat.api.models.card.*;
import ch.viascom.hipchat.api.models.message.MessageColor;
import ch.viascom.hipchat.api.request.models.*;
import ch.viascom.hipchat.api.response.GetRoomStatisticsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by patrickboesch on 11.04.16.
 */
public class RoomAPITest {

    private static final Logger log = LogManager.getLogger(RoomAPITest.class);
    private static final String clientToken = "";
    private static final String integrationToken = "";

    private static int testBotUserId;

    //@Test
    public void sendRoomNotificationTest() throws APIException {
        HipChat hipChat = new HipChat(integrationToken);
        hipChat.roomsAPI().sendRoomNotification(new SendNotification("Test-Room", "Hello World", MessageColor.RED, true));
    }

    //@Test
    public void sendRoomMessageTest() throws APIException {
        HipChat hipChat = new HipChat(clientToken);
        hipChat.roomsAPI().sendRoomMessage(new SendMessage("Test-Room", "Hello World"));
    }

    //@Test
    public void replyToMessageTest() throws APIException {
        HipChat hipChat = new HipChat(clientToken);
        hipChat.roomsAPI().replyToMessage(new ReplyMessage("Test-Room", "Reply", "")); // Fill out last parameter with parentId
    }

    //@Test
    public void setTopicTest() throws APIException {
        HipChat hipChat = new HipChat(integrationToken);
        hipChat.roomsAPI().setTopic(new SetTopic("Test-Room", "New fancy topic for our test room"));
    }

    //@Test
    public void getAllParticipantsTest() throws APIException {
        HipChat hipChat = new HipChat(integrationToken);
        log.debug(hipChat.roomsAPI().getAllParticipants(new GetAllParticipants("Test-Room", 0, 100, true)));

    }

    //@Test
    public void addMemberTest() throws APIException {
        HipChat hipChat = new HipChat(clientToken);
        hipChat.roomsAPI().addMember(new AddMember(String.valueOf(testBotUserId), "Private-Test-Room", null));
    }

    //@Test
    public void viewRoomHistoryTest() throws APIException {
        HipChat hipChat = new HipChat(clientToken);
        ViewRoomHistory viewRoomHistory = new ViewRoomHistory("TestRoom", 0, 100);
        viewRoomHistory.setReverse(false);
        ArrayList<Message> messages = hipChat.roomsAPI().viewRoomHistory(viewRoomHistory).getItems();
    }

    //@Test
    public void getRoomStatisticsTest() throws APIException {
        HipChat hipChat = new HipChat(clientToken);
        GetRoomStatisticsResponse response = hipChat.roomsAPI().getRoomStatistics("TestRoom");
        System.out.println(response.getMessagesSent());
    }

    //@Test
    public void sendRoomNotificationCardTest() throws APIException {
        HipChat hipChat = new HipChat(integrationToken);
        RoomsAPI roomsAPI = hipChat.roomsAPI();

        SendNotification notification = new SendNotification();
        notification.setRoomId("Test-Room");
        notification.setMessage("Hi from (puzzle) HipChat-API v2");
        notification.setColor(MessageColor.RED);
        notification.setNotify(true);

        Card card = new Card();
        card.setTitle("API Card");

        CardIcon icon = new CardIcon();
        icon.setUrl("https://dujrsrsgsd3nh.cloudfront.net/img/emoticons/22438/fire-1366382911.png");
        card.setIcon(icon);
        card.setStyle(CardStyle.APPLICATION);
        card.setUrl("http://github.com");
        card.setId("3985698273957");
        card.setFormat(CardFormat.COMPACT);

        CardAttribute cardAttribute = new CardAttribute();

        cardAttribute.setLabel("Version");

        CardAttributeValue cardAttributeValue = new CardAttributeValue();

        cardAttributeValue.setLabel("1.0");
        CardIcon icon2 = new CardIcon();
        icon2.setUrl("https://dujrsrsgsd3nh.cloudfront.net/img/emoticons/22438/puzzle-1366382992.png");
        cardAttributeValue.setIcon(icon2);

        cardAttribute.setValue(cardAttributeValue);

        card.getAttributes().add(cardAttribute);

        CardActivity cardActivity = new CardActivity();

        cardActivity.setHtml("API test Card from hipchat-api");
        cardActivity.setIcon(icon);

        card.setActivity(cardActivity);

        notification.setCard(card);

        roomsAPI.sendRoomNotification(notification);
    }
}
