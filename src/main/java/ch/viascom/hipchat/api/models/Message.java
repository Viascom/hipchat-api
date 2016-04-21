package ch.viascom.hipchat.api.models;

import ch.viascom.hipchat.api.models.message.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by patrickboesch on 11.04.16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String from;
    private String date;
    private String message;
    private ArrayList<MessageMention> mentions;
    private MessageType type;
    private String id;
    private MessageColor color;
    private MessageFormat message_format = MessageFormat.HTML;

    private MessageFile file;
    private ArrayList<MessageLink> message_links;
    private NotificationSender notification_sender;
}
