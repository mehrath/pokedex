import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class PokePanel extends JPanel {
    JLabel iconLabels[] = new JLabel[PokemonStorage.Appearance.values().length];
    JLabel backIconLabel = new JLabel();
    JLabel frontIconLabel = new JLabel();
    ImageIcon backIcon = null;
    ImageIcon frontIcon = null;
    JButton leftButton = new JButton("<");
    JButton rightButton = new JButton(">");
    int currentPokemon = 1;
    public PokePanel(){
        add(leftButton);
        add(rightButton);
        add(backIconLabel);
        add(frontIconLabel);
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPokemon--;
                updatePokemon();
            }
        });
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPokemon++;
                updatePokemon();
            }
        });

    }
    public void updatePokemon(){

        Map sprites = null;
        try {
            sprites = Pokedex.pokedex.pokemonStorage.getPokeSprites(currentPokemon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String front_default = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_DEFAULT));
        String back_default = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_DEFAULT));
        if (front_default != null){
            try {
                setFrontIcon(front_default);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (back_default != null){
            try {
                setBackIcon(back_default);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void setBackIcon(String url) throws MalformedURLException {
        if (url == null) return;
        backIcon = new ImageIcon(new URL(url));
        backIconLabel.setIcon(backIcon);
        backIconLabel.setText("back view");

    }
    public void setFrontIcon(String url) throws MalformedURLException {
        if (url == null) return;
        frontIcon = new ImageIcon(new URL(url));
        frontIconLabel.setIcon(frontIcon);
        frontIconLabel.setText("front view");
    }
}
