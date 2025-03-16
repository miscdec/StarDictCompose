package com.datong.dict.utils;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.text.TextUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import java.io.File;

/* loaded from: classes.dex */
public class SoundUtil {
    public static final int DICT_TYPE_EN_BING = 4;
    public static final int DICT_TYPE_EN_CIBA = 3;
    public static final int DICT_TYPE_EN_DATONG = 1;
    public static final int DICT_TYPE_EN_DICT_CN = 5;
    public static final int DICT_TYPE_EN_YOUDAO = 2;
    public static final int DICT_TYPE_JP_DATONG = -1;
    private static SoundUtil INSTANCE = null;
    public static final int LANGUAGE_TYPE_CN = 3;
    public static final int LANGUAGE_TYPE_EN_UK = 2;
    public static final int LANGUAGE_TYPE_EN_US = 1;
    public static final int LANGUAGE_TYPE_JP = 4;
    public static final int SOUND_TYPE_SENTENCE = 2;
    public static final int SOUND_TYPE_WORD = 1;
    public static String SOUND_URL_CN_BAIDU;
    public static String SOUND_URL_CN_SOUGOU;
    public static String SOUND_URL_JP_BAIDU;
    public static String SOUND_URL_JP_JAPANESE_DICT;
    public static String SOUND_URL_JP_SOUGOU;
    public static String SOUND_URL_UK_BAIDU;
    public static String SOUND_URL_UK_DATONG;
    public static String SOUND_URL_UK_YOUDAO;
    public static String SOUND_URL_US_BAIDU;
    public static String SOUND_URL_US_DATONG;
    public static String SOUND_URL_US_SOUGOU;
    public static String SOUND_URL_US_YOUDAO;
    private int currentSteamId;
    private int dictType;
    private boolean isSlowSound;
    private boolean isSlowSoundEnable;
    private int languageType;
    private int lastSteamId;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private int soundType;
    private String text = "";
    private String url = "";

    public static SoundUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SoundUtil();
        }
        SoundUtil soundUtil = INSTANCE;
        soundUtil.isSlowSoundEnable = true;
        return soundUtil;
    }

    private SoundUtil() {
    }

    public SoundUtil text(String text) {
        if (!this.text.equals(text)) {
            this.isSlowSound = false;
        }
        this.text = text;
        return INSTANCE;
    }

    public SoundUtil url(String url) {
        String str = this.url;
        if (str == null || !str.equals(url)) {
            this.isSlowSound = false;
        }
        this.url = url;
        return INSTANCE;
    }

    public SoundUtil slowSound(boolean isSlowSoundEnable) {
        this.isSlowSoundEnable = isSlowSoundEnable;
        return INSTANCE;
    }

    public SoundUtil soundType(int soundType) {
        if (this.soundType != soundType) {
            this.isSlowSound = false;
        }
        this.soundType = soundType;
        return INSTANCE;
    }

    public SoundUtil languageType(int languageType) {
        if (this.languageType != languageType) {
            this.isSlowSound = false;
        }
        this.languageType = languageType;
        return INSTANCE;
    }

    public SoundUtil dictType(int dictType) {
        if (this.dictType != dictType) {
            this.isSlowSound = false;
        }
        this.dictType = dictType;
        return INSTANCE;
    }

    public SoundUtil play() {
        String soundPath = getSoundPath();
        String soundUrl = getSoundUrl();
        if (new File(soundPath).exists()) {
            playSound(soundPath);
        } else {
            downloadSound(soundPath, soundUrl);
        }
        return INSTANCE;
    }

    public void destroy() {
        this.text = "";
        this.url = "";
        this.isSlowSound = false;
        SoundPool soundPool = this.soundPool;
        if (soundPool != null) {
            soundPool.release();
            this.soundPool = null;
        }
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    private void downloadSound(String soundPath, String soundUrl) {
        if (!NetworkUtil.isUsable()) {
            this.dictType = this.dictType > 0 ? 1 : -1;
            playSound(getSoundPath());
            return;
        }
        FileDownloader.getImpl().pauseAll();
        FileDownloader.getImpl().create(soundUrl).setPath(soundPath).setListener(new FileDownloadSampleListener() { // from class: com.datong.dict.utils.SoundUtil.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadSampleListener, com.liulishuo.filedownloader.FileDownloadListener
            public void error(BaseDownloadTask task, Throwable e) {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.liulishuo.filedownloader.FileDownloadSampleListener, com.liulishuo.filedownloader.FileDownloadListener
            public void completed(BaseDownloadTask task) {
                SoundUtil.this.playSound(task.getPath());
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playSound(String soundPath) {
        SoundPool soundPool = this.soundPool;
        if (soundPool != null) {
            soundPool.stop(this.lastSteamId);
        }
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        if (this.soundType == 1) {
            if (this.isSlowSound) {
                playSoundByMediaPlayer(soundPath);
            } else {
                playSoundBySoundPool(soundPath);
            }
            if (this.isSlowSoundEnable) {
                this.isSlowSound = !this.isSlowSound;
            }
        } else if (this.text.length() > 100) {
            playSoundByMediaPlayer(soundPath);
        } else {
            playSoundBySoundPool(soundPath);
        }
    }

    private void playSoundByMediaPlayer(String soundPath) {
        if (Build.VERSION.SDK_INT < 21) {
            playSoundBySoundPool(soundPath);
            return;
        }
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        try {
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(soundPath);
            this.mediaPlayer.setAuxEffectSendLevel(1.0f);
            this.mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(2).setLegacyStreamType(3).setUsage(1).build());
            if (SettingUtil.isSlowSound() && this.isSlowSound && Build.VERSION.SDK_INT >= 23) {
                MediaPlayer mediaPlayer = this.mediaPlayer;
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(SettingUtil.getSlowSoundSpeed()));
            }
            this.mediaPlayer.setVolume(1.0f, 1.0f);
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
        } catch (Exception unused) {
            playSoundBySoundPool(soundPath);
        }
    }

    private void playSoundBySoundPool(final String soundPath) {
        if (this.soundPool == null) {
            if (Build.VERSION.SDK_INT >= 21) {
                SoundPool.Builder builder = new SoundPool.Builder();
                AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
                builder2.setLegacyStreamType(3);
                builder.setAudioAttributes(builder2.build());
                builder.setMaxStreams(1);
                this.soundPool = builder.build();
            } else {
                this.soundPool = new SoundPool(1, 3, 1);
            }
        }
        this.soundPool.stop(this.lastSteamId);
        this.currentSteamId = this.soundPool.load(soundPath, 1);
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.datong.dict.utils.SoundUtil$$ExternalSyntheticLambda0
            @Override // android.media.SoundPool.OnLoadCompleteListener
            public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
                SoundUtil.this.lambda$playSoundBySoundPool$0$SoundUtil(soundPath, soundPool, i, i2);
            }
        });
    }

    public /* synthetic */ void lambda$playSoundBySoundPool$0$SoundUtil(String str, SoundPool soundPool, int i, int i2) {
        SoundPool soundPool2;
        if (i2 != 0) {
            new File(str).delete();
        } else if (this.currentSteamId == i && (soundPool2 = this.soundPool) != null) {
            this.lastSteamId = soundPool2.play(i, 1.0f, 1.0f, 1, 0, 1.0f);
            this.soundPool.unload(i);
        }
    }

    private String getSoundPath() {
        int i = this.soundType;
        if (i == 1) {
            int i2 = this.languageType;
            if (i2 == 1) {
                int i3 = this.dictType;
                if (i3 == 1) {
                    return PathUtil.PATH_SOUND_DATONG_US + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i3 == 2) {
                    return PathUtil.PATH_SOUND_YOUDAO_US + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i3 == 3) {
                    return PathUtil.PATH_SOUND_CIBA_US + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i3 == 4) {
                    return PathUtil.PATH_SOUND_BING_US + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i3 == 5) {
                    return PathUtil.PATH_SOUND_DICTCN_US + "/" + MD5Util.getMyMD5(this.text + this.url) + ".mp3";
                }
            } else if (i2 == 2) {
                int i4 = this.dictType;
                if (i4 == 1) {
                    return PathUtil.PATH_SOUND_DATONG_UK + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i4 == 2) {
                    return PathUtil.PATH_SOUND_YOUDAO_UK + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i4 == 3) {
                    return PathUtil.PATH_SOUND_CIBA_UK + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i4 == 4) {
                    return PathUtil.PATH_SOUND_BING_UK + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
                } else if (i4 == 5) {
                    return PathUtil.PATH_SOUND_DICTCN_UK + "/" + MD5Util.getMyMD5(this.text + this.url) + ".mp3";
                }
            } else if (i2 == 4 && this.dictType == -1) {
                return PathUtil.PATH_SOUND_DATONG_JP + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
            }
        } else if (i != 2) {
            return "";
        }
        return PathUtil.PATH_CACHE_SENTENCE + "/" + MD5Util.getMyMD5(this.text) + ".mp3";
    }

    private String getSoundUrl() {
        if (TextUtils.isEmpty(this.url)) {
            initURLs();
            int i = this.soundType;
            if (i == 1) {
                int i2 = this.languageType;
                if (i2 == 1) {
                    int i3 = this.dictType;
                    if (i3 == 1) {
                        return SOUND_URL_US_BAIDU;
                    }
                    if (i3 == 2) {
                        return SOUND_URL_US_YOUDAO;
                    }
                } else if (i2 == 2) {
                    int i4 = this.dictType;
                    if (i4 == 1) {
                        return SOUND_URL_UK_BAIDU;
                    }
                    if (i4 == 2) {
                        return SOUND_URL_UK_YOUDAO;
                    }
                } else if (i2 == 4 && this.dictType == -1) {
                    return SOUND_URL_JP_BAIDU;
                }
            } else if (i != 2) {
                return "";
            }
            int i5 = this.languageType;
            if (i5 == 1 || i5 == 2) {
                return this.text.length() > 300 ? SOUND_URL_US_SOUGOU : SOUND_URL_US_YOUDAO;
            } else if (i5 == 4) {
                return SOUND_URL_JP_BAIDU;
            } else {
                return i5 == 3 ? SOUND_URL_CN_BAIDU : "";
            }
        }
        return this.url;
    }

    private void initURLs() {
        SOUND_URL_UK_DATONG = "http://p22alm2w2.bkt.clouddn.com/" + this.text + ".mp3";
        StringBuilder sb = new StringBuilder();
        sb.append("https://fanyi.baidu.com/gettts?lan=uk&spd=3&source=web&text=");
        sb.append(TextUtil.URLEncode(this.text));
        SOUND_URL_UK_BAIDU = sb.toString();
        SOUND_URL_UK_YOUDAO = "http://dict.youdao.com/dictvoice?type=1&audio=" + TextUtil.URLEncode(this.text);
        SOUND_URL_US_DATONG = "http://p22ajbz94.bkt.clouddn.com/" + this.text + ".mp3";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("https://fanyi.baidu.com/gettts?lan=en&spd=3&source=web&text=");
        sb2.append(TextUtil.URLEncode(this.text));
        SOUND_URL_US_BAIDU = sb2.toString();
        SOUND_URL_US_YOUDAO = "http://dict.youdao.com/dictvoice?type=2&audio=" + TextUtil.URLEncode(this.text);
        SOUND_URL_US_SOUGOU = "https://fanyi.sogou.com/reventondc/synthesis?text=" + TextUtil.URLEncode(this.text) + "&speed=2&lang=en&from=translateweb";
        SOUND_URL_JP_JAPANESE_DICT = "http://www.japandict.com/voice/read?text=" + TextUtil.URLEncode(this.text) + "&outputFormat=ogg_vorbis";
        StringBuilder sb3 = new StringBuilder();
        sb3.append("https://fanyi.baidu.com/gettts?lan=jp&spd=3&source=web&text=");
        sb3.append(TextUtil.URLEncode(this.text));
        SOUND_URL_JP_BAIDU = sb3.toString();
        SOUND_URL_JP_SOUGOU = "https://fanyi.sogou.com/reventondc/microsoftGetSpeakFile?text=" + TextUtil.URLEncode(this.text) + "&spokenDialect=ja&from=translateweb";
        StringBuilder sb4 = new StringBuilder();
        sb4.append("https://fanyi.baidu.com/gettts?lan=zh&spd=3&source=web&text=");
        sb4.append(TextUtil.URLEncode(this.text));
        SOUND_URL_CN_BAIDU = sb4.toString();
        SOUND_URL_CN_SOUGOU = "https://fanyi.sogou.com/reventondc/synthesis?text=" + TextUtil.URLEncode(this.text) + "&speed=1&lang=zh-CHS&from=translateweb";
    }
}
