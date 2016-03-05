package UI;

import Bean.Media;
import Bean.Multimedia;
import com.sun.jna.NativeLibrary;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//import javax.swing.JLabel;
import javax.swing.Timer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MultimediaApp extends javax.swing.JFrame {
    //list of multimedia files
    //private ArrayList<Media> Multimedia = new ArrayList<>();
    private ArrayList<Multimedia> FilesInFolder = new ArrayList<>();
    
    //private File[] files;
    //private JLabel[] label;
    private int index = 0;
    private Timer time;
    
    //media player-related vars
    private MediaPlayerFactory mediaPlayerFactory;
    private EmbeddedMediaPlayer mediaPlayer;
    private Canvas canvas;
    
    private final String searchPath = new File("").getAbsolutePath();
    private DatagramSocket serverSocket; 
    
    public MultimediaApp(DatagramSocket _serverSocket) throws Exception {
        initComponents();
        serverSocket = _serverSocket;
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), searchPath + "\\Libraries\\VLC Plugins\\64-bit");
        showFilesInFolder(new File(searchPath + "\\Multimedia"));
        showImage(setImageSize(index));
        initializeMediaPlayer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        allThumbnails = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        imageViewer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(720, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(720, 100));

        allThumbnails.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        allThumbnails.setForeground(new java.awt.Color(240, 240, 240));
        jScrollPane1.setViewportView(allThumbnails);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 720, 100));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Multimedia Application");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 4, 710, 50));

        panel.setBackground(new java.awt.Color(0, 0, 0));
        panel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 720, 440));
        getContentPane().add(imageViewer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 720, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    public void showFilesInFolder(File folder) throws IOException {
        //Image thumbnail = null;
        String type = "";
        //ImageIcon icon = null;
        File[] files = folder.listFiles();
        //label = new JLabel[files.length];
        for(int i = 0; i < files.length; i++) {
            String path = files[i].getAbsolutePath();
            long size = files[i].length();
            int x = path.lastIndexOf('\\');
            String name = path.substring(x+1);
            if(name.endsWith("jpg") || name.endsWith(".png") || name.endsWith("mp3") || name.endsWith("mp4")) {
                if(name.endsWith("jpg") || name.endsWith(".png")) {
                    //try{
                        //thumbnail = ImageIO.read(new File(path)).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                        Multimedia file = new Multimedia(name, path);
                        //file.setThumbnail(thumbnail);
                        file.setType("Image");
                        file.setLength(size);
                        file.setThumbnailPath(null);
                        FilesInFolder.add(file);
                    //}catch(IOException e){
                    //  System.out.println(e);
                    //}
                    //thumbnail = photo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                } else {
                    String thumbnailPath;
                    int thumbnailLength;
                    if(name.endsWith("mp3")) {
                        thumbnailPath = "audio.png";
                        //thumbnail = ImageIO.read(new File("audio.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                        thumbnailLength = (int) new File(thumbnailPath).length();
                        type = "Audio";
                    } else {
                        thumbnailPath = "video.png";
                        //thumbnail = ImageIO.read(new File("video.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                        thumbnailLength = (int) new File(thumbnailPath).length();
                        type = "Video";
                    }
                    Multimedia file = new Multimedia(name, path);
                    //file.setThumbnail(thumbnail);
                    file.setType(type);
                    file.setLength(size);
                    file.setThumbnailPath(thumbnailPath);
                    file.setThumbnailLength(thumbnailLength);
                    FilesInFolder.add(file);
                    //thumbnail = temp.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                } 
                //icon = new ImageIcon(thumbnail);
               //label[i] = new JLabel();
               //label[i].setIcon(icon);
               //label[i].setText("");
                //allThumbnails.add(label[i]);
                //allThumbnails.repaint();
                //allThumbnails.updateUI();
            } 
        }
    }
    
    public void showImage(ImageIcon icon) {
        imageViewer.setIcon(icon);
    }
    
    public void nextImage() {
        if(index < FilesInFolder.size() - 1) {
            index++;
            showImage(setImageSize(index));
        } 
    }
    
    public void prevImage() {
        if(index > 0) {
            index--;
            showImage(setImageSize(index));
        }
    }
    
    public void initializeMediaPlayer() { 
        canvas = new Canvas();
        panel.add(canvas);
        panel.repaint();

        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        mediaPlayer.setVideoSurface(videoSurface);
        panel.setVisible(false);
    }
        
    public void play() {
        if(FilesInFolder.get(index).getType().equals("Video")) {
            panel.setVisible(true);
        } else {
            panel.setVisible(false);
        }
        
        mediaPlayer.playMedia(FilesInFolder.get(index).getPath());
        return;
    }
    
    public void stop() {
        mediaPlayer.stop();
        panel.setVisible(false);
    }
    
    public void sendToClient(InetAddress _IPAddress, int _port) throws IOException {
        /* Send file details (headers) first */
        sendFileDetails(_IPAddress, _port);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket;
        
        if(!FilesInFolder.get(index).getType().equalsIgnoreCase("Image")) {
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String status = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if(status.equalsIgnoreCase("New Thumbnail")) {
                /* Send the actual file by chopping it into 1500-byte chunks */
                sendFile(_IPAddress, _port, FilesInFolder.get(index).getThumbnailPath());
            }
            return;
        }
        
        /* Receive status to see if file already exists or not in client */
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        String status = new String(receivePacket.getData(), 0, receivePacket.getLength());
        if(status.equalsIgnoreCase("File Exists")) {
            return;
        } else if(status.equalsIgnoreCase("New File")) {
            /* Send the actual file by chopping it into 1500-byte chunks */
            sendFile(_IPAddress, _port, FilesInFolder.get(index).getPath());
        }
    }
    
    public void sendFileDetails(InetAddress _IPAddress, int _port) throws IOException {
        byte[] headers = new byte[1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(FilesInFolder.get(index));
        headers = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(headers, headers.length, _IPAddress, _port);
        serverSocket.send(sendPacket);
        System.out.println("Sent file details!");
    }
    
    public void sendFile(InetAddress _IPAddress, int _port, String _path) throws IOException {
        File file = new File(_path);
        byte[] buffer = new byte[(int) file.length()];
        
        /* Convert file to byte array so it can be sent */
        try (
            FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(buffer);
            fileInputStream.close();
        }
        
        int i = 0;
	int j = 1499;
        int length = buffer.length;
        int count = 1;
        
        while(length > 0) {
            byte[] chunk = new byte[1500];
            if(j < buffer.length) {
                System.out.println("First");
		chunk = Arrays.copyOfRange(buffer, i, j);
            } else {
                System.out.println("Second");
                int diff = j - buffer.length;
                j -= diff;
                chunk = Arrays.copyOfRange(buffer, i, j);
                
                System.out.println("FINALLY");
            }
            System.out.println("Data: " + chunk.length + " x " + count);
            DatagramPacket sendPacket = new DatagramPacket(chunk, chunk.length, _IPAddress, _port);       
            serverSocket.send(sendPacket);  
            i = j;
            j += 1500;
            length -= 1500;
            count++;
            System.out.println("Length: " + length);
        }
    }
    
    public void slideshow(int i) { 
        /*final InetAddress IPAddress = _IPAddress;
        final int port = _port;*/
        time = new Timer(i,new ActionListener() { 
            @Override public void actionPerformed(ActionEvent e) { 
                //String check = FilesInFolder.get(index).getFileName();
                /*try {
                    sendFileName(IPAddress, port);
                } catch (IOException ex) {
                    Logger.getLogger(MultimediaApp.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                if(FilesInFolder.get(index).getType().equals("Image")) {
                    showImage(setImageSize(index));
                    index++;
                } else {
                    index++;
                }
                
                if(index >= FilesInFolder.size())
                    index = 0; 
            } 
        }); 
        time.start(); 
    }  
    
    public void stopSlideshow() {
        time.stop();
    }
    
    public ImageIcon setImageSize(int i) { 
        Image img = null;
        String location;
        
        if(FilesInFolder.get(i).getType().equalsIgnoreCase("Image")) {
            location = FilesInFolder.get(i).getPath();
        } else {
            location = FilesInFolder.get(i).getThumbnailPath();
        }
        
        try{
            img = ImageIO.read(new File(location)).getScaledInstance(imageViewer.getWidth(), imageViewer.getHeight(), Image.SCALE_SMOOTH);
        }catch(IOException e){
          System.out.println(e);
        }
        
        ImageIcon icon = new ImageIcon(img); 
        return icon; 
    }
    
    public void editTimer(int i) {
        if(time.isRunning()) {
            time.stop();
            slideshow(i);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allThumbnails;
    private javax.swing.JLabel imageViewer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
