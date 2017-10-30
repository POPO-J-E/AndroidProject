package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.dummy;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Beer {

    @SerializedName("id")
    public final String id;
    @SerializedName("name")
    public final String name;
    @SerializedName("image_url")
    public final String url;

    public Beer(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name;
    }

}
