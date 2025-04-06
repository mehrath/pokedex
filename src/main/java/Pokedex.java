import org.json.JSONObject;
import org.json.JSONTokener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Pokedex {

    public PokemonStorage pokemonStorage = new PokemonStorage();
    public static Pokedex pokedex;

    public Pokedex() throws IOException {
    }
    public void run() throws IOException {
        Map sprites = pokemonStorage.getPokeSprites(1);
        String front_default = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_DEFAULT));
        String back_default = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_DEFAULT));
        String front_female = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_FEMALE));
        String back_female = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_FEMALE));
        String front_shiny = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_SHINY_DEFAULT));
        String back_shiny = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_SHINY_DEFAULT));
        String front_shiny_female = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_SHINY_FEMALE));
        String back_shiny_female = (String) sprites.get(pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_SHINY_FEMALE));
        //"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/380.png";
        drawImg(front_default);
    }

    public static void main(String[] args) throws IOException {
        Pokedex.pokedex = new Pokedex();
        pokedex.run();

    }

    private static String toPrettyFormat(String jsonString) {
        JSONTokener tokener = new JSONTokener(jsonString);
        JSONObject root = new JSONObject(tokener);
        return root.toString(4);
    }

    private static void drawImg(String url) throws IOException {
        JFrame frame = new JFrame();
        Image image = ImageIO.read(new URL(url));

        frame.setIconImage(new ImageIcon(image).getImage());
        ;
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        //left_panel.setLayout(new FlowLayout());
        //right_panel.setLayout(new FlowLayout());
        JLabel title = new JLabel("Pokedex");
        //panel.setSize(800,600);
        //container.add(left_panel);
        PokePanel left_box = new PokePanel();
        PokePanel right_box = new PokePanel();
        container.add(left_box);
        container.add(right_box);
        frame.setSize(2000,1000);
        frame.add(container);
        //frame.add(label);

        //frame.add(title);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }
}

