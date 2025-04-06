import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class PokePanel extends JPanel {
    JLabel iconLabels[] = new JLabel[PokemonStorage.Appearance.values().length];
    JLabel nameLabel = new JLabel();
    JLabel backDefaultIconLabel = new JLabel();
    JLabel frontDefaultIconLabel = new JLabel();
    JLabel backShinyIconLabel = new JLabel();
    JLabel frontShinyIconLabel = new JLabel();
    JLabel backFemaleIconLabel = new JLabel();
    JLabel frontFemaleIconLabel = new JLabel();
    JLabel backFemaleShinyIconLabel = new JLabel();
    JLabel frontFemaleShinyIconLabel = new JLabel();
    JLabel fetchPokeNumLabel = new JLabel();
    JLabel fetchPokeNameLabel = new JLabel();
    ImageIcon backIcon = null;
    ImageIcon frontIcon = null;
    JButton leftButton = new JButton("<");
    JButton rightButton = new JButton(">");
    JTextField inputPokeNameNum = new JTextField(10);
    int currentPokemon = 1;
    String currentPokemonName = null;
    String maxCanCode = "Maxi likes Faputa";
    public PokePanel(){
        nameLabel.setVerticalTextPosition(JLabel.TOP);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(nameLabel);
        add(inputPokeNameNum);
        add(leftButton);
        add(rightButton);
        backDefaultIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        backDefaultIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(backDefaultIconLabel);
        frontDefaultIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        frontDefaultIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(frontDefaultIconLabel);
        backShinyIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        backShinyIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(backShinyIconLabel);
        frontShinyIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        frontShinyIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(frontShinyIconLabel);
        backFemaleIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        backFemaleIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(backFemaleIconLabel);
        frontFemaleIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        frontFemaleIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(frontFemaleIconLabel);
        backFemaleShinyIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        backFemaleShinyIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(backFemaleShinyIconLabel);
        frontFemaleShinyIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        frontFemaleShinyIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(frontFemaleShinyIconLabel);
        fetchPokeNumLabel.setVerticalTextPosition(JLabel.TOP);
        fetchPokeNumLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(fetchPokeNumLabel);
        fetchPokeNameLabel.setVerticalTextPosition(JLabel.TOP);
        fetchPokeNameLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(fetchPokeNameLabel);
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
            fetchPokeNameLabel.setText(String.valueOf(Pokedex.pokedex.pokemonStorage.getPokeName(currentPokemon)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String front_default = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_DEFAULT));
        String back_default = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_DEFAULT));
        String front_shiny = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_SHINY_DEFAULT));
        String back_shiny = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_SHINY_DEFAULT));
        String front_female = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_FEMALE));
        String back_female = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_FEMALE));
        String front_shiny_female = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.FRONT_SHINY_FEMALE));
        String back_shiny_female = (String) sprites.get(Pokedex.pokedex.pokemonStorage.appearanceSpriteMap.get(PokemonStorage.Appearance.BACK_SHINY_FEMALE));
        if (front_default != null){
            try {
                setFrontDefaultIcon(front_default);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (back_default != null){
            try {
                setBackDefaultIcon(back_default);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (front_shiny != null){
            try {
                setFrontShinyIcon(front_shiny);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (back_shiny != null){
            try {
                setBackShinyIcon(back_shiny);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (front_female != null){
            try {
                setFrontFemaleIcon(front_female);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (back_female != null){
            try {
                setBackFemaleIcon(back_female);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (front_shiny_female != null){
            try {
                setFrontShinyFemaleIcon(front_shiny_female);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        if (back_shiny_female != null){
            try {
                setBackShinyFemaleIcon(back_shiny_female);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setFrontDefaultIcon(String url) throws MalformedURLException {
        if (url == null) return;
        frontIcon = new ImageIcon(new URL(url));
        frontDefaultIconLabel.setIcon(frontIcon);
        frontDefaultIconLabel.setText("front view");
    }
    public void setBackDefaultIcon(String url) throws MalformedURLException {
        if (url == null) return;
        backIcon = new ImageIcon(new URL(url));
        backDefaultIconLabel.setIcon(backIcon);
        backDefaultIconLabel.setText("back view");
    }
    public void setFrontFemaleIcon(String url) throws MalformedURLException {
        if (url == null) return;
        frontIcon = new ImageIcon(new URL(url));
        frontShinyIconLabel.setIcon(frontIcon);
        frontShinyIconLabel.setText("front view");
    }
    public void setBackFemaleIcon(String url) throws MalformedURLException {
        if (url == null) return;
        backIcon = new ImageIcon(new URL(url));
        backShinyIconLabel.setIcon(backIcon);
        backShinyIconLabel.setText("back view");
    }
    public void setFrontShinyIcon(String url) throws MalformedURLException {
        if (url == null) return;
        frontIcon = new ImageIcon(new URL(url));
        frontFemaleIconLabel.setIcon(frontIcon);
        frontFemaleIconLabel.setText("front view");
    }
    public void setBackShinyIcon(String url) throws MalformedURLException {
        if (url == null) return;
        backIcon = new ImageIcon(new URL(url));
        backFemaleIconLabel.setIcon(backIcon);
        backFemaleIconLabel.setText("back view");
    }
    public void setFrontShinyFemaleIcon(String url) throws MalformedURLException {
        if (url == null) return;
        frontIcon = new ImageIcon(new URL(url));
        frontFemaleShinyIconLabel.setIcon(frontIcon);
        frontFemaleShinyIconLabel.setText("front view");
    }
    public void setBackShinyFemaleIcon(String url) throws MalformedURLException {
        if (url == null) return;
        backIcon = new ImageIcon(new URL(url));
        backFemaleShinyIconLabel.setIcon(backIcon);
        backFemaleShinyIconLabel.setText("back view");
    }
}

