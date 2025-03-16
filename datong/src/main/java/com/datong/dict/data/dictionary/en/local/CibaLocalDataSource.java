package com.datong.dict.data.dictionary.en.local;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import com.datong.dict.data.dictionary.en.local.dao.CibaDao;
import com.datong.dict.data.dictionary.en.local.database.CibaDatabase;
import com.datong.dict.data.dictionary.en.local.entity.CibaWord;
import com.datong.dict.data.dictionary.en.source.CibaDataSource;
import com.datong.dict.utils.SecretUtil;

/* loaded from: classes.dex */
public class CibaLocalDataSource implements CibaDataSource {
    private static CibaLocalDataSource INSTANCE;
    private CibaDao cibaDao;

    public static CibaLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CibaLocalDataSource(context);
        }
        return INSTANCE;
    }

    private CibaLocalDataSource(Context context) {
        this.cibaDao = Room.databaseBuilder(context, CibaDatabase.class, "ciba").build().cibaDao();
    }

    @Override // com.datong.dict.data.dictionary.en.source.CibaDataSource
    public void getWord(final String keywords, final CibaDataSource.GetWordCallback callback) {
//        User user = User.getUser();
//        if (user == null || !user.isProLearner()) {
//            callback.onError();
//        } else {
            new Thread(new Runnable() { // from class: com.datong.dict.data.dictionary.en.local.CibaLocalDataSource$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CibaLocalDataSource.this.lambda$getWord$1$CibaLocalDataSource(keywords, callback);
                }
            }).start();
//        }
    }

    public /* synthetic */ void lambda$getWord$1$CibaLocalDataSource(String str, final CibaDataSource.GetWordCallback getWordCallback) {
        final CibaWord word = this.cibaDao.getWord(str);
        decrypt(word);
        // from class: com.datong.dict.data.dictionary.en.local.CibaLocalDataSource$$ExternalSyntheticLambda1
// java.lang.Runnable
        new Handler(Looper.getMainLooper()).post(() -> CibaLocalDataSource.lambda$getWord$0(word, getWordCallback));
    }

    public static /* synthetic */ void lambda$getWord$0(CibaWord cibaWord, CibaDataSource.GetWordCallback getWordCallback) {
        if (cibaWord == null) {
            getWordCallback.onError();
        } else {
            getWordCallback.onLoad(cibaWord);
        }
    }

    private void decrypt(CibaWord word) {
        if (word == null) {
            return;
        }
        try {
            word.setPhoneticUs(SecretUtil.decrypt(word.getPhoneticUs(), word.getWord()));
            word.setPhoneticUk(SecretUtil.decrypt(word.getPhoneticUk(), word.getWord()));
            word.setSoundUsUrl(SecretUtil.decrypt(word.getSoundUsUrl(), word.getWord()));
            word.setSoundUkUrl(SecretUtil.decrypt(word.getSoundUkUrl(), word.getWord()));
            word.collins = SecretUtil.decrypt(word.collins, word.getWord());
            word.expEn2Cn = SecretUtil.decrypt(word.expEn2Cn, word.getWord());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
