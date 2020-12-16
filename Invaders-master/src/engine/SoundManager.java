package engine;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.logging.Logger;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundManager {
	
	private final static int BGM_CYCLECOUNT = 10;
	private final static int SFX_COOLTIME = 1000;
	
	/** Singleton instance of the class. */
	private static SoundManager instance;
	/** Application logger. */
	private static Logger logger;
	
	private Media sound_TitleBGM;
	private Media sound_GameBGM;
	private Media sound_ShipShoot;
	private Media sound_ShipDead;
	private Media sound_EnumyDead;
	private Media sound_Click;
	private MediaPlayer bgmPlayer;
	private MediaPlayer sfxPlayer;
	
	
	private SoundManager() {
		logger = Core.getLogger();
		this.Init();
	}
	
	/**
	 * Returns shared instance of SoundManager.
	 * 
	 * @return Shared instance of SoundManager.
	 */
	protected static SoundManager getInstance() {
		if (instance == null)
			instance = new SoundManager();

		return instance;
	}
	

	protected void Init() {
		// Bring All Sounds.
		try {
				this.sound_TitleBGM = this.loadSoundFile("titleBGM.mp3");
				this.sound_GameBGM = this.loadSoundFile("gameBGM.mp3");
				this.sound_ShipShoot = this.loadSoundFile("shipShoot.mp3");
				this.sound_ShipDead = this.loadSoundFile("shipDead.wav");
				this.sound_EnumyDead = this.loadSoundFile("enumyDead.mp3");
				this.sound_Click = this.loadSoundFile("click.mp3");
		} catch (Exception e) {
			logger.info("Error load Sounds : " + e);
		}
		
		// Set Default BGM
		this.bgmPlayer = new MediaPlayer(this.sound_TitleBGM);
		// Set Default SFX
		this.sfxPlayer = new MediaPlayer(this.sound_Click);
		
	}
	
	/**
     * Bring the sound you want.
     * 
	 * @param soundName
	 * 				The name of the sound you want to bring.
	 * 
     * @throws IOException
     *             In case of loading problems.
     */
    public Media loadSoundFile(String soundName) 
            throws IOException {
        try {
        	new JFXPanel(); // this will prepare JavaFX toolkit and environment
        	
            String jarPath = FileManager.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(jarPath, "UTF-8");
            String soundPath = new File(jarPath).getParent();
            soundPath += File.separator;
            soundPath = soundPath.replace('\\', '/');
            soundPath = "file:/" + soundPath;
			soundPath += "res/sound/" + soundName;
			
			
			Media media = new Media(soundPath);
            
            logger.info("Complete Load sound file.");
            return media;
        } catch (Exception e)
        {
        	logger.info("Error Load sound file : " + e);
        	return null;
        }
        
	}
	
	/**
	 * Changing bgmPlayer media.
	 * 
	 * @param bgmName
	 * 			mediaName : [game], [title].
	 */
	public synchronized void ChangeBGM(String bgmName) {
		
		switch (bgmName) {
			case "game":
				if (this.bgmPlayer.getMedia() == this.sound_GameBGM) 
					return;
				this.bgmPlayer.dispose();
				this.bgmPlayer = new MediaPlayer(this.sound_GameBGM);
				break;
			case "title":
				if (this.bgmPlayer.getMedia() == this.sound_TitleBGM) 
					return;
				this.bgmPlayer.dispose();
				this.bgmPlayer = new MediaPlayer(this.sound_TitleBGM);
				break;
			default :
				logger.info("Can't Find this sound");
				break;
		}
		
		this.bgmPlayer.setCycleCount(BGM_CYCLECOUNT);
		this.bgmPlayer.setVolume(0.3f);
	}
	
	/**
	 * BGM Control.
	 * 
	 * @param flag
	 * 			0 : stop | 1 : play | 2 : pause
	 */
	public synchronized void BGMControler(int flag) {
		switch (flag) {
			case 0:
				this.bgmPlayer.stop();
				break;
			case 1:
				this.bgmPlayer.play();
				break;
			case 2:
				this.bgmPlayer.pause();
				break;
			default :
				logger.info("Illiger BGM Controler flag");
				break;
			}
	}
	
	
	/**
	 * Changing sfxPlayer media.
	 * 
	 * @param sfxName
	 * 			media name : [shipShoot], [shipDead], [enumyDead], [click].
	 */
	public synchronized void ChangeSFX(String sfxName) {
		
		this.sfxPlayer.dispose();
		
		switch (sfxName) {
			case "shipShoot":
				this.sfxPlayer = new MediaPlayer(this.sound_ShipShoot);
				break;
			case "shipDead":
				this.sfxPlayer = new MediaPlayer(this.sound_ShipDead);
				break;
			case "enumyDead":
				this.sfxPlayer = new MediaPlayer(this.sound_EnumyDead);
				break;
			case "click":
				this.sfxPlayer = new MediaPlayer(this.sound_Click);
				break;
			default :
				logger.info("Can't Find this sound");
				break;
		}
		
		this.sfxPlayer.setStopTime(Duration.millis(SFX_COOLTIME));
	}
	
	/**
	 * SFX Control.
	 * 
	 * @param flag
	 * 			0 : stop | 1 : play | 2 : pause
	 */
	public synchronized void SFXControler(int flag) {
		switch (flag) {
			case 0:
				this.sfxPlayer.stop();
				break;
			case 1:
				this.sfxPlayer.play();
				break;
			case 2:
				this.sfxPlayer.pause();
				break;
			default :
				logger.info("Illiger SFX Controler flag");
				break;
		}
	}
	
}
