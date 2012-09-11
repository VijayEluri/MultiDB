package image;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import music.db.Disc;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;



public class ImageDealer {

    public final Dimension COVERS_DIM = new Dimension(400,400);
    public final Dimension MAX_COVERS_DIM = new Dimension(1200,800);
    public final int SEEK_NUMBER = 4;
    
    
    private JLabel coversView,selectCoversView,bigCoversView;
    private JFrame selectCoverFrame,bigCoversFrame;
    private JScrollPane bigCoversScroll;
    private JSpinner spinnerCovers;
    private JButton spinnerCoversButton;
    JRadioButton backRButton,frontRButton;
    private SpinnerListModel spinnerCoversM;
    private ImageIcon origIcon, scaledIcon, bigIcon;
    private Disc currentDisc;
    private MultiDBImage multiIm;
	private ArrayList<MultiDBImage> imageListWeb = new ArrayList<MultiDBImage>();
	protected boolean frontCover=true;
	
	
    
    public ImageDealer() {
    	selectFrameInit();
	}
    
    public ImageDealer(Disc disc) {
    	this.currentDisc=disc;
    	selectFrameInit();
	}
    
    public void setDisc(Disc disc){
    	this.currentDisc=disc;
    }


	public void selectFrameInit(){
        ///////////setting icons for pictures
        multiIm = new MultiDBImage();
        
	    selectCoverFrame = new JFrame("Select a picture");
	    selectCoverFrame.setSize(500, 500);
	    selectCoversView = new JLabel();
	    selectCoversView.setMinimumSize(COVERS_DIM);
	    selectCoverFrame.getContentPane().setLayout(new BoxLayout(selectCoverFrame.getContentPane(),BoxLayout.Y_AXIS));
	    spinnerCoversM = new SpinnerListModel();
	    spinnerCovers = new JSpinner(spinnerCoversM);
	    spinnerCoversButton = new JButton("Save current cover");
	    //handler to view covers on selectFrameCover
        ViewCoverHandler viewHandler = new ViewCoverHandler();
        spinnerCovers.addChangeListener(viewHandler);
	    SaveCurrentCoverHandler saveCurrentCoverHandler = new SaveCurrentCoverHandler();
	    spinnerCoversButton.addActionListener(saveCurrentCoverHandler);

	    //Create the radio buttons.
	    frontRButton = new JRadioButton("Front");
	    frontRButton.setSelected(true);

	    backRButton = new JRadioButton("Back");

	    //Group the radio buttons.
	    ButtonGroup frontBackSelect = new ButtonGroup();
	    frontBackSelect.add(frontRButton);
	    frontBackSelect.add(backRButton);
	    
	    SelectTypeCoverHandler selectTypeCoverHandler = new SelectTypeCoverHandler();
	    frontRButton.addActionListener(selectTypeCoverHandler);
	    backRButton.addActionListener(selectTypeCoverHandler);
	    
 
	    selectCoverFrame.getContentPane().add(spinnerCovers);
	    selectCoverFrame.getContentPane().add(frontRButton);
	    selectCoverFrame.getContentPane().add(backRButton);
	    selectCoverFrame.getContentPane().add(spinnerCoversButton);
	    
	    
	  /*  coversView = new JLabel();
        //dimensions for covers
        coversView.setMinimumSize(COVERS_DIM);
	    
	    //bigcover frame
        bigCoversFrame=new JFrame("Cover");
        bigCoversView = new JLabel();
        bigCoversScroll = new JScrollPane(bigCoversView);
        bigCoversFrame.add(bigCoversScroll);*/
    }
    
    
    public void searchImage(String name){
    	String HTMLText="";
    	JSONObject job;
    	ArrayList<String> imageNames = new ArrayList<String>();
    	MultiDBImage tempIm;
    	
    	imageListWeb.clear();
    	/*try{
		  	String search=URLEncoder.encode("'"+name+"'","UTF-8");
		    String bingUrl = "https://api.datamarket.azure.com/Bing/Search/Image?$top="+SEEK_NUMBER+"&$skip=1&$format=json&Query="+search;
		    String accountKey = "q/A6WQyu0vO3KTSBuWct2DJaYopgVSwrbcs60F4aUjc=";
		    byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
		    String accountKeyEnc = new String(accountKeyBytes);
		    URL urlb = new URL(bingUrl);
		    URLConnection urlConnection =urlb.openConnection();
		    urlConnection.setRequestProperty("Authorization","Basic " + accountKeyEnc);
		            
		    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) 
				HTMLText=HTMLText+inputLine;
			//System.out.println(HTMLText);
			in.close();
			
			
			try {
				job = new JSONObject(HTMLText);
				job=job.getJSONObject("d");
				JSONArray list = job.getJSONArray("results");				
				for (int i=0;i<list.length();i++){
					job=list.getJSONObject(i);
					tempIm=new MultiDBImage();
					tempIm.url=job.getString("MediaUrl");
					tempIm.width=job.getInt("Width");
					tempIm.height=job.getInt("Height");
					tempIm.fileSize=job.getInt("FileSize");
					imageNames.add(tempIm.width+" "+tempIm.height+" "+tempIm.url);
					tempIm.image=MultiDBImage.getImageFromUrl(tempIm.url);
					imageListWeb.add(tempIm);
				}
				*/
				tempIm=new MultiDBImage();
				tempIm.url="http://i118.photobucket.com/albums/o99/JouniK86/absml/frontbig.jpg";				
				tempIm.image=MultiDBImage.getImageFromUrl(tempIm.url);
				if (tempIm.image==null) System.out.println("merdaa!");
				imageListWeb.add(tempIm);
				tempIm=new MultiDBImage();
				tempIm.url="http://i118.photobucket.com/albums/o99/JouniK86/absml/rawfront.jpg";				
				tempIm.image=MultiDBImage.getImageFromUrl(tempIm.url);
				if (tempIm.image==null) System.out.println("merdaa!");
				imageListWeb.add(tempIm);
				tempIm=new MultiDBImage();
				tempIm.url="http://www.metalkingdom.net/album/img/d45/23694.jpg";				
				tempIm.image=MultiDBImage.getImageFromUrl(tempIm.url);
				if (tempIm.image==null) System.out.println("merdaa!");
				imageListWeb.add(tempIm);
				
				spinnerCoversM.setList(imageListWeb);
				tempIm=new MultiDBImage();
				tempIm.putImage(selectCoversView,imageListWeb.get(0).image);
				selectCoverFrame.getContentPane().add(selectCoversView);
				selectCoverFrame.setVisible(true);
			/*} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}    
    

    
///////////////////////////////////////////COVER MENU HANDLERS///////////////////////////////
///////////////////////////////////////////COVER MENU HANDLERS///////////////////////////////
///////////////////////////////////////////COVER MENU HANDLERS///////////////////////////////

    
private class SaveCurrentCoverHandler implements ActionListener {

    	String archivo,rutaArch,type;
    	File file;
    	
    	public void actionPerformed(ActionEvent evento) {
    		archivo = ((MultiDBImage) spinnerCovers.getValue()).name;
    		rutaArch = currentDisc.path.getAbsolutePath();
    		file = new File(rutaArch + File.separator + archivo);
    		if (file.canWrite()) {
    			if (frontCover) type="front"; else type="back";
    			if (file.renameTo(new File(rutaArch + File.separator + currentDisc.group + " - " + currentDisc.title + " - " + type+".jpg"))) {
    				JOptionPane.showMessageDialog(selectCoverFrame, "File renamed succesfully");
    				} else {
    				JOptionPane.showMessageDialog(selectCoverFrame, "Could not rename file");
    				}
    				} else {
    				JOptionPane.showMessageDialog(selectCoverFrame, "Could not rename file");
    			}
    			selectCoverFrame.dispose();
    		}
    	} //FIN HANDLER CHANGE NAME
    


private class SelectTypeCoverHandler implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().compareTo("Back")==0) frontCover=false;
		else frontCover=true;
	}
} //FIN SELECT TYPE COVER


   
private class ViewCoverHandler implements ChangeListener {

    // manejar evento de cambio en lista
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        multiIm.putImage(selectCoversView, ((MultiDBImage) spinner.getValue()).image);
    }
} //FIN HANDLER VIEW COVERS

}
