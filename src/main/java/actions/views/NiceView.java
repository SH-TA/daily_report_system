package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Employee;
import models.Report;

/**
 * リアクション情報について画面の入力値・出力地を扱うViewモデル
 * @author ryouta.osada
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class NiceView {

    /**
     * id
     */
    private Integer id;

    /**
     * いいねをつけた日報
     */
    private Report report;

    /**
     * いいねをつけた従業員
     */
    private Employee employee;

    /**
     * いいねの状況
     */
    private Integer niceFlag;

    /**
     * いいねした日時
     */
    private LocalDateTime nicedAt;

}
