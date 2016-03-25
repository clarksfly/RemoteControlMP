package UI;

import Bean.Media;
import Bean.Multimedia;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RemoteControl extends javax.swing.JFrame {
    private DatagramSocket clientSocket;
    private InetAddress IPAddress;
    private int port;
    private String searchPath = new File("").getAbsolutePath();
    private boolean isPlaying = false;
    private boolean isSlideshowPlaying = false;
    
    public RemoteControl(DatagramSocket _clientSocket, InetAddress _IPAddress, int _port) throws Exception {
        initComponents();
        clientSocket = _clientSocket;
        IPAddress = _IPAddress;
        port = _port;
        requestToServer("Initialize");
        editTime.setVisible(false);
        applyBtn.setVisible(false);
        playBtn.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        playBtn = new javax.swing.JButton();
        applyBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        slideshowBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        editTime = new javax.swing.JFormattedTextField();
        fileNameLabel = new javax.swing.JLabel();
        imagePreview = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileNameList = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        uploadFile = new javax.swing.JMenuItem();

        fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\SVE14112EG\\Pictures\\Wallpapers"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(380, 360));
        setPreferredSize(new java.awt.Dimension(380, 360));
        setResizable(false);
        setSize(new java.awt.Dimension(380, 360));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeWindow(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playBtn.setFont(new java.awt.Font("Calibri Light", 1, 15)); // NOI18N
        playBtn.setText("Play");
        playBtn.setBorder(null);
        playBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playBtn.setOpaque(false);
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });
        getContentPane().add(playBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 60, 40));

        applyBtn.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        applyBtn.setText("Apply");
        applyBtn.setBorder(null);
        applyBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        applyBtn.setOpaque(false);
        applyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyBtnActionPerformed(evt);
            }
        });
        getContentPane().add(applyBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 50, 40));

        backBtn.setFont(new java.awt.Font("Calibri Light", 1, 15)); // NOI18N
        backBtn.setText("Back");
        backBtn.setBorder(null);
        backBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 60, 40));

        nextBtn.setFont(new java.awt.Font("Calibri Light", 1, 15)); // NOI18N
        nextBtn.setText("Next");
        nextBtn.setBorder(null);
        nextBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextBtn.setOpaque(false);
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        getContentPane().add(nextBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 60, 40));

        slideshowBtn.setFont(new java.awt.Font("Calibri Light", 1, 15)); // NOI18N
        slideshowBtn.setText("Start Slideshow");
        slideshowBtn.setBorder(null);
        slideshowBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        slideshowBtn.setOpaque(false);
        slideshowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slideshowBtnActionPerformed(evt);
            }
        });
        getContentPane().add(slideshowBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 130, 40));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Universal Remote");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 40));

        editTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        editTime.setText("1000");
        editTime.setInheritsPopupMenu(true);
        getContentPane().add(editTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 60, 40));

        fileNameLabel.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        fileNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(fileNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 210, 20));

        imagePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagePreview.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(imagePreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 210, 120));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List of Files Found in Folder", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 11))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");

        fileNameList.setColumns(20);
        fileNameList.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        fileNameList.setRows(5);
        jScrollPane1.setViewportView(fileNameList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, -1));

        menu.setText("File");

        uploadFile.setText("Upload File");
        uploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFileActionPerformed(evt);
            }
        });
        menu.add(uploadFile);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        try {
            if(isPlaying) {
                playBtn.setText("Play");
                isPlaying = false;
                requestToServer("Stop");
            }
            requestToServer("Next");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_nextBtnActionPerformed

    private void slideshowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slideshowBtnActionPerformed
        if(!isSlideshowPlaying) {
            try {
                isSlideshowPlaying = true;
                requestToServer("StartSlideshow");
                slideshowBtn.setText("Stop Slideshow");
                //playBtn.setVisible(false);
                editTime.setVisible(true);
                applyBtn.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                isSlideshowPlaying = false;
                requestToServer("StopSlideshow");
                slideshowBtn.setText("Start Slideshow");
                editTime.setVisible(false);
                applyBtn.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_slideshowBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        try {
            if(isPlaying) {
                playBtn.setText("Play");
                isPlaying = false;
                requestToServer("Stop");
            }
            requestToServer("Back");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_backBtnActionPerformed

    private void applyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyBtnActionPerformed
        try {
            Integer.parseInt(editTime.getText());
            requestToServer("SetTime:" + editTime.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_applyBtnActionPerformed

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
        if(!isPlaying) {
            try {
                playBtn.setText("Stop");
                isPlaying = true;
                requestToServer("Play");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                playBtn.setText("Play");
                isPlaying = false;
                requestToServer("Stop");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_playBtnActionPerformed

    private void closeWindow(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeWindow
        try {
            requestToServer("Close");
            dispose();
            System.exit(0);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_closeWindow

    private void uploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFileActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String _path = file.getAbsolutePath();
                System.out.println("Chose a file!");
                requestToServer("Upload");
                sendToServer(_path);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_uploadFileActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    public void requestToServer(String request) throws Exception {
        String searchPath = new File("").getAbsolutePath();
        byte[] sendRequest = new byte[1024];
        sendRequest = request.getBytes();   
        DatagramPacket sendPacket = new DatagramPacket(sendRequest, sendRequest.length, IPAddress, port);       
        clientSocket.send(sendPacket);  
        
        if(request.equalsIgnoreCase("Close")) {
            clientSocket.close();
            return;
        } else if(request.equalsIgnoreCase("Next") 
                    || request.equalsIgnoreCase("Back")) {
            receiveFromServer();            
        } else if(request.equalsIgnoreCase("Initialize")) {
            getFileNames();
            receiveFromServer();
        } 
    }
    
    public void receiveFromServer() throws Exception {
        byte[] sendRequest = new byte[1024];
        DatagramPacket sendPacket;
        
        /* Receive details (headers) of file being sent */
        Multimedia file = getFileDetails();
        /* If file is not an image (hence, an audio or a video), get thumbnails first */
        if(!file.getType().equalsIgnoreCase("Image")) {
            String thumbnail = new File("").getAbsolutePath() + "\\TestWrite\\Thumbnails\\" + file.getThumbnailPath();
            System.out.println(file.getThumbnailPath());
            if(checkIfFileExists(thumbnail)) {
                System.out.println("Thumbnail already exists!");
                showPreview(file);
                sendRequest = "Thumbnail Exists".getBytes();
                sendPacket = new DatagramPacket(sendRequest, sendRequest.length, IPAddress, port);
                clientSocket.send(sendPacket);
            } else {
                sendRequest = "New Thumbnail".getBytes();
                sendPacket = new DatagramPacket(sendRequest, sendRequest.length, IPAddress, port);       
                clientSocket.send(sendPacket); 
                byte[] wholeFile = getFile((int) file.getThumbnailLength());
                writeToDisk(wholeFile, thumbnail);
                showPreview(file);
            }
            return; //as of now, don't request for audio & video files first because of shitty udp
        }
        
        /* Finally request for the actual file */
        String location = new File("").getAbsolutePath() + "\\TestWrite\\" + file.getFileName();
        if(checkIfFileExists(location)) {
            showPreview(file);
            System.out.println("File already exists!");
            sendRequest = "File Exists".getBytes();
            sendPacket = new DatagramPacket(sendRequest, sendRequest.length, IPAddress, port);
            clientSocket.send(sendPacket);
        } else {
            sendRequest = "New File".getBytes();
            sendPacket = new DatagramPacket(sendRequest, sendRequest.length, IPAddress, port);       
            clientSocket.send(sendPacket); 
            byte[] wholeFile = getFile((int) file.getLength());
            writeToDisk(wholeFile, location);
            showPreview(file);
        }
    }
    
    public Multimedia getFileDetails() throws Exception {
        byte[] headers = new byte[1024];
        DatagramPacket receiveHeaders = new DatagramPacket(headers, headers.length);
        clientSocket.receive(receiveHeaders);
        ByteArrayInputStream in = new ByteArrayInputStream(receiveHeaders.getData());
        ObjectInputStream is = new ObjectInputStream(in);
        Multimedia file = (Multimedia) is.readObject();
        return file;
    }
    
    public boolean checkIfFileExists(String _path) {
        File file = new File(_path);
        if(file.exists() && file.isFile()) {
            return true;
        } else {
            return false;
        }        
    }
    
    public byte[] getFile(int totalLength) throws Exception {
        int length = totalLength;
        int i = 0;
        int j = 1499;
        int count = 1;
        byte[] wholeFile = new byte[length];
        
        while(length > 0) {
            byte[] receiveData = new byte[1500];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            byte[] data = receivePacket.getData();
            if(j < totalLength) {
                System.out.println("First");
                System.arraycopy(data, 0, wholeFile, i, receiveData.length);
            } else {
                System.out.println("Second");
                int diff = j - totalLength;
                j -= diff;
                System.arraycopy(data, 0, wholeFile, i, receiveData.length-diff);
                System.out.println("FINALLY");
            }
            
            System.out.println("Data: " + data.length + " x " + count);
            i = j;
            j += 1500;
            length -= 1500; 
            count++;
            System.out.println("Length: " + length);
        }
        System.out.println("STOP");
        
        return wholeFile;
    }
    
    public void writeToDisk(byte[] _wholeFile, String _location) throws Exception {
        /* Check first if directories exists */
        if(!new File(searchPath + "\\TestWrite\\").isDirectory()) {
            new File(searchPath + "\\TestWrite\\").mkdirs();
        } 
        
        if(!new File(searchPath + "\\TestWrite\\Thumbnails\\").isDirectory()) {
            new File(searchPath + "\\TestWrite\\Thumbnails\\").mkdirs();
        } 
        
        FileOutputStream fileOuputStream = new FileOutputStream(_location); 
	fileOuputStream.write(_wholeFile);
	fileOuputStream.close();
            //InputStream inputStream = new ByteArrayInputStream(wholeFile);
            //BufferedImage bImageFromConvert = ImageIO.read(inputStream);
            //ImageIO.write(bImageFromConvert, "jpg", new File(searchPath + "\\TestWrite\\" + file.getFileName()));
        System.out.println("Created file!");
    }
    
    public void showPreview(Multimedia _file) throws IOException {
        String location;
        
        if(_file.getType().equalsIgnoreCase("Image")) {
            location = searchPath + "\\TestWrite\\" + _file.getFileName();
            playBtn.setVisible(false);
            slideshowBtn.setVisible(true);
        } else {
            location = searchPath + "\\TestWrite\\Thumbnails\\" + _file.getThumbnailPath();
            playBtn.setVisible(true);
            slideshowBtn.setVisible(false);
        }
        
        Image img = ImageIO.read(new File(location)).getScaledInstance(imagePreview.getWidth(), imagePreview.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img); 
        imagePreview.setIcon(icon);
        fileNameLabel.setText(_file.getFileName());
    }
    
    public void sendToServer(String _path) throws IOException {
        /* Send file details (headers) first */
        sendFileDetails(_path);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket;
        
        /* Receive status to see if file already exists or not in client */
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String status = new String(receivePacket.getData(), 0, receivePacket.getLength());
        if(status.equalsIgnoreCase("File Exists")) {
            return;
        } else if(status.equalsIgnoreCase("New File")) {
            /* Send the actual file by chopping it into 1500-byte chunks */
            sendFile(_path);
        }
    }
    
    public void sendFileDetails(String _path) throws IOException {
        int x = _path.lastIndexOf('\\');
        String name = _path.substring(x+1);
        Multimedia file = new Multimedia(name, _path);
        if(name.contains(".jpg") || name.contains(".png"))
            file.setType("Image");
        else if(name.contains(".mp3"))
            file.setType("Audio");
        else if(name.contains(".mp4"))
            file.setType("Video");
        long size = new File(_path).length();
        file.setLength(size);
        file.setThumbnailPath(null);
        
        byte[] headers = new byte[1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(file);
        headers = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(headers, headers.length, IPAddress, port);
        clientSocket.send(sendPacket);
        System.out.println("Sent file details!");
    }
    
    public void sendFile(String _path) throws IOException {
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
        int count = 0;
        int seqNum = 0;
        int ackNum = 0;
        int repeatedAck = 0;
        int prevAck = 0;
        int tempSeqNum = 0;
        //int windowSize = 5;
        byte[] chunk = new byte[1500];
        byte[] ack = new byte[1024];
        boolean fileComplete = false;
        boolean lostPacket = false;
        ArrayList<byte[]> chunkQueue = new ArrayList<>();
        Queue seqAck = new LinkedList();
        
        /* put all chunks into queue */
        while(length > 0) {
            if(j < buffer.length) {
                chunk = Arrays.copyOfRange(buffer, i, j);
            } else {
                int diff = j - buffer.length;
                j -= diff;
                chunk = Arrays.copyOfRange(buffer, i, j);
            }
            chunkQueue.add(chunk);
                //System.out.println("Data: " + chunk.length + " x " + count);
            //DatagramPacket sendPacket = new DatagramPacket(chunk, chunk.length, IPAddress, port);   
            //clientSocket.send(sendPacket);  

            i = j;
            j += 1500;
            length -= 1500;
        }
        
        
                //System.out.println("Sending segment " + message.getSegmentID() + " with " + bytesRead + " byte payload.");
                //byte[] test = serialize(message);
        System.out.println("Number of Packets to Send: " + chunkQueue.size());
        i = 0;
        while(seqNum < chunkQueue.size() || fileComplete) {
            if(!fileComplete) {
                while(i < 5) {
                        
                    Media fileChunk = new Media(seqNum, chunkQueue.get(seqNum));
                    byte[] test = serialize(fileChunk);
                    DatagramPacket sendPacket = new DatagramPacket(test, test.length, IPAddress, port);   
                    clientSocket.send(sendPacket);  
                    System.out.println("SENT: " + fileChunk.getID());
                    seqAck.add(seqNum);
                    i++;
                    seqNum++;
                    
                    if(lostPacket) {
                        seqNum = tempSeqNum;
                        lostPacket = false;
                    }
                }
                
                if(seqNum >= chunkQueue.size()) {
                    fileComplete = true;
                }
            }
            clientSocket.setSoTimeout(100);
            try {
                DatagramPacket receiveAck = new DatagramPacket(ack, ack.length);
                clientSocket.receive(receiveAck);
                ackNum = Integer.parseInt(new String(receiveAck.getData(), 0, receiveAck.getLength()));
                System.out.println("RCVD ACK#: " + ackNum);
                int temp = (int) seqAck.element();
                if(ackNum == temp) {
                    //System.out.println(ackNum + " is equal with " + temp);
                    seqAck.remove();
                    
                } else {
                    //System.out.println(ackNum + " is NOT equal with " + temp);
                    if(prevAck == ackNum)
                        repeatedAck++;
                    else
                        repeatedAck = 1;
                    if(repeatedAck == 3) {
                        seqNum = ackNum + 1;
                        repeatedAck = 0;
                    }
                    
                    prevAck = ackNum;
                }
                
                i--;
            } catch(SocketTimeoutException e) {
                lostPacket = true;
                tempSeqNum = seqNum;
                seqNum = ackNum + 1;
                System.out.println("Packet with seq. num " + seqNum + " is deemed lost.");
                System.out.println("Trying to send packet with seq. num " + seqNum + "...");
                i--;
                fileComplete = false;
            } 
            
            if(fileComplete && ackNum == seqNum-1) {
                fileComplete = false;
            }
        }
        clientSocket.setSoTimeout(0);
        
    }
    
    public void getFileNames() throws IOException {
        byte[] headers = new byte[1500];
        DatagramPacket receiveFileNames = new DatagramPacket(headers, headers.length);
        clientSocket.receive(receiveFileNames);
        String[] fileNames = new String(receiveFileNames.getData()).trim().split("\\,");
        for(int i = 0; i < fileNames.length; i++) {
            fileNameList.append(fileNames[i] + "\n");
        }
    }
    
    public byte[] serialize(Object obj) throws IOException
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(obj);
        objectStream.flush();
        return byteStream.toByteArray();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JFormattedTextField editTime;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JTextArea fileNameList;
    private javax.swing.JLabel imagePreview;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton playBtn;
    private javax.swing.JButton slideshowBtn;
    private javax.swing.JMenuItem uploadFile;
    // End of variables declaration//GEN-END:variables
}
