import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class PokemonStorage {
    enum Appearance
    {
        FRONT_DEFAULT, BACK_DEFAULT, FRONT_FEMALE, BACK_FEMALE,
        FRONT_SHINY_DEFAULT, BACK_SHINY_DEFAULT, FRONT_SHINY_FEMALE, BACK_SHINY_FEMALE
    }
    HashMap<Appearance, String> appearanceSpriteMap = new HashMap<>();
    PokemonStorage() throws IOException {
        appearanceSpriteMap.put(Appearance.FRONT_DEFAULT, "front_default");
        appearanceSpriteMap.put(Appearance.BACK_DEFAULT, "back_default");
        appearanceSpriteMap.put(Appearance.FRONT_FEMALE, "front_female");
        appearanceSpriteMap.put(Appearance.BACK_FEMALE, "back_female");
        appearanceSpriteMap.put(Appearance.FRONT_SHINY_DEFAULT, "front_shiny");
        appearanceSpriteMap.put(Appearance.BACK_SHINY_DEFAULT, "back_shiny");
        appearanceSpriteMap.put(Appearance.FRONT_SHINY_FEMALE, "front_shiny_female");
        appearanceSpriteMap.put(Appearance.BACK_SHINY_FEMALE, "back_shiny_female");
    }
    public String getPokeURL(int pokeNum){
       return "https://pokeapi.co/api/v2/pokemon/" + pokeNum +"/";
    }
    public Map getPokeSprites(int pokeNum) throws IOException {
        URLConnection connection = new URL(getPokeURL(pokeNum)).openConnection();
        InputStream is = connection.getInputStream();
        System.out.println(connection.getContentType());
        byte[] response = is.readAllBytes();
        String resp = new String(response);
        JSONTokener tokener = new JSONTokener(resp);
        JSONObject root = new JSONObject(tokener);
        JSONObject sprites = (JSONObject) root.get("sprites");
        return sprites.toMap();
    }
}
