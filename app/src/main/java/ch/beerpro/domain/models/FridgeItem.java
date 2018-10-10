package ch.beerpro.domain.models;

import android.content.Intent;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@IgnoreExtraProperties
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FridgeItem implements Entity {
    public static final String COLLECTION = "fridge";
    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_BEER_ID = "beerId";
    public static final String FIELD_AMOUNT = "amount";

    @Exclude
    private String id;
    @NonNull
    private String userId;
    @NonNull
    private String beerId;
    @NonNull
    private Integer amount;

    public static String generateId(String userId, String beerId) {
        return String.format("%s_%s", userId, beerId);
    }
}
