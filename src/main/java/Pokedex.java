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
        URLConnection connection = new URL("https://pokeapi.co/api/v2/pokemon/380/").openConnection();
        InputStream is = connection.getInputStream();
        System.out.println(connection.getContentType());
        byte[] response = is.readAllBytes();
        String resp = new String(response);
        String prettyString = toPrettyFormat(resp);
        JSONTokener tokener = new JSONTokener(resp);
        JSONObject root = new JSONObject(tokener);
        JSONObject sprites = (JSONObject) root.get("sprites");
        String front_default = sprites.getString("front_default");
        String back_default = sprites.getString("back_default");
        System.out.println(prettyString);
                //"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/381.png";
        drawImg(front_default, back_default);
    }

    private static String toPrettyFormat(String jsonString) {
        JSONTokener tokener = new JSONTokener(jsonString);
        JSONObject root = new JSONObject(tokener);
        return root.toString(4);
    }

    private static void drawImg(String url, String url2) throws IOException {
        JFrame frame = new JFrame();
        Image image = ImageIO.read(new URL(url));
        ImageIcon icon = new ImageIcon(new URL(url));
        ImageIcon icon2 = new ImageIcon(new URL(url2));
        JLabel label_icon = new JLabel(icon);
        JLabel label_icon2 = new JLabel(icon2);
        frame.setIconImage(new ImageIcon(image).getImage());
        JPanel left_panel = new JPanel();
        JPanel right_panel = new JPanel();
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        //left_panel.setLayout(new FlowLayout());
        //right_panel.setLayout(new FlowLayout());
        JLabel title = new JLabel("Pokedex");
        JButton button = new JButton();
        JButton button2 = new JButton();
        button.setText("Button");
        button2.setText("Button2");
        //panel.setSize(800,600);
        container.add(left_panel);
        container.add(right_panel);
        frame.setSize(800,600);
        frame.add(container);
        //frame.add(label);
        left_panel.add(label_icon);
        right_panel.add(label_icon2);
        //frame.add(title);
        left_panel.add(button);
        right_panel.add(button2);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }
}

