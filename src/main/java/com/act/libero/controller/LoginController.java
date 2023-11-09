package com.act.libero.controller;

import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.act.libero.dto.LoginInfo;
import com.act.libero.dto.SessionInfo;
import com.act.libero.entity.User;
import com.act.libero.service.LoginService;


@Controller
public class LoginController {
	@Autowired
    protected SessionInfo sessionInfo;

	@Autowired
    LoginService loginService;

	/**
	 * 初期表示
	 * @return 遷移先画面名
	 */
    @RequestMapping("/")
	public String login() {
		return "login";
	}

	/**
	 * 
	 * @param loginInfo ログイン入力情報
	 * @param model モデル
	 * @return 遷移先画面名
	 */
	@PostMapping("/certification")
	public String certification(@ModelAttribute LoginInfo loginInfo, RedirectAttributes redirectAttributes, Model model) {

		//入力情報の取得
		String userId = loginInfo.getUserid();
		String password = loginInfo.getPassword();
		
		//ユーザー情報
		User user = null;

		try {
			// 設定ファイル(application.properties)の読み込み
			ResourceBundle rb = ResourceBundle.getBundle("application");

		// 入力パスワードを暗号化するための設定
		IvParameterSpec ivTest = new IvParameterSpec(rb.getString("crypto.iv.string").getBytes());
		SecretKeySpec keyTest = new SecretKeySpec(rb.getString("crypto.key.string").getBytes(), "AES");
		//ユーザーテーブルからデータ取得
		user = loginService.selectUserPassword(userId, new String(encrypto(password, keyTest, ivTest)));
		} catch (GeneralSecurityException e) {
			// 入力パスワードの暗号化に失敗した場合	
			e.printStackTrace();
			user = null;
		}

		//ユーザー情報取得チェック
		if (user == null) {
			// ユーザテーブルから情報を取得できなかった場合
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザIDかパスワードに誤りがあります。");
			
			// ログイン画面を再表示
			return "redirect:";
		}

		// 認証成功した場合
		// ユーザー情報更新
		if(!loginService.UpdateUserLastLoginAt(userId)){
			// 更新に失敗した場合
			redirectAttributes.addFlashAttribute("errorMessage", "最終ログイン日時の更新に失敗しました。");
			// ログイン画面を再表示
			return "redirect:";
		}
		// セッション情報格納
		sessionInfo.setUserId(user.getUserId());
		sessionInfo.setLastName(user.getLastName());
		sessionInfo.setFirstName(user.getFirstName());
		sessionInfo.setAuthorityNo(user.getAuthorityNo());
		sessionInfo.setUsersGroupId(user.getUsersGroupId());

		// カレンダー種別を遷移先コントローラへ渡す()
		redirectAttributes.addFlashAttribute("calendarSBT", "0");

		// 次画面に遷移
		return "redirect:calendarDisplay";
	}

	/**
	 * 文字列の暗号化
	 * @param plainText 入力文字列
	 * @param key 暗号化Key
	 * @param iv IV
	 * @return 暗号化文字列
	 * @throws GeneralSecurityException 例外
	 */
	private byte[] encrypto(String plainText, SecretKey key, IvParameterSpec iv) throws GeneralSecurityException {
		// 書式:"アルゴリズム/ブロックモード/パディング方式"
		Cipher encrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");

		encrypter.init(Cipher.ENCRYPT_MODE, key, iv);
		return encrypter.doFinal(plainText.getBytes());
	}

	/**
	 * 文字列の復号化
	 * @param cryptoText 入力文字列
	 * @param key 暗号化Key
	 * @param iv IV
	 * @return 復号化文字列
	 * @throws GeneralSecurityException 例外
	 */
	public String decrypto(byte[] cryptoText, SecretKey key, IvParameterSpec iv) throws GeneralSecurityException {			
			
        // 書式:"アルゴリズム/ブロックモード/パディング方式"			
        Cipher decrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");			
        decrypter.init(Cipher.DECRYPT_MODE, key, iv);			
			
        return new String(decrypter.doFinal(cryptoText));			
	}
}
