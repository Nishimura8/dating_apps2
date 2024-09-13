package models.validators;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserValidator {
    public static List<String> validate(User u,  Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String prefecture_error = _validatePrefecture(u.getPrefecture());
        if(!prefecture_error.equals("")) {
            errors.add(prefecture_error);
        }

        String name_error = _validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = _validatePassword(u.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }



        return errors;
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    private static String _validatePrefecture(String prefecture) {
        if(prefecture == null || prefecture.equals("")) {
            return "都道府県を選択してください";
        }

        return "";
    }



    // パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}