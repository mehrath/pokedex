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
    JLabel nameLabel = new JLabel();
    JLabel frontIconLabel = new JLabel();
    JLabel fetchPokeNumLabel = new JLabel();
    ImageIcon backIcon = null;
    ImageIcon frontIcon = null;
    JButton leftButton = new JButton("<");
    JButton rightButton = new JButton(">");
    JTextField inputPokeNameNum = new JTextField(10);
    int currentPokemon = 1;
    String currentPokemonName = null;
    String maxClaimsHeCanCode = "Max is a cute avali";
    public PokePanel(){
        nameLabel.setVerticalTextPosition(JLabel.TOP);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(nameLabel);
        add(inputPokeNameNum);
        add(leftButton);
        add(rightButton);
        backIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        backIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(backIconLabel);
        frontIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        frontIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(frontIconLabel);
        fetchPokeNumLabel.setVerticalTextPosition(JLabel.TOP);
        fetchPokeNumLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(fetchPokeNumLabel);
        inputPokeNameNum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dex = inputPokeNameNum.getText();
                try {
                    currentPokemon = Integer.parseInt(dex);

                } catch (NumberFormatException ex) {
                    //ex.printStackTrace();
                    currentPokemonName = dex;
                }
                updatePokemon();
            }
        });
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
        updatePokemon();
    }
    public void updatePokemon(){
        Map sprites = null;
        try {
            if (currentPokemonName == null) {
                sprites = Pokedex.pokedex.pokemonStorage.getPokeSprites(currentPokemon);
            }
            else {
                sprites = Pokedex.pokedex.pokemonStorage.getPokeSprites(currentPokemonName);
                currentPokemon = Pokedex.pokedex.pokemonStorage.getPokeId(currentPokemonName);
                currentPokemonName = null;
            }
            fetchPokeNumLabel.setText(String.valueOf(currentPokemon));
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
