import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
import org.json.JSONTokener;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Pokedex {
    public static void main(String[] args) throws IOException {
        URLConnection connection = new URL("https://pokeapi.co/api/v2/pokemon/412/").openConnection();
        InputStream is = connection.getInputStream();
        System.out.println(connection.getContentType());
        byte[] response = is.readAllBytes();
        String resp = new String(response);
        String prettyString = toPrettyFormat(resp);
        JSONTokener tokener = new JSONTokener(resp);
        JSONObject root = new JSONObject(tokener);
        JSONObject sprites = (JSONObject) root.get("sprites");
        String front_default = sprites.getString("front_default");
        System.out.println(prettyString);
                //"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/381.png";
        drawImg(front_default);
    }

    private static String toPrettyFormat(String jsonString) {
        JSONTokener tokener = new JSONTokener(jsonString);
        JSONObject root = new JSONObject(tokener);
        return root.toString(4);
    }

    private static void drawImg(String url) throws IOException {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setSize(500,500);
        panel.setBackground(Color.white);
        Image image = ImageIO.read(new URL(url));
        ImageIcon icon = new ImageIcon(new URL(url));
        JLabel label = new JLabel(icon);
        panel.add(label);
        //frame.getContentPane().add(panel);
        frame.setIconImage(new ImageIcon(image).getImage());
        frame.add(label);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

