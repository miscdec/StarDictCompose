package com.datong.dict.data.dictionary.en.local;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.datong.dict.data.dictionary.en.local.dao.DatongDao;
import com.datong.dict.data.dictionary.en.local.database.DatongDatabase;
import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord;
import com.datong.dict.data.dictionary.en.source.DatongEnDateSource;
import com.datong.dict.utils.ConvertUtil;
import com.datong.dict.utils.PathUtil;
import com.datong.dict.utils.SecretUtil;
import java.io.File;

/* loaded from: classes.dex */
public class DatongEnLoaclDataSource implements DatongEnDateSource {
    private static DatongEnLoaclDataSource INSTANCE;
    private DatongDao datongDao;

    public static DatongEnLoaclDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DatongEnLoaclDataSource(context);
        }
        return INSTANCE;
    }

    private DatongEnLoaclDataSource(Context context) {
        this.datongDao = DatongDatabase.getInstance(context).datongDao();
    }

    @Override // com.datong.dict.data.dictionary.en.source.DatongEnDateSource
    public void getWord(String keywords, DatongEnDateSource.GetWordCallback callback) {
        if (new File(PathUtil.PATH_DOWNLOAD + "/DICT_EN_DATONG").exists()) {
            getWordFromOfflineDict(keywords, callback);
        } else {
            getWordFromLocalCache(keywords, callback);
        }
    }

    public void addWord(final DatongEnWord word) {
        new Thread(new Runnable() { // from class: com.datong.dict.data.dictionary.en.local.DatongEnLoaclDataSource$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DatongEnLoaclDataSource.this.lambda$addWord$0$DatongEnLoaclDataSource(word);
            }
        }).start();
    }

    public /* synthetic */ void lambda$addWord$0$DatongEnLoaclDataSource(DatongEnWord datongEnWord) {
        this.datongDao.getWord(datongEnWord.word);
    }

    private void getWordFromLocalCache(String keywords, DatongEnDateSource.GetWordCallback callback) {
        callback.onError();
    }

    private void getWordFromOfflineDict(final String keywords, final DatongEnDateSource.GetWordCallback callback) {
//        User user = User.getUser();
//        if (user == null || !user.isProLearner()) {
//            callback.onError();
//        } else {
            new Thread(new Runnable() { // from class: com.datong.dict.data.dictionary.en.local.DatongEnLoaclDataSource$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DatongEnLoaclDataSource.this.lambda$getWordFromOfflineDict$2$DatongEnLoaclDataSource(keywords, callback);
                }
            }).start();
//        }
    }

    public /* synthetic */ void lambda$getWordFromOfflineDict$2$DatongEnLoaclDataSource(String str, final DatongEnDateSource.GetWordCallback getWordCallback) {
        final DatongEnWord decrypt = decrypt(this.datongDao.getWord(str));
        // from class: com.datong.dict.data.dictionary.en.local.DatongEnLoaclDataSource$$ExternalSyntheticLambda2
// java.lang.Runnable
        new Handler(Looper.getMainLooper()).post(() -> DatongEnLoaclDataSource.lambda$getWordFromOfflineDict$1(decrypt, getWordCallback));
    }

    public static /* synthetic */ void lambda$getWordFromOfflineDict$1(DatongEnWord datongEnWord, DatongEnDateSource.GetWordCallback getWordCallback) {
        if (datongEnWord == null || datongEnWord.word.isEmpty()) {
            getWordCallback.onError();
        } else {
            getWordCallback.onLoad(datongEnWord);
        }
    }

    private DatongEnWord decrypt(DatongEnWord word) {
        try {
            if (!TextUtils.isEmpty(ConvertUtil.MAP_DATONG_EN)) {
                word.phoneticUk = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.phoneticUk);
                word.phoneticUs = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.phoneticUs);
                word.expCN = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.expCN);
                word.expEN = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.expEN);
                word.shape = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.shape);
                word.syllable = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.syllable);
                word.sentence = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.sentence);
                word.synonym = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.synonym);
                word.antonym = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.antonym);
                word.wordRoot = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.wordRoot);
                word.wordOrigin = ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, word.wordOrigin);
            } else {
                word.phoneticUk = SecretUtil.decrypt(word.phoneticUk, word.word);
                word.phoneticUs = SecretUtil.decrypt(word.phoneticUs, word.word);
                word.expEN = SecretUtil.decrypt(word.expEN, word.word);
                word.shape = SecretUtil.decrypt(word.shape, word.word);
                word.syllable = SecretUtil.decrypt(word.syllable, word.word);
                word.sentence = SecretUtil.decrypt(word.sentence, word.word);
                word.synonym = SecretUtil.decrypt(word.synonym, word.word);
                word.antonym = SecretUtil.decrypt(word.antonym, word.word);
                word.wordRoot = SecretUtil.decrypt(word.wordRoot, word.word);
                word.wordOrigin = SecretUtil.decrypt(word.wordOrigin, word.word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }
}
