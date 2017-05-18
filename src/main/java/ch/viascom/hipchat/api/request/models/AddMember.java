package ch.viascom.hipchat.api.request.models;

import ch.viascom.hipchat.api.models.user.UserRole;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by Nikola Stankovic on 20.04.2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMember {
    private transient String userId;
    private transient String roomId;
    @SerializedName("room_roles")
    private ArrayList<UserRole> roomRoles;
}
